<template>
    <v-container>
        <v-snackbar v-model="snackbarError" multi-line top color="red">
            {{Erreur}}
        </v-snackbar>

        <v-snackbar v-model="snackbarSuccess" multi-line top color="success">
            {{Message}}
        </v-snackbar>
        <v-form v-model="valid" lazy-validation ref="form">
            <v-text-field
                    v-model="currentUser.Name"
                    :rules="nameRules"
                    id="name"
                    label="Nom"
                    required
                    type="text"
                    color="teal accent-3"
                    prepend-inner-icon="person"
            ></v-text-field>

            <v-text-field
                    v-if="currentUser.RoleId == 1"
                    v-model="currentUser.Firstname"
                    :rules="nameRules"
                    id="firstname"
                    label="Prénom"
                    prepend-inner-icon="person"
                    type="text"
                    color="teal accent-3"
                    required
            ></v-text-field>
            <v-text-field
                    v-if="currentUser.RoleId == 1"
                    v-model="currentUser.StudentNumber"
                    id="studentNumber"
                    :counter="8"
                    :rules="studentNumberRules"
                    type="number"
                    label="N° Etudiant"
                    required
                    prepend-inner-icon="confirmation_number"
            ></v-text-field>
            <v-menu
                    v-if="currentUser.RoleId == 1"
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
                            v-if="currentUser.RoleId == 1"
                            id="birthDate"
                            v-model="currentUser.BirthDate"
                            label="Date de naissance"
                            readonly
                            v-on="on"
                            prepend-inner-icon="calendar_today"
                    ></v-text-field>
                </template>
                <v-date-picker
                        ref="picker"
                        v-model="currentUser.BirthDate"
                        hint="MM/DD/YYYY format"
                        persistent-hint
                        :max="new Date().toISOString().substr(0, 10)"
                        min="1950-01-01"
                        prepend-inner-icon="calendar_today"
                ></v-date-picker>
            </v-menu>

            <v-text-field
                    v-model="currentUser.Email"
                    id="emailInscription"
                    :rules="emailRules"
                    prepend-inner-icon="email"
                    type="text"
                    label="E-mail"
                    required
            ></v-text-field>
            <v-select
                    id="comboStatus"
                    label="Renseignez votre status actuel"
                    small-chips
                    v-model="currentUser.Status"
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
                    name="presentation"
                    v-model="currentUser.Presentation"
                    id="presentation"
                    prepend-inner-icon="create"
                    type="text"
                    label="Présentation"
            ></v-textarea>
            <ChangeMotDePasse @error-newpassword="setSnackbarError" @newpassword-ok="setSnackbarSuccess" ></ChangeMotDePasse>

            <div class="text-center mt-n5">
                <v-btn
                        style="margin-top: 5%"
                        rounded outlined
                        color="teal accent-3"
                        @click="save"
                        :disabled="!valid">Enregistrer</v-btn>
            </div>
        </v-form>
        <p></p>
            <v-card
                    elevation="10"
            >
                <v-card-title>Entreprises me comptant dans leurs voeux</v-card-title>
                <v-data-table
                        :headers="headers"
                        :items="companies"
                        :items-per-page="5"
                >
                    <template v-slot:[`item.actions`]="{ item }">
                        <PopUpOtherProfile :id-user-to-display="item.idEntity"></PopUpOtherProfile>
                    </template>
                </v-data-table>
            </v-card>
    </v-container>
</template>

<script>
    import ChangeMotDePasse from "@/components/ChangeMotDePasse";
    import StudentDataService from "@/service/StudentDataService";
    import {mapState} from "vuex";
    import PopUpOtherProfile from "@/components/PopUpOtherProfile";
    export default {
        name: "Profil",
        components: {PopUpOtherProfile, ChangeMotDePasse},
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
                    value: 'inprogress'
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
                {
                    text: '',
                    value: 'actions',
                    sortable: false }
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
        computed: {
            ...mapState({
                currentUser: 'currentUser',
            }),
        },
        beforeCreate() {
            //On récupère l'utilisateur connecté.
            StudentDataService.getConnectedUser().then(response => {
                switch (response.status) {
                    case 200 :
                        console.log("case 200")
                        console.log(response.data)
                        this.$store.commit('SET_CURRENTUSER_FROM_JSON', response.data);
                        this.$store.commit('CONNEXION_MANAGEMENT', true);
                        break;
                }
            }).catch(err => {
                console.log("erreur : " + err);
            });
        },
        methods:{
            save(){
                let userToModify = this.currentUser;
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
            getAllVoeux(){
                let userId = this.currentUser.Id;
                StudentDataService.CompaniesWhoWantedMe(userId).then(response => {
                    console.log("getAllVoeux : ");
                    console.log(response.data);
                    this.companies = response.data;
                })
            }
        }
    }
</script>

<style scoped>

</style>