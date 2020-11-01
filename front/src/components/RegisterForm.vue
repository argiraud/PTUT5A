<template>
   <div class="container">
    <div class="row">
            <SelectUserType @change-user="updateUser"/>
    </div>
    <EntrepriseInfos v-if="isEntreprise == true"/>
    <CandidatInfos v-if="isEntreprise == false"/>

    <div class="row">
            <input type="submit" @click="SignUp" value="Valider">
            <router-link to="/">Se connecter</router-link>
    </div>
</div>
</template>

<script>
import SelectUserType from "./SelectUserType"
import EntrepriseInfos from "./EntrepriseInfos"
import CandidatInfos from "./CandidatInfos"
export default {
    name: 'RegisterForm',
    components: {
        SelectUserType,
        EntrepriseInfos,
        CandidatInfos
    },
    methods: {
        updateUser(payload){
            this.message = payload.UserType;
        },
        SignUp(){
            if(this.isEntreprise){
                //Methode d'inscription entreprise
            }else{
                //Methode d'inscription candidat
            }
        }
    },
    mounted(){
        this.isEntreprise = true;
    },
    data: () => ({
        isEntreprise: Boolean
    })
}
</script>