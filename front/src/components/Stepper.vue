<template>
  <v-stepper v-model="currentStep">
    <v-stepper-header>
      <v-stepper-step :complete="currentStep > 1" step="1"> Présentation </v-stepper-step>
      <v-divider></v-divider>
      <v-stepper-step :complete="currentStep > 2" step="2"> Dépôt de documents </v-stepper-step>
      <v-divider></v-divider>
      <v-stepper-step :complete="currentStep > 3" step="3"> Saisie des voeux </v-stepper-step>
    </v-stepper-header>
    <v-stepper-content step="1" currentStep=1>
      <Presentation @step1-finish="updateMes"></Presentation>
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
import SaisieVoeux from '@/views/SaisieVoeuxForm'
import DepotOffre from "@/views/DepotOffreForm";
import Presentation from "@/components/Presentation";

export default {
name: "Stepper",
  data () {
    return {
      currentStep: 1,
    }
  },
  components :{
    Presentation,
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