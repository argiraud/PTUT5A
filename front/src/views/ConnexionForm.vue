<template>
<div class="container">
    <div class="row">
            <SelectUserType @change-user="updateUser"/>
    </div>
    <form>
            <div class="row">
                <label for="Identifiant">Identifiant de connexion</label>
                <input id="Identifiant" placeholder="Email">
            </div>
            <div class="row">
                <label for="Mdp">Mot de passe</label>
                <input id="Mdp" placeholder="Mot de passe">
            </div>
            <div class="row">
            <input type="submit" @click="SignIn" value="Valider">
            <router-link to="/Register">S'inscrire</router-link>
            </div>
        </form>  
</div>
</template>

<script>
import SelectUserType from "./SelectUserType"

export default {
    name: 'ConnexionForm',
    components: {
        SelectUserType
    },
    methods: {
        updateUser(payload){
            this.isEntreprise = payload.UserType;
        },
        SignIn(){
            if(this.isEntreprise){
                //Methode de connexion entreprise
            }else{
                //Methode de connexion candidat
            }
        }
     },
    data: () => ({
        isEntreprise: Boolean
    })
}
</script>