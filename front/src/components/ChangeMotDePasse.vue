<template>
    <div class="text-center">
        <v-dialog
            v-model="isPopUpShown"
            width="500">

            <template v-slot:activator="{ on, attrs }">
                <v-btn
                        rounded outlined color="teal accent-3"
                        v-bind="attrs"
                        v-on="on"
                >
                    Modifier le mot de passe
                </v-btn>
            </template>
            <v-card class="elevation-12" v-if="isPopUpShown" style="padding: 2%">
                <v-window>
                    <v-window-item>
                        <v-row>
                            <v-col cols="12" md="12">
                                <v-btn
                                        rounded outlined color="red"
                                        style="float: right !important;"
                                        @click="HidePopUp"
                                >X</v-btn>
                                <v-form ref="form"
                                        v-model="valid"
                                        lazy-validation>
                                    <v-text-field
                                            style="margin-top: 5%"
                                            v-model="mdp"
                                            id="mdp"
                                            label="Nouveau mot de passe"
                                            prepend-inner-icon="lock"
                                            type="password"
                                            color="teal accent-3"
                                            required
                                            :rules="mdpRules"/>
                                    <v-text-field
                                            v-model="mdpIdentique"
                                            id="mdpIdentique"
                                            label="Confirmation"
                                            prepend-inner-icon="lock"
                                            type="password"
                                            color="teal accent-3"
                                            required
                                            :rules="mdpConfirmationRule1.concat(mdpConfirmationRule2)"/>
                                </v-form>
                                <p></p>
                                <div class="text-center mt-n5" style="margin-top: 2%">
                                    <v-btn
                                            :disabled="!valid"
                                            rounded outlined color="teal accent-3"
                                            @click="SaveUser"
                                    >Valider</v-btn>
                                </div>
                            </v-col>
                        </v-row>
                    </v-window-item>
                </v-window>
            </v-card>
        </v-dialog>
    </div>
</template>

<script>
    import StudentDataService from "@/service/StudentDataService";
    import {mapState} from 'vuex';

    export default {
        name: "ChangeMotDePasse",
        data: () => ({
            valid: false,
            isPopUpShown: '',
            mdp: '',
            mdpIdentique: '',
            mdpRules: [
                v => !!v || 'Le mot de passe est requis',
            ],
            mdpConfirmationRule1: [
                v => !!v || 'La confirmation du mot de passe est requise',
            ],
        }),
        methods: {
            HidePopUp(){
                this.isPopUpShown = false;
            },
            SaveUser(){
                let mdp = document.getElementById('mdp').value.toString();
                if(this.$refs.form.validate()){
                    console.log("Je sauvegarde le mot de passe")
                    StudentDataService.setNewUserPassword(mdp).then(response => {
                        console.log("response : ")
                        console.log(response)
                        switch (response.status) {
                         case 200:
                             this.$emit('newpassword-ok',{
                                 message: "Votre mot de passe a bien été modifié.",
                             });
                             this.HidePopUp()
                             break;
                        case 401 :
                            this.$emit('error-newpassword',{
                                message: "Vous devez être identifié pour changer votre mot de passe.",
                            });
                            break;
                        case 400 :
                            this.$emit('error-newpassword',{
                                message: "Votre token est altéré ou périmé, reconnectez-vous.",
                            });
                            break;
                        }
                    } )
                }
            },
            mdpConfirmationRule2(value){
                return value == this.mdp || "Les deux mots de passe ne sont pas identiques";
            }
        },
        computed: {
            ...mapState({
                isConnected: 'isConnected',
                currentUser: 'currentUser',
            })
        }
    }
</script>

<style scoped>

</style>