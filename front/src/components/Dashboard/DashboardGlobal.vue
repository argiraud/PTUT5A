<template>
  <v-container>
    <v-row>
      <v-col>
        <DashboardCard color="blue" icon="mdi-account" title="Entreprises" text="28"></DashboardCard>
      </v-col>
      <v-col>
        <DashboardCard color="red" icon="mdi-account" title="Etudiants" text="58"></DashboardCard>
      </v-col>
      <v-col>
        <DashboardCard color="red" icon="mdi-account" title="Etudiants" text="58"></DashboardCard>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <DashboardCard color="blue" icon="mdi-offer" title="Offres Entreprises" text="50"></DashboardCard>
      </v-col>
      <v-col>
        <DashboardCard color="red" icon="mdi-offer" title="Offres libres" text="18"></DashboardCard>
      </v-col>
      <v-col>
        <DashboardCard color="green" icon="mdi-account" title="Etudiants sans offres" text="15"></DashboardCard>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import DashboardCard from "@/components/Dashboard/DashboardCard";

export default {
  name: "DashboardGlobal",
  components: {DashboardCard}
}
</script>

<style scoped>

</style>