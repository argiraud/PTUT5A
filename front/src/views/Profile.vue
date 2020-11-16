<template>
    <v-container class="fill-height" fluid>
        okok
        <v-btn @click="test"></v-btn>
        <ProfileCandidat v-if="currentUserRole == '1'"></ProfileCandidat>
        <ProfileEntreprise v-if="currentUserRole == '2'"></ProfileEntreprise>
    </v-container>
</template>

<script>
    import ProfileCandidat from "../components/ProfileCandidat";
    import ProfileEntreprise from "../components/ProfileEntreprise";
    import {mapState} from 'vuex';
    export default {
        name: "Profil",
        components: {ProfileEntreprise, ProfileCandidat},
        computed: {
            ...mapState({
                currentUserRole: 'currentUserRole',
            })
        }
    }
</script>

<style scoped>

</style>