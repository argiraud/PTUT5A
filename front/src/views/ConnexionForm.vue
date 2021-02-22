<template>
<v-container class="fill-height" fluid>
    <v-snackbar v-model="snackbarError" top color="red">
        {{Erreur}}
    </v-snackbar>

    <v-snackbar v-model="snackbarSuccess" top color="success">
        {{Message}}
    </v-snackbar>
    <v-row align="center" justify="center">
        <v-card class="elevation-12">
            <v-window>
                <v-window-item v-if="!isConnected">
                    <v-row>
                        <v-col cols="12" md="12">
                            <v-card-text class="mt-12">
                                <h1 class="text-center display-2 teal--text text--accent-3">Se Connecter à PolyRecrute</h1>
                                <div class="text-center" mt-4>
                                    <v-btn class="mx-2" fab color="black" outlined>
                                        <v-icon>
                                            fab fa-facebook-f
                                        </v-icon>
                                    </v-btn>
                                    <v-btn class="mx-2" fab color="black" outlined>
                                        <v-icon>
                                            fab fa-google-plus-g
                                        </v-icon>
                                    </v-btn>
                                    <v-btn class="mx-2" fab color="black" outlined>
                                        <v-icon>
                                            fab fa-linkedin-in
                                        </v-icon>
                                    </v-btn>
                                </div>
                                <h4 class="text-center mlt-4">Vérifez votre Email pour compléter l'inscription</h4>
                                <v-form ref="formconnexion"
                                        v-model="valid"
                                        lazy-validation>
                                    <v-text-field
                                            v-model="email"
                                            id="emailConnexion"
                                            label="Email"
                                            prepend-inner-icon="email"
                                            type="text"
                                            color="teal accent-3"
                                            required
                                            :rules="emailRules"/>
                                    <v-text-field
                                            v-model="mdpConnexion"
                                            id="mdpConnexion"
                                            label="Mot de passe"
                                            prepend-inner-icon="lock"
                                            type="password"
                                            color="teal accent-3"
                                            required
                                            :rules="mdpRules"/>
                                </v-form>
                                <h3 class="text-center mt-3">Mot de passe oublié ?</h3>
                            </v-card-text>
                            <div class="text-center mt-3">
                                <v-btn rounded color="teal accent-3" dark @click="SignIn">CONNEXION</v-btn>
                            </div>
                        </v-col>
                      <!--Ancienne colonne pour switcher entre connexion et inscirption
                        <v-col cols="12" md="4" class="teal accent-3">
                            <v-card-text class="white--text mt-12">
                                <h1 class="text-center display-1">Bienvenue !</h1>
                                <h5 class="text-center">Saisissez vos informations personnelles et rejoignez-nous !</h5>
                            </v-card-text>
                            <div class="text-center">
                                <v-btn rounded outlined="" dark @click="step++">S'inscrire</v-btn>
                            </div>
                        </v-col>-->
                    </v-row>
                </v-window-item>
                <v-window-item v-if="isConnected && currentUser.IsAdmin">
                    <v-row class="fill-height">
                        <!--Ancienne colonne pour switcher entre connexion et inscirption
                        <v-col cols="12" md="4" class="teal accent-3">
                            <v-card-text class="white--text mt-12">
                                <h1 class="text-center display-1">De retour parmis nous ?</h1>
                                <h5 class="text-center">Pour vous connecter, renseigner vos informations personnelles</h5>
                            </v-card-text>
                            <div class="text-center">
                                <v-btn rounded outlined dark @click="step--">Se connecter</v-btn>
                            </div>
                        </v-col>-->
                        <v-col cols="12" md="12">
                            <v-card-text class="mt-12">
                                <h1 class="text-center display-2 teal--text text--accent-3">Créer un compte</h1>
                                <div class="text-center mt-4">
                                    <v-btn id="btnEntreprise" v-bind:outlined="!isEntreprise" rounded color="black" dark @click="changeUserType">Entreprise</v-btn>
                                    <v-btn id="btnCandidat" v-bind:outlined="isEntreprise" rounded color="black" dark @click="changeUserType">Candidat</v-btn>
                                </div>
                                <h4 class="text-center mt-4">Vérifiez votre adresse mail pour finaliser l'inscription</h4>
                                <EntrepriseInfos v-if="isEntreprise" @erreur-inscription="setSnackbarError" @inscription-ok="setSnackbarSuccess"></EntrepriseInfos>
                                <CandidatInfos v-if="!isEntreprise" @erreur-inscription="setSnackbarError" @inscription-ok="setSnackbarSuccess"></CandidatInfos>
                            </v-card-text>
                        </v-col>
                    </v-row>
                </v-window-item>
                <v-window-item v-if="isConnected && !currentUser.IsAdmin">
                    <v-row>
                      <v-col cols="12" md="12">
                        <v-card-text class="mt-12">
                          <h1 class="text-center display-2 teal--text text--accent-3">Vous êtes déjà connecté !</h1>
                          <br/>
                          <br/>
                          <h4 class="text-center mlt-4">Déconnectez-vous en utilisant le bouton de déconnexion en haut à droite pour changer de compte.</h4>
                        </v-card-text>
                      </v-col>
                    </v-row>
                </v-window-item>
            </v-window>
        </v-card>
    </v-row>
</v-container>
</template>

<script>
import EntrepriseInfos from "@/components/EntrepriseInfos"
import CandidatInfos from "@/components/CandidatInfos"
import Authentification from "@/service/Authentification";
import {mapState} from "vuex";

export default {
    name: 'ConnexionForm',
    components: {
        EntrepriseInfos,
        CandidatInfos
    },
    methods: {
        updateUser(payload){
            this.isEntreprise = payload.UserType;
        },
        changeUserType(){
            this.isEntreprise = !this.isEntreprise;
        },
        SignIn(){
            if(this.$refs.formconnexion.validate()){
                this.APISignIn();
            }
        },
        APISignIn(){
            let email = document.getElementById('emailConnexion').value.toString();
            let mdp = document.getElementById('mdpConnexion').value.toString();
            Authentification.signin(email, mdp).then(response => {
                console.log("response : ")
                console.log(response)
                switch (response.status) {
                    case 200 :
                        console.log("case 200")
                        console.log("data : ")
                        console.log(response.data)

                        window.sessionStorage.setItem("UserToken", response.data.tokenType + ' ' + response.data.tokenJWT);
                        this.$store.commit('SET_CURRENTUSER_FROM_JSON', response.data);
                        this.$store.commit('CONNEXION_MANAGEMENT', true);

                        if(response.data.presentation == null || response.data.presentation === ""){
                            this.$router.push("/creationCompte");
                        }else{
                            this.$router.push("/home");
                        }
                        break;

                }
            }).catch(err => {
                var payload
                switch(err.response.status){
                    case 404 :
                        payload = {
                            "message" : 'Email ou mot de passe incorrecte !'
                        }
                        this.setSnackbarError(payload)
                        break;
                    case 401 :
                        payload = {
                            "message" : 'Email ou mot de passe incorrecte !'
                        }
                        this.setSnackbarError(payload)
                        break;
                    case 500 :
                        payload = {
                            "message" : 'Problème du serveur : erreur 500'
                        }
                        this.setSnackbarError(payload)
                        break;
                }
                console.log("erreur : " + err);
            });
        },
        setSnackbarError(payload){
            this.snackbarError = true;
            this.Erreur = payload.message;
        },
        setSnackbarSuccess(payload){
            this.snackbarSuccess = true;
            this.Message = payload.message;
        }
     },
    data: () => ({
        valid: true,
        snackbarSuccess : false,
        snackbarError : false,
        Erreur: '',
        Message: '',
        isEntreprise: true,
        email: '',
        emailRules: [
            v => !!v || 'L\'addresse mail est requise',
            v => /.+@.+\..+/.test(v) || 'L\'addresse mail doit être valide',
        ],
        mdpConnexion: '',
        mdpRules: [
          v => !!v || 'Le mot de passe est requis',
        ]
    }),
    computed: {
      ...mapState({
        currentUser: 'currentUser',
        isConnected: 'isConnected'
      }),
    },
    props: {
        source: String
    }
}
</script>
<style>
    #btnEntreprise{
        margin-right:5px;
    }
    #btnCandidat{
        margin-left:5px;
    }
</style>