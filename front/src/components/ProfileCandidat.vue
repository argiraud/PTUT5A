<template>
    <v-container>
        <v-snackbar v-model="snackbarError" multi-line top color="red">
            {{Erreur}}
        </v-snackbar>

        <v-snackbar v-model="snackbarSuccess" multi-line top color="success">
            {{Message}}
        </v-snackbar>
        <p>
            currentuser
            {{currentUser.Id}}
        </p>
        <p>
            profileUserInfos
            {{profileUserInfos.Id}}
            {{ isDisabled}}
        </p>
        <v-form v-model="valid" lazy-validation ref="form">
            <v-text-field
                    :disabled="isDisabled"
                    v-model="profileUserInfos.Name"
                    :rules="nameRules"
                    id="name"
                    label="Nom"
                    required
                    type="text"
                    color="teal accent-3"
                    prepend-inner-icon="person"
            ></v-text-field>

            <v-text-field
                    :disabled="isDisabled"
                    v-model="profileUserInfos.Firstname"
                    :rules="nameRules"
                    id="firstname"
                    label="Prénom"
                    prepend-inner-icon="person"
                    type="text"
                    color="teal accent-3"
                    required
            ></v-text-field>
            <v-text-field
                    :disabled="isDisabled"
                    v-model="profileUserInfos.StudentNumber"
                    id="studentNumber"
                    :counter="8"
                    :rules="studentNumberRules"
                    type="number"
                    label="N° Etudiant"
                    required
                    prepend-inner-icon="confirmation_number"
            ></v-text-field>
            <v-menu
                    :disabled="isDisabled"
                    ref="menu"
                    v-model="menu"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    lazy
                    transition="scale-transition"
                    offset-y
                    full-width
                    min-width="290px"
                    prepend-inner-icon="calendar_today"
            >
                <template v-slot:activator="{ on }">
                    <v-text-field
                            :disabled="isDisabled"
                            id="birthDate"
                            v-model="profileUserInfos.BirthDate"
                            label="Date de naissance"
                            readonly
                            v-on="on"
                            prepend-inner-icon="calendar_today"
                    ></v-text-field>
                </template>
                <v-date-picker
                        ref="picker"
                        v-model="profileUserInfos.BirthDate"
                        hint="MM/DD/YYYY format"
                        persistent-hint
                        :max="new Date().toISOString().substr(0, 10)"
                        min="1950-01-01"
                        prepend-inner-icon="calendar_today"
                ></v-date-picker>
            </v-menu>

            <v-text-field
                    :disabled="isDisabled"
                    v-model="profileUserInfos.Email"
                    id="emailInscription"
                    :rules="emailRules"
                    prepend-inner-icon="email"
                    type="text"
                    label="E-mail"
                    required
            ></v-text-field>
            <v-select
                    :disabled="isDisabled"
                    id="comboStatus"
                    label="Renseignez votre status actuel"
                    small-chips
                    v-model="profileUserInfos.Status"
                    :items="items"
            >
                <template v-slot:selection="{ item, selected }">
                    <v-chip
                            :color="`${item.color} lighten-3`"
                            :input-value="selected"
                            label
                            small
                    >
                      <span class="pr-2">
                        {{ item.text }}
                      </span>
                    </v-chip>
                </template>
                <template v-slot:item="{ item }">
                    <v-chip
                            :color="`${item.color} lighten-3`"
                            dark
                            label
                            small
                    >
                        {{ item.text }}
                    </v-chip>
                </template>
            </v-select>
            <v-textarea
                    :disabled="isDisabled"
                    name="presentation"
                    v-model="profileUserInfos.Presentation"
                    id="presentation"
                    prepend-inner-icon="create"
                    type="text"
                    label="Présentation"
            ></v-textarea>
            <div :hidden="isDisabled">
                <ChangeMotDePasse @error-newpassword="setSnackbarError" @newpassword-ok="setSnackbarSuccess" ></ChangeMotDePasse>
            </div>

            <div class="text-center mt-n5">
                <v-btn
                        style="margin-top: 5%"
                        rounded outlined
                        color="teal accent-3"
                        @click="save"
                        :disabled="!valid || isDisabled">Enregistrer</v-btn>
            </div>
        </v-form>
        <p></p>
        <div :hidden="isDisabled">
            <v-card
                    elevation="10"
            >
                <v-card-title>Entreprises me comptant dans leurs voeux</v-card-title>
                <v-data-table
                        :headers="headers"
                        :items="companies"
                        :items-per-page="5"
                ></v-data-table>
            </v-card>
        </div>
    </v-container>
</template>

<script>
    import {mapState} from 'vuex';
    import ChangeMotDePasse from "@/components/ChangeMotDePasse";
    import StudentDataService from "@/service/StudentDataService";

    export default {
        name: "ProfileCandidat",
        components: {ChangeMotDePasse},
        data: () => ({
            items: [
                    {
                        text: 'Disponible',
                        color: 'green',
                        value: 'available'
                    },
                    {
                        text: 'En cours',
                        color: 'orange',
                        value: 'in progress'
                    },
                    {
                        text: 'Terminé',
                        color: 'red',
                        value: 'finished'
                    }],
            companies: [],
            headers: [
                {
                    text: "Id",
                    align: "start",
                    value: "idEntity",
                    sortable: true
                },
                {
                    text: "Nom",
                    value: "name",
                    sortable: true
                },
            ],
            snackbarSuccess : false,
            snackbarError : false,
            Erreur: '',
            Message: '',
            valid: true,
            nameRules: [
                v => !!v || 'Le nom et le prénom sont requis',
                v => (v && v.length <= 20) || 'Le nom ou le prénom doivent faire moins de 20 charactères',
            ],
            studentNumberRules: [
                //v => /.+@.+\..+/.test(v) || 'Le numéro étudiant doit être composé uniquement de chiffres',
                v => (v.length == 8 || v.length == 0) || 'Le numéro étudiant est composé de 8 chiffres'
            ],
            emailRules: [
                v => !!v || 'L\'addresse mail est requise',
                v => /.+@.+\..+/.test(v) || 'L\'addresse mail doit être valide',
            ],
            mdp: '',
            mdpRules: [
                v => !!v || 'Le mot de passe est requis',
            ],
            picker: new Date().toISOString().substr(0, 10),
            menu: false,
        }),
        watch: {
            menu (val) {
                val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
            }
        },
        methods:{
            save(){
                let userToModify = this.profileUserInfos;
                let user = {
                    "id" : userToModify.Id,
                    "name" : userToModify.Name,
                    "email" : userToModify.Email,
                    "presentation" : userToModify.Presentation,
                    "roles" : [],
                    "files" : [],
                    "enable" : "",
                    "firstName" : userToModify.Firstname,
                    "etudiantNumber" : userToModify.StudentNumber,
                    "birthDate" : userToModify.BirthDate,
                    "status" : userToModify.Status,
                };
                StudentDataService.updateUserInfos(user).then(response => {
                    console.log("response : ")
                    console.log(response)
                    var payload;
                    switch(response.status){
                        case 200:
                            payload = {
                                "message" : "Modifications enregistrées. - Redirection dans 2 secondes..."
                            }
                            this.setSnackbarSuccess(payload)
                                var store = this.$store
                                var router = this.$router
                            setTimeout(function(){
                                window.sessionStorage.clear();
                                store.commit('CONNEXION_MANAGEMENT', false);
                                router.push("/Connexion");
                            }, 2000)
                            break;
                        case 401:
                            payload = {
                                "message" : "Vous devez être authentifié pour modifier votre profil"
                            }
                            this.setSnackbarError(payload)
                            break;
                        case 400:payload = {
                            "message" : "Votre token de session est compromis ou périmé, veuillez vous reconnecter."
                            }
                            this.setSnackbarError(payload)
                            break;
                    }
                })
            },
            setSnackbarError(payload){
                this.snackbarError = true;
                this.Erreur = payload.message;
            },
            setSnackbarSuccess(payload){
                this.snackbarSuccess = true;
                this.Message = payload.message;
            },
            LogOut(){
                window.sessionStorage.clear();
                this.$store.commit('CONNEXION_MANAGEMENT', false);
                this.$router.push("/Connexion");
            },
            mounted(){
               this.getAllVoeux();
            },
            getAllVoeux(){
                let userId = this.currentUser.Id;
                StudentDataService.CompaniesWhoWantedMe(userId).then(response => {
                    console.log("getAllVoeux : ");
                    console.log(response.data);
                    this.companies = response.data;
                })
            }
            },
        computed: {
            ...mapState({
                isConnected: 'isConnected',
                currentUser: 'currentUser',
                profileUserInfos : 'profileUserInfos'
            }),
            isDisabled(){
                if(this.currentUser.IsAdmin){
                    return false;
                }else
                {
                    if (this.currentUser.Id !== this.profileUserInfos.Id)
                    {
                        return true
                    } else {
                        return false;
                    }
                }
            }
        }
    }
</script>

<style scoped>

</style>