<template>
    <v-container class="fill-height" fluid>
        <p>{{$route.params.id}}</p>
        <ProfileCandidat v-if="currentUser.RoleId == '1'"></ProfileCandidat>
        <ProfileEntreprise v-if="currentUser.RoleId == '2'"></ProfileEntreprise>
    </v-container>
</template>

<script>
    import ProfileCandidat from "../components/ProfileCandidat";
    import ProfileEntreprise from "../components/ProfileEntreprise";
    import StudentDataService from "@/service/StudentDataService";
    import {mapState} from "vuex";
    export default {
        name: "Profil",
        components: {ProfileEntreprise, ProfileCandidat},
        data: () => ({
            currentUserRole: ''
        }),
        computed: {
            ...mapState({
                currentUser: 'currentUser'
            }),
        },
        mounted() {
            //Prévoir le faite qu'on puisse rafraichir la page
            // ==> User/détails en passant par le token en session pour récupérer notre currentUSer
            console.log("BeforeCreate Profile");
            let userId = this.currentUser.Id;
            if(typeof(this.$route.params.id ) != "undefined" && this.$route.params.id != null){
                userId = this.$route.params.id;
            }
            StudentDataService.getConnectedUser(userId).then(response => {
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
        }
    }
</script>

<style scoped>

</style>