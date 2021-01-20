<template>
    <div>
        <v-btn
                rounded outlined color="teal accent-3"
                @click="ShowPopUp"
                v-if="!isPopUpShown"
        >Modifier le mot de passe</v-btn>

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
    </div>
</template>

<script>
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
            ShowPopUp(){
                this.isPopUpShown = true;
            },
            HidePopUp(){
                this.isPopUpShown = false;
            },
            SaveUser(){
                if(this.$refs.form.validate()){
                    console.log("Je sauvegarde le mot de passe")
                }
            },
            mdpConfirmationRule2(value){
                return value == this.mdp || "Les deux mots de passe ne sont pas identiques";
            }
        }
    }
</script>

<style scoped>

</style>