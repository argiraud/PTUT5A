<template>
        <div class="container">
        <div class="row">
                    <label for="Nom">Nom</label>
                    <input id="Nom" placeholder="Nom">

                    <label for="Prénom">Prénom</label>
                    <input id="Prénom" placeholder="Prénom">

                    <label for="NumEtu">N° Etudiant</label>
                    <input id="NumEtu" placeholder="N° Etudiant">
                    
                    <label for="DateNaiss">Date de naissance</label>
                    <input id="DateNaiss" placeholder="Date de naissance">
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
    name: 'EntrepriseInfos',
    components: {
    },
}
</script>