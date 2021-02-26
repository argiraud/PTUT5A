<template>
    <v-dialog
            v-model="dialog"
            width="80%">

        <template v-slot:activator="{ on, attrs }">
            <v-btn
                    rounded outlined color="#009BDD"
                    v-bind="attrs"
                    v-on="on"
            >
                Voir le profil
            </v-btn>
        </template>
        <v-card class="elevation-12" style="padding: 2%">
            <v-form v-model="valid" lazy-validation ref="form">
                <v-text-field
                        :disabled="!currentUser.IsAdmin"
                        v-model="popUpUser.Name"
                        :rules="nameRules"
                        id="name"
                        label="Nom"
                        required
                        type="text"
                        color="#009BDD"
                        prepend-inner-icon="person"
                ></v-text-field>

                <v-text-field
                        v-if="popUpUser.RoleId == 1"
                        :disabled="!currentUser.IsAdmin"
                        v-model="popUpUser.Firstname"
                        :rules="nameRules"
                        id="firstname"
                        label="Prénom"
                        prepend-inner-icon="person"
                        type="text"
                        color="#009BDD"
                        required
                ></v-text-field>
                <v-text-field
                        v-if="popUpUser.RoleId == 1"
                        :disabled="!currentUser.IsAdmin"
                        v-model="popUpUser.StudentNumber"
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
                        v-if="popUpUser.RoleId == 1"
                        :disabled="!currentUser.IsAdmin"
                        ref="menu"
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
                                :disabled="!currentUser.IsAdmin"
                                id="birthDate"
                                v-model="popUpUser.BirthDate"
                                label="Date de naissance"
                                readonly
                                color="#009BDD"
                                v-on="on"
                                prepend-inner-icon="calendar_today"
                        ></v-text-field>
                    </template>
                    <v-date-picker
                            ref="picker"
                            v-model="popUpUser.BirthDate"
                            hint="MM/DD/YYYY format"
                            persistent-hint
                            :max="new Date().toISOString().substr(0, 10)"
                            min="1950-01-01"
                            prepend-inner-icon="calendar_today"
                    ></v-date-picker>
                </v-menu>

                <v-text-field
                        :disabled="!currentUser.IsAdmin"
                        v-model="popUpUser.Email"
                        :rules="emailRules"
                        id="emailInscription"
                        prepend-inner-icon="email"
                        type="text"
                        label="E-mail"
                        color="#009BDD"
                        required
                ></v-text-field>
                <v-select
                        :disabled="!currentUser.IsAdmin"
                        id="comboStatus"
                        label="Renseignez votre status actuel"
                        small-chips
                        color="#009BDD"
                        v-model="popUpUser.Status"
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
                        :disabled="!currentUser.IsAdmin"
                        name="presentation"
                        v-model="popUpUser.Presentation"
                        id="presentation"
                        prepend-inner-icon="create"
                        color="#009BDD"
                        type="text"
                        label="Présentation"
                ></v-textarea>
                <div :hidden="!currentUser.IsAdmin">
                    <ChangeMotDePasse @error-newpassword="setSnackbarError" @newpassword-ok="setSnackbarSuccess" ></ChangeMotDePasse>
                </div>
                <div class="text-center mt-n5" :hidden="!currentUser.IsAdmin">
                    <v-btn
                            style="margin-top: 5%"
                            rounded outlined
                            color="#009BDD"
                            @click="save"
                            :disabled="!valid || !currentUser.IsAdmin">Enregistrer</v-btn>
                </div>
            </v-form>
            <br/>
            <div :hidden="!currentUser.IsAdmin">
            <v-card
                    elevation="10"
            >
                <v-card-title>Entreprises me comptant dans leurs voeux</v-card-title>
                <v-data-table
                        :headers="headers"
                        :items="companies"
                        :items-per-page="5"
                >
                </v-data-table>
            </v-card>
            <br />
            <br />
            </div>
                <v-card
                        elevation="10"
                        v-if="popUpUser.RoleId == 1"
                >
                    <v-card-title>Mon document descriptif</v-card-title>
                    <v-data-table
                            :headers="headers2"
                            :items="candidatures"
                            item-key="name"
                            show-expand
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
                    v-if="popUpUser.RoleId == 2"
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
                                <v-btn rounded outlined color="#009BDD" class="ma-2" @click="openItemById(item.files[0].idFile, item.files[0].name)">Télécharger</v-btn>
                            </td>
                        </tr>
                    </template>
                </v-data-table>
            </v-card>

        </v-card>
    </v-dialog>
</template>

<script>
import StudentDataService from "@/service/StudentDataService";
import FileDataService from "@/service/FileDataService";
import {mapState} from "vuex";
import ChangeMotDePasse from "@/components/ChangeMotDePasse"
import CompanyDataService from "@/service/CompanyDataService";
export default {
    components: {ChangeMotDePasse, },
    name: "PopUpOtherProfile",
    props: ['idUserToDisplay'],
    data: () => ({
        singleExpand: true,
        dialog : false,
        popUpUser: {
            "Id" : "",
            "Name" : "",
            "Email" : "",
            "Presentation" : "",
            "Roles" : [],
            "Files" : [],
            "Enable" : "",
            "FirstName" : "",
            "StudentNumber" : "",
            "BirthDate" : "",
            "Status" : "",
            "RoleId" : "",
        },
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
            }
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
        offers: [],
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
            {
                text: 'Nom du document',
                sortable: true,
                value: 'files[0].name',
            }
        ],
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
            }
        ],
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
        valid: true,
    }),
    mounted() {
        console.log("userToDisplay" + this.idUserToDisplay);
        StudentDataService.getUserById(this.idUserToDisplay).then(response => {
            switch (response.status) {
                case 200 :
                    this.setpopUpUser(response.data);
                    this.getAllVoeux();
                    this.setGridOfferOrCandidature();
                    break;
            }
        }).catch(err => {
            console.log("erreur : " + err);
        });
    },
    methods:{
        setpopUpUser(payload){
            this.popUpUser.Id = payload.id !== undefined ? payload.id : '';
            this.popUpUser.Name = payload.name !== undefined ? payload.name : '';
            this.popUpUser.Firstname = payload.firstName !== undefined ? payload.firstName : '';
            this.popUpUser.StudentNumber = payload.etudiantNumber !== undefined ? payload.etudiantNumber : '';
            this.popUpUser.BirthDate = payload.birthDate !== undefined ? payload.birthDate : '';
            this.popUpUser.Email = payload.email !== undefined ? payload.email : '';
            this.popUpUser.Status = payload.status !== undefined ? payload.status : '';
            this.popUpUser.Presentation = payload.presentation !== undefined ? payload.presentation : '';
            if(typeof(payload.roles) !== undefined ){
                this.popUpUser.RoleId = payload.roles[0] !== undefined ? payload.roles[0].idRole : '';
            }else{
                this.popUpUser.RoleId = '';
            }
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
            StudentDataService.getUserById(this.popUpUser.Id).then(response => {
                console.log(response.data.files);
                this.candidatures = response.data.files;
            })
                    .catch(e => {
                        console.error(e);
                    })
        },
        APIGetOffer(){
            let idEntity = this.popUpUser.Id;
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
        save(){
            let userToModify = this.popUpUser;
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
        getAllVoeux(){
            //Gérer si on est une entreprise ou un etudiant
            let userId = this.popUpUser.Id;
            if(this.popUpUser.RoleId == 1){
                StudentDataService.CompaniesWhoWantedMe(userId).then(response => {
                    this.companies = response.data;
                })
            }else if (this.popUpUser.RoleId == 2){
                CompanyDataService.UsersWhoWantedMe(userId).then(response => {
                    this.companies = response.data;
                })
            }
        },
        setGridOfferOrCandidature(){
            if(this.popUpUser.RoleId == 1)
                this.APIGetCandidature();
            else if (this.popUpUser.RoleId == 2)
                this.APIGetOffer();
        }
    },
    computed: {
        ...mapState({
            currentUser: 'currentUser',
        })
    }
}
</script>

<style scoped>

</style>