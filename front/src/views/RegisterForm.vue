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
import EntrepriseInfos from "@/components/EntrepriseInfos"
import CandidatInfos from "@/components/CandidatInfos"
export default {
    name: 'RegisterForm',
    components: {
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
        this.isEntreprise = false;
    },
    data: () => ({
        isEntreprise: Boolean
    })
}
</script>