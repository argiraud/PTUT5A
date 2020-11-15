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
            <v-window v-model="step">
                <v-window-item :value="1">
                    <v-row>
                        <v-col cols="12" md="8">
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
                        <v-col cols="12" md="4" class="teal accent-3">
                            <v-card-text class="white--text mt-12">
                                <h1 class="text-center display-1">Bienvenue !</h1>
                                <h5 class="text-center">Saisissez vos informations personnelles et rejoignez-nous !</h5>
                            </v-card-text>
                            <div class="text-center">
                                <v-btn rounded outlined="" dark @click="step++">S'inscrire</v-btn>
                            </div>
                        </v-col>
                    </v-row>
                </v-window-item>
                <v-window-item :value="2">
                    <v-row class="fill-height">
                        <v-col cols="12" md="4" class="teal accent-3">
                            <v-card-text class="white--text mt-12">
                                <h1 class="text-center display-1">De retour parmis nous ?</h1>
                                <h5 class="text-center">Pour vous connecter, renseigner vos informations personnelles</h5>
                            </v-card-text>
                            <div class="text-center">
                                <v-btn rounded outlined dark @click="step--">Se connecter</v-btn>
                            </div>
                        </v-col>
                        <v-col cols="12" md="8">
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
            </v-window>
        </v-card>
    </v-row>
</v-container>
</template>

<script>
import EntrepriseInfos from "@/components/EntrepriseInfos"
import CandidatInfos from "@/components/CandidatInfos"

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
            let url = "https://api.polyrecrute.tk/auth/signin?email="+email+"&password="+mdp;
            fetch(url,{
                method: "GET",
                headers:{
                    "accept": "application/json"
                }
            }).then(response => {
                console.log("response : ")
                console.log(response)
                switch (response.status) {
                    case 200 :
                        response.json().then(respjson => {
                            console.log("data : ")
                            console.log(respjson)
                            window.sessionStorage.setItem("UserId",respjson.id);
                            window.sessionStorage.setItem("UserName",respjson.name);
                            window.sessionStorage.setItem("UserRoleId",respjson.roles[0].idRole);
                            window.sessionStorage.setItem("UserToken", respjson.tokenJWT);
                            this.$store.commit('CONNEXION_MANAGEMENT', true);
                            this.$store.commit('SET_CURRENTUSERNAME', window.sessionStorage.getItem("UserName"));
                            if(respjson.presentation == null){
                                this.$router.push("/creationCompte");
                            }else{
                                this.$router.push("/home");
                            }

                        })
                        break;
                    case 404 :
                        this.Erreur = 'Email ou mot de passe incorrecte !';
                        this.snackbarError = true;
                        break;
                    case 401 :
                        this.Erreur = 'Email ou mot de passe incorrecte !';
                        this.snackbarError = true;
                        break;
                    case 500 :
                        this.Erreur = 'Problème du serveur : erreur 500';
                        this.snackbarError = true;
                        break;

                }
            }).catch(err => {
                console.log("erreur : " + err);
            });
        },
        setSnackbarError(payload){
            this.snackbarError = true;
            this.Erreur = payload.message;
        },
        setSnackbarSuccess(payload){
            this.step--;
            this.snackbarSuccess = true;
            this.Message = payload.message;
        }
     },
    data: () => ({
        valid: true,
        step: 1,
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