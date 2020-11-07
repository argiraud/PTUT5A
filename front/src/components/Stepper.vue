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
      <AdminDonnee @step1-finish="updateMes"></AdminDonnee>
    </v-stepper-content>
    <v-stepper-content step="2" currentStep=2>
      <DepotOffre @step2-finish="updateMes2"></DepotOffre>
    </v-stepper-content>
    <v-stepper-content step="3" currentStep=3>
      <SaisieVoeux @step3-finish="updateMes3"></SaisieVoeux>
    </v-stepper-content>

  </v-stepper>
</template>

<script>
import AdminDonnee from '@/views/AdmiDonneeForm'
import SaisieVoeux from '@/views/SaisieVoeuxForm'
import DepotOffre from "@/views/DepotOffreForm";

export default {
name: "Stepper",
  data () {
    return {
      currentStep: 1,
    }
  },
  components :{
    AdminDonnee,
    SaisieVoeux,
    DepotOffre
  },
  methods :{
    updateMes(){
      this.currentStep = 2;
    },
    updateMes2(){
      this.currentStep = 3;
    },
    updateMes3(){
      this.currentStep = 1;
    }
  }
}
</script>

<style scoped>

</style>