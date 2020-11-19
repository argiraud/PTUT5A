<template>
    <v-container>
        <v-form>
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
                            id="birthDate"
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
                <v-btn style="margin-top: 5%" rounded outlined color="teal accent-3" @click="Save" dark :disabled="!valid">Enregistrer</v-btn>
            </div>
        </v-form>
    </v-container>
</template>

<script>
    export default {
        name: "ProfileCandidat",
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
        methods:{
            save(){
                console.log("Je sauvegarde les données en base");
            },
            beforeCreate(){
                let url = "https://api.polyrecrute.tk/auth/userdetails?";
                fetch(url,{
                    method: "GET",
                    headers:{
                        "accept": "application/json"
                    }
                }).then(response => {
                    console.log("response : ")
                    console.log(response)
                    switch (response.status) {
                        case 200 :
                            console.log("case 200")
                            response.json().then(respjson => {
                                console.log("data : ")
                                console.log(respjson)

                                this.$store.commit('SET_SESSION_FROM_JSON', respjson);
                                this.$store.commit('CONNEXION_MANAGEMENT', true);

                                if(respjson.presentation == null || respjson.presentation == ""){
                                    this.$router.push("/creationCompte");
                                }else{
                                    this.$router.push("/home");
                                }
                            })
                            break;
                        case 404 :
                            this.Erreur = 'Email ou mot de passe incorrecte !';
                            this.snackbarError = true;
                            break;
                        case 401 :
                            this.Erreur = 'Email ou mot de passe incorrecte !';
                            this.snackbarError = true;
                            break;
                        case 500 :
                            this.Erreur = 'Problème du serveur : erreur 500';
                            this.snackbarError = true;
                            break;

                    }
                }).catch(err => {
                    console.log("erreur : " + err);
                });
            },
            data: () => ({
                userInfos: {
                    name: '',
                    email: '',
                    birthDate: '',
                    presentation: ''
                }
            })
        },

    }
</script>

<style scoped>

</style>