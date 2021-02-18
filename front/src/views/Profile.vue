<template>
    <v-container class="fill-height" fluid>
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
            //On récupère l'utilisateur connecté dans tous les cas.

            StudentDataService.getConnectedUser().then(response => {
                switch (response.status) {
                    case 200 :
                        console.log("case 200")
                        console.log(response.data)
                        this.$store.commit('SET_CURRENTUSER_FROM_JSON', response.data);
                        this.$store.commit('CONNEXION_MANAGEMENT', true);
                        break;
                }
                //On récupère les informations utilisateurs qu'on veut afficher dans le profil.
                if(typeof(this.$route.params.id ) != "undefined" && this.$route.params.id != null){
                    let userId = this.$route.params.id;
                    StudentDataService.getUserById(userId).then(response => {
                        switch (response.status) {
                            case 200 :
                                console.log("case 200")
                                console.log("data profile : ")
                                console.log(response.data)
                                this.$store.commit('SET_PROFILEUSERINFOS_FROM_JSON', response.data);
                                break;
                        }
                    }).catch(err => {
                        console.log("erreur : " + err);
                    });
                }else{
                    //On récupère les données du currentUser pour les afficher.
                    let formatedUser = {
                        "id" : this.currentUser.Id,
                        "name" : this.currentUser.Name,
                        "email" : this.currentUser.Email,
                        "presentation" : this.currentUser.Presentation,
                        "roles" : [],
                        "files" : [],
                        "enable" : "",
                        "firstName" : this.currentUser.Firstname,
                        "etudiantNumber" : this.currentUser.StudentNumber,
                        "birthDate" : this.currentUser.BirthDate,
                        "status" : this.currentUser.Status,
                    };
                    this.$store.commit('SET_PROFILEUSERINFOS_FROM_JSON', formatedUser);
                }
            }).catch(err => {
                console.log("erreur : " + err);
            });



        }
    }
</script>

<style scoped>

</style>