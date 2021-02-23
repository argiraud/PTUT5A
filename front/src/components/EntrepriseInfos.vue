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
                color="#009BDD"
                prepend-inner-icon="person"
        ></v-text-field>

        <v-text-field
                v-model="email"
                id="emailInscription"
                :rules="emailRules"
                prepend-inner-icon="email"
                label="E-mail"
                color="#009BDD"
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
                color="#009BDD"/>

        <div class="text-center mt-n5">
            <v-btn style="margin-top: 5%" rounded outlined color="#009BDD" @click="SignUp" dark :disabled="!valid">INSCRIPTION</v-btn>
        </div>

    </v-form>
</template>

<script>
    import Authentification from "@/service/Authentification";
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
                Authentification.companySignUp(JSON.stringify(user)).then(response => {
                    console.log(response);
                    switch (response.status) {
                        case 201 :
                            this.$router.push("/Connexion");
                            this.$emit('inscription-ok',{
                                message: "Inscription réussie, veuillez vous connecter",
                            });
                            break;
                        case 400 :
                            this.$emit('erreur-inscription',{
                                message: "Email ou nom trop long",
                            });
                            break;
                        case 409 :
                            this.$emit('erreur-inscription',{
                                message: "L'Email existe déjà !",
                            });
                            break;
                        case 500 :
                            this.$emit('erreur-inscription',{
                                message: "Problème du serveur : erreur 500",
                            });
                            break;
                    }
                }).catch(err => {
                        console.log(err);
                    });
            }
        },
    }
</script>