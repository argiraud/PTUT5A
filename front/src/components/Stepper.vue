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
      <DepotOffre v-if="this.$store.state.currentUser.RoleId == 2" @step2-finish="updateMes2"></DepotOffre>
      <DepotCandidatureForm v-if="this.$store.state.currentUser.RoleId == 1" is-creation-compte="true" @step2-finish="updateMes2"></DepotCandidatureForm>
    </v-stepper-content>
    <v-stepper-content step="3" currentStep=3>
      <SaisieVoeux v-if="this.$store.state.currentUser.RoleId == 2" @step3-finish="updateMes3"></SaisieVoeux>
      <SaisieVoeuxEtudiantForm v-if="this.$store.state.currentUser.RoleId == 1" @step3-finish="updateMes3"></SaisieVoeuxEtudiantForm>
    </v-stepper-content>

  </v-stepper>
</template>

<script>
import SaisieVoeux from '@/views/SaisieVoeuxForm'
import DepotOffre from "@/views/DepotOffreForm";
import DepotCandidatureForm from "@/views/DepotCandidatureForm";
import Presentation from "@/components/Presentation";
import SaisieVoeuxEtudiantForm from "@/views/SaisieVoeuxEtudiantForm";

export default {
name: "Stepper",
  data () {
    return {
      currentStep: 1,
    }
  },
  components :{
    SaisieVoeuxEtudiantForm,
    Presentation,
    SaisieVoeux,
    DepotOffre,
    DepotCandidatureForm
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