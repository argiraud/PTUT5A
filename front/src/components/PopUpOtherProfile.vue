<template>
    <v-dialog
            v-model="dialog"
            width="500">

        <template v-slot:activator="{ on, attrs }">
            <v-btn
                    rounded outlined color="teal accent-3"
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
                        color="teal accent-3"
                        prepend-inner-icon="person"
                ></v-text-field>

                <v-text-field
                        :disabled="!currentUser.IsAdmin"
                        v-model="popUpUser.Firstname"
                        :rules="nameRules"
                        id="firstname"
                        label="Prénom"
                        prepend-inner-icon="person"
                        type="text"
                        color="teal accent-3"
                        required
                ></v-text-field>
                <v-text-field
                        :disabled="!currentUser.IsAdmin"
                        v-model="popUpUser.StudentNumber"
                        id="studentNumber"
                        :counter="8"
                        :rules="studentNumberRules"
                        type="number"
                        label="N° Etudiant"
                        required
                        prepend-inner-icon="confirmation_number"
                ></v-text-field>
                <v-menu
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
                        required
                ></v-text-field>
                <v-select
                        :disabled="!currentUser.IsAdmin"
                        id="comboStatus"
                        label="Renseignez votre status actuel"
                        small-chips
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
                            color="teal accent-3"
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
            </div>
        </v-card>
    </v-dialog>
</template>

<script>
import StudentDataService from "@/service/StudentDataService";
import {mapState} from "vuex";
export default {
    name: "PopUpOtherProfile",
    props: ['idUserToDisplay'],
    data: () => ({
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
        StudentDataService.getUserById(this.idUserToDisplay).then(response => {
            switch (response.status) {
                case 200 :
                    console.log("case 200")
                    console.log("data profile : ")
                    console.log(response.data)
                    this.setpopUpUser(response.data);
                    this.getAllVoeux();
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
        setSnackbarError(payload){
            this.snackbarError = true;
            this.Erreur = payload.message;
        },
        setSnackbarSuccess(payload){
            this.snackbarSuccess = true;
            this.Message = payload.message;
        },
        getAllVoeux(){
            let userId = this.currentUser.Id;
            StudentDataService.CompaniesWhoWantedMe(userId).then(response => {
                this.companies = response.data;
            })
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