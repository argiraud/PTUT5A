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
                    color="#009BDD"
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
                    color="#009BDD"
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
                    color="#009BDD"
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
                            color="#009BDD"
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
                    color="#009BDD"
                    required
            ></v-text-field>
            <v-select
                    id="comboStatus"
                    label="Renseignez votre status actuel"
                    small-chips
                    color="#009BDD"
                    prepend-inner-icon="donut_large"
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
                    color="#009BDD"
                    label="Présentation"
            ></v-textarea>
            <ChangeMotDePasse @error-newpassword="setSnackbarError" @newpassword-ok="setSnackbarSuccess" ></ChangeMotDePasse>

            <div class="text-center mt-n5">
                <v-btn
                        style="margin-top: 5%; color: white"
                        rounded
                        color="#009BDD"
                        @click="save"
                        :disabled="!valid">ENREGISTRER</v-btn>
            </div>
        </v-form>
        <p></p>
            <v-card
                    elevation="10"
            >
                <v-card-title>Entités me comptant dans leurs voeux</v-card-title>
                <v-data-table
                        :headers="headers"
                        :items="companies"
                        :items-per-page="5"
                >
                    <template v-slot:[`item.actions`]="{ item }">
                        <PopUpOtherProfile :id-user-to-display="item.id"></PopUpOtherProfile>
                    </template>
                </v-data-table>
            </v-card>
        <br />
        <br />
        <v-card
                v-if="currentUser.RoleId == 1"
                elevation="10"
        >
            <v-card-title>Mon document descriptif</v-card-title>
            <v-data-table
                    :headers="headers2"
                    :items="candidatures"
                    item-key="name"
                    :items-per-page="5"
                    class="elevation-1"
            >
                <template v-slot:[`item.actions`]="{ item }">
                    <v-btn rounded outlined color="#009BDD" @click="openItemById(item.idFile, item.name)">Ouvrir</v-btn>
                </template>
            </v-data-table>
        </v-card>
        <v-card
                elevation="10"
                v-if="currentUser.RoleId == 2"
        >
        <v-card-title>Mes offres</v-card-title>
        <v-data-table
                :headers="headers3"
                :items="offers"
                item-key="title"
                show-expand
                :items-per-page="5"
                :single-expand="singleExpand"
                class="elevation-1"
        >
            <template v-slot:[`item.state`]="{ item }">
                {{item.state}}
            </template>
            <template v-slot:expanded-item="{item}">
                <tr>
                    <td>
                        <v-btn rounded outlined color="#009BDD" class="ma-2" @click="openItemById(item.idFile, item.files.name)">Télécharger</v-btn>
                    </td>
                </tr>
            </template>
        </v-data-table>
        </v-card>
    </v-container>
</template>

<script>
    import ChangeMotDePasse from "@/components/ChangeMotDePasse";
    import StudentDataService from "@/service/StudentDataService";
    import FileDataService from "@/service/FileDataService";
    import {mapState} from "vuex";
    import PopUpOtherProfile from "@/components/PopUpOtherProfile";
    import CompanyDataService from "@/service/CompanyDataService";
    export default {
        name: "Profil",
        components: {PopUpOtherProfile, ChangeMotDePasse},
        data: () => ({
            singleExpand: true,
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
                    value: "id",
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
            candidatures: [],
            headers2: [
                {
                    text: 'Nom du fichier',
                    align: 'start',
                    sortable: false,
                    value: 'name',
                },
                {
                    text: '',
                    value: 'actions',
                    sortable: false
                }
            ],
            offers: [
            ],
            headers3: [
                {
                    text: 'Nom offre',
                    align: 'start',
                    sortable: true,
                    value: 'title',
                },
                {
                    text: 'Mots clés',
                    sortable: true,
                    value: 'keyWord',
                },
                {
                    text: 'Description',
                    sortable: true,
                    value: 'description',
                },
                {
                    text: 'Statut',
                    sortable: true,
                    value: 'state',
                    color: "blue",
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
        computed: {
            ...mapState({
                currentUser: 'currentUser',
            }),
        },
        beforeCreate() {
            //On récupère l'utilisateur connecté.
            StudentDataService.getConnectedUserDetails().then(response => {
                switch (response.status) {
                    case 200 :
                        console.log("case 200")
                        console.log(response.data)
                        this.$store.commit('SET_CURRENTUSER_FROM_JSON', response.data);
                        this.$store.commit('CONNEXION_MANAGEMENT', true);
                        if(this.currentUser.RoleId == 1)
                            this.APIGetCandidature();
                        else if(this.currentUser.RoleId == 2)
                            this.APIGetOffer();
                        this.getAllVoeux();
                        break;
                }
            }).catch(err => {
                console.log("erreur : " + err);
            });
        },
        refresh(){
            this.APIGetOffer();
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
            openItemById (idFile, title) {
                FileDataService.getFileById(idFile).then(response => {
                    console.log(title)
                    const url = window.URL.createObjectURL(new Blob([response.data]))
                    const link = document.createElement('a')
                    link.href = url
                    link.setAttribute('download', title)
                    document.body.appendChild(link)
                    link.click()
                }).catch(e => {
                    console.error(e);
                })
            },
            APIGetCandidature(){
                StudentDataService.getConnectedUserDetails().then(response => {
                    console.log(response.data.files);
                    this.candidatures = response.data.files;
                })
                        .catch(e => {
                            console.error(e);
                        })
            },
            APIGetOffer(){
                let idEntity = this.$store.state.currentUser.Id;
                console.log("idEntity : " + idEntity)
                CompanyDataService.get(idEntity).then(response => {

                    for(let i= 0; i < response.data.length; i++)
                    {
                        switch (response.data[i].state)
                        {
                            case "available":
                                response.data[i].state = "Disponible"
                                break
                            case "inprogress":
                                response.data[i].state = "En cours"
                                break
                            case "finished":
                                response.data[i].state = "Terminé"
                                break
                        }
                    }

                    this.offers = response.data;


                })
                        .catch(e => {
                            console.error(e);
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
                if(this.currentUser.RoleId == 1){
                    StudentDataService.CompaniesWhoWantedMe(userId).then(response => {
                        this.companies = response.data;
                    })
                }else if (this.currentUser.RoleId == 2){
                    CompanyDataService.UsersWhoWantedMe(userId).then(response => {
                        this.companies = response.data;
                    })
                }

            }
        }
    }
</script>

<style scoped>

</style>