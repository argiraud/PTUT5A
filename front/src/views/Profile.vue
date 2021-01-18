<template>
    <v-container class="fill-height" fluid>
        <ProfileCandidat v-if="currentUser.RoleId == '1'"></ProfileCandidat>
        <ProfileEntreprise v-if="currentUser.RoleId == '2'"></ProfileEntreprise>
    </v-container>
</template>

<script>
    import ProfileCandidat from "../components/ProfileCandidat";
    import ProfileEntreprise from "../components/ProfileEntreprise";
    import {mapState} from "vuex";
    import StudentDataService from "@/service/StudentDataService";
    export default {
        name: "Profil",
        components: {ProfileEntreprise, ProfileCandidat},
        data: () => ({
            currentUserRole: ''
        }),
        beforeCreate(){
            console.log("BeforeCreate Profile");
            StudentDataService.getConnectedUser().then(response => {
                console.log("response : ")
                console.log(response)
                switch (response.status) {
                    case 200 :
                        console.log("case 200")
                        console.log("data profile candidat : ")
                        console.log(response.data)

                        this.$store.commit('SET_CURRENTUSER_FROM_JSON', response.data);
                        this.$store.commit('CONNEXION_MANAGEMENT', true);

                        /*if(response.data.presentation == null || response.data.presentation == ""){
                            this.$router.push("/creationCompte");
                        }*/
                        break;
                }
            }).catch(err => {
                console.log("erreur : " + err);
            });
        },
        computed: {
            ...mapState({
                currentUser: 'currentUser'
            }),
        }
    }
</script>

<style scoped>

</style>