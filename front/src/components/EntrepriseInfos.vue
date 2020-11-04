<template>
    <v-form
            ref="form"
            v-model="valid"
            lazy-validation
    >
        <v-text-field
                v-model="name"
                id="name"
                :rules="nameRules"
                label="Nom de l'entreprise"
                required
                type="text"
                color="teal accent-3"
                prepend-inner-icon="person"
        ></v-text-field>

        <v-text-field
                v-model="email"
                id="emailInscription"
                :rules="emailRules"
                prepend-inner-icon="email"
                label="E-mail"
                required
        ></v-text-field>

        <v-text-field
                v-model="mdp"
                id="mdpInscription"
                label="Mot de passe"
                prepend-inner-icon="lock"
                type="password"
                required
                :rules="mdpRules"
                color="teal accent-3"/>

        <div class="text-center mt-n5">
            <v-btn style="margin-top: 5%" rounded outlined color="teal accent-3" @click="SignUp" dark :disabled="!valid">S'inscrire</v-btn>
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
                    this.APISignUp();
                }
            },
            APISignUp(){
                const user = {
                    name: document.getElementById('name').value.toString(),
                    email: document.getElementById('emailInscription').value.toString(),
                    password: document.getElementById('mdpInscription').value.toString(),
                };
                const options = {
                    method: 'POST',
                    body: JSON.stringify(user),
                    headers: {
                        "accept": "*/*",
                        "Content-Type": "application/json"
                    }
                };
                fetch("https://api.polyrecrute.tk/auth/company/signup", options).then(response => {
                    console.log(response);
                    return response.json();
                }).then(data => {
                    console.log(data);
                }).catch(err => {
                        console.log(err);
                    });
            }
        },
    }
</script>