<template>
    <v-container class="fill-height" fluid>
        <ProfileCandidat v-if="currentUserRole == '1'"></ProfileCandidat>
        <ProfileEntreprise v-if="currentUserRole == '2'"></ProfileEntreprise>
    </v-container>
</template>

<script>
    import ProfileCandidat from "../components/ProfileCandidat";
    import ProfileEntreprise from "../components/ProfileEntreprise";

    export default {
        name: "Profil",
        components: {ProfileEntreprise, ProfileCandidat},
        created() {
            this.currentUserRole = window.sessionStorage.getItem("UserRoleId")
        },
        data: () => ({
            currentUserRole: ''
        }),
    }
</script>

<style scoped>

</style>