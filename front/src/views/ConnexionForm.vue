<template>
<v-container class="fill-height" fluid>
    <v-row align="center" justify="center">
        <h3 class="text--center display-1 teal--text text--accent-3">Type de compte</h3>
    </v-row>
    <v-row align="center" justify="center">
        <v-btn id="btnEntreprise" v-bind:outlined="!isEntreprise" rounded color="black" dark @click="changeUserType">Entreprise</v-btn>
        <v-btn id="btnCandidat" v-bind:outlined="isEntreprise" rounded color="black" dark @click="changeUserType">Candidat</v-btn>
    </v-row>
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
                                <v-form>
                                    <v-text-field
                                        label="Email"
                                        name="Email"
                                        prepend-inner-icon="email"
                                        type="text"
                                        color="teal accent-3"/>
                                    <v-text-field
                                            id="mdpConnexion"
                                            label="Mot de passe"
                                            name="Mot de passe"
                                            prepend-inner-icon="lock"
                                            type="password"
                                            color="teal accent-3"/>
                                </v-form>
                                <h3 class="text-center mt-3">Mot de passe oublié ?</h3>
                            </v-card-text>
                            <div class="text-center mt-3">
                                <v-btn rounded color="teal accent-3" dark>CONNEXION</v-btn>
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
                                <h1 class="text--center display-2 teal--text text--accent-3">Créer un compte</h1>
                                <div class="text-center mt-4">
                                    <v-btn class="mx-2" fab color="black" outlined>
                                        <v-icon>
                                            fab fa-facebook
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
                                <h4 class="text-center mt-4">Vérifiez votre adresse mail pour finaliser l'inscription</h4>
                                <EntrepriseInfos v-if="isEntreprise"></EntrepriseInfos>
                                <CandidatInfos v-if="!isEntreprise"></CandidatInfos>
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
            this.$refs.form.validate()
            if(this.isEntreprise){
                //Methode de connexion entreprise
            }else{
                //Methode de connexion candidat
            }
        }
     },
    data: () => ({
        step: 1,
        isEntreprise: true
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