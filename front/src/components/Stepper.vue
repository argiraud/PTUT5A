<template>
  <v-stepper v-model="currentStep">
    <v-stepper-header>
      <v-stepper-step :complete="currentStep > 1" step="1"> Création de compte </v-stepper-step>
      <v-divider></v-divider>
      <v-stepper-step :complete="currentStep > 2" step="2"> Dépôt de documents </v-stepper-step>
      <v-divider></v-divider>
      <v-stepper-step :complete="currentStep > 3" step="3"> Saisie des voeux </v-stepper-step>
    </v-stepper-header>
    <v-stepper-content step="1" currentStep=1>
      <AdminDonnee @message-sent="updateMes"></AdminDonnee>
    </v-stepper-content>
  </v-stepper>
</template>

<script>
import AdminDonnee from '@/views/AdmiDonnee'

export default {
name: "Stepper",
  data () {
    return {
      currentStep: 1,
    }
  },
  components :{
    AdminDonnee
  },
  methods :{
    updateMes(){
      this.currentStep = 2;
    }
  }
}
</script>

<style scoped>

</style>