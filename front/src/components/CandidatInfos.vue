<template>
        <v-form
            ref="form"
            v-model="valid"
            lazy-validation
        >
            <v-text-field
                    v-model="name"
                    :rules="nameRules"
                    id="name"
                    label="Nom"
                    required
                    type="text"
                    color="teal accent-3"
                    prepend-inner-icon="person"
            ></v-text-field>

            <v-text-field
                    v-model="firstname"
                    :rules="nameRules"
                    id="firstname"
                    label="Prénom"
                    prepend-inner-icon="person"
                    type="text"
                    color="teal accent-3"
                    required
            ></v-text-field>
            <v-text-field
                    v-model="studentNumber"
                    id="studentNumber"
                    :counter="8"
                    :rules="studentNumberRules"
                    type="number"
                    label="N° Etudiant"
                    required
                    prepend-inner-icon="confirmation_number"
            ></v-text-field>
            <v-menu
                    ref="menu"
                    v-model="menu"
                    :close-on-content-click="false"
                    :nudge-right="40"
                    lazy
                    transition="scale-transition"
                    offset-y
                    full-width
                    min-width="290px"
                    prepend-inner-icon="calendar_today"
            >
                <template v-slot:activator="{ on }">
                    <v-text-field
                            v-model="date"
                            label="Date de naissance"
                            readonly
                            v-on="on"
                            prepend-inner-icon="calendar_today"
                    ></v-text-field>
                </template>
                <v-date-picker
                        ref="picker"
                        v-model="date"
                        hint="MM/DD/YYYY format"
                        persistent-hint
                        :max="new Date().toISOString().substr(0, 10)"
                        min="1950-01-01"
                        prepend-inner-icon="calendar_today"
                ></v-date-picker>
            </v-menu>

            <v-text-field
                    v-model="email"
                    id="emailInscription"
                    :rules="emailRules"
                    prepend-inner-icon="email"
                    type="text"
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
                <v-btn style="margin-top: 5%" rounded outlined color="teal accent-3" @click="SignUp" dark :disabled="!valid">S'inscrire</v-btn>
            </div>

        </v-form>
</template>

<script>
    export default {
        name: 'CandidatInfos',
        data: () => ({
            valid: true,
            name: '',
            nameRules: [
                v => !!v || 'Le nom et le prénom sont requis',
                v => (v && v.length <= 20) || 'Le nom ou le prénom doivent faire moins de 20 charactères',
            ],
            firstname: '',
            studentNumber : '',
            studentNumberRules: [
                //v => /.+@.+\..+/.test(v) || 'Le numéro étudiant doit être composé uniquement de chiffres',
                v => (v.length == 8 || v.length == 0) || 'Le numéro étudiant est composé de 8 chiffres'
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
            picker: new Date().toISOString().substr(0, 10),
            menu: false,
            date: null
        }),
        watch: {
            menu (val) {
                val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
            }
        },

        methods: {
            SignUp(){
                if(this.$refs.form.validate()){
                    this.APISignUp();
                }
            },
            APISignUp(){
                const user = {
                    name: document.getElementById('name').value.toString(),
                    firstname: document.getElementById('firstname').value.toString(),
                    studentNumber: document.getElementById('studentNumber').value.toString(),
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
                fetch("https://api.polyrecrute.tk/auth/user/signup", options).then(response => {
                    console.log(response);
                    switch (response.status) {
                        case 201 :
                            this.$router.push("/creationCompte");
                            break;
                        case 400 :
                            this.$emit('erreur-inscription',{
                                message: "Email, le nom  le prénom ou le numéro d'étudiant est trop long / La date de naissance est incorrecte",
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