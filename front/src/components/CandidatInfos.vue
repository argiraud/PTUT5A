<template>
    <div class="container">
        <div class="row">
                    <label for="Nom">Nom de l'entreprise</label>
                    <input id="Nom" placeholder="Nom">
        </div>

        <div class="row">
                    <label for="Identifiant">Email</label>
                    <input id="Identifiant" placeholder="Email">
        </div>

        <div class="row">
                    <label for="Mdp">Mot de passe</label>
                    <input id="Mdp" placeholder="Mot de passe">
        </div>
    </div>
</template>

<script>
export default {
    name: 'CandidatInfos',
    components: {
    },
}
</script>