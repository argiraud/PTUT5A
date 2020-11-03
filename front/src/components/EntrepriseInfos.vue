<template>
    <v-form
            ref="form"
            v-model="valid"
            lazy-validation
    >
        <v-text-field
                v-model="name"
                :rules="nameRules"
                label="Nom de l'entreprise"
                required
                type="text"
                color="teal accent-3"
                prepend-inner-icon="person"
        ></v-text-field>

        <v-text-field
                v-model="email"
                :rules="emailRules"
                prepend-inner-icon="email"
                label="E-mail"
                required
        ></v-text-field>

        <v-text-field
                id="mdpInscription"
                v-model="mdp"
                label="Mot de passe"
                prepend-inner-icon="lock"
                type="password"
                required
                :rules="mdpRules"
                color="teal accent-3"/>

        <div class="text-center mt-n5">
            <v-btn rounded outlined color="teal accent-3" @click="SignUp" dark :disabled="!valid">S'inscrire</v-btn>
        </div>

    </v-form>
</template>

<script>
    export default {
        name: 'EntrepriseInfos',
        data: () => ({
            valid: true,
            name: '',
            nameRules: [
                v => !!v || 'Le nom et le prénom sont requis',
                v => (v && v.length <= 20) || 'Le nom ou le prénom doivent faire moins de 20 charactères',
            ],
            email: '',
            emailRules: [
                v => !!v || 'L\'addresse mail est requise',
                v => /.+@.+\..+/.test(v) || 'L\'addresse mail doit être valide',
            ],
            mdp: '',
            mdpRules: [
                v => !!v || 'Le mot de passe est requis',
            ],
        }),

        methods: {
            SignUp(){
                if(this.$refs.form.validate()){
                    //Methode de connexion entreprise
                }
            }
        },
    }
</script>