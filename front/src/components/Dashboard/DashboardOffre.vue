<template>
  <v-container>
    <v-row>
      <v-col>
        <DashboardCard color="blue" icon="mdi-account" title="Offres" text="0"></DashboardCard>
      </v-col>
      <v-col>
        <DashboardCard color="green" icon="mdi-account" title="Offres disponibles" text="0"></DashboardCard>
      </v-col>
      <v-col>
        <DashboardCard color="orange" icon="mdi-account" title="Offres à valider"
                       text="0"></DashboardCard>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-card
            elevation="10"
        >
          <v-card-title class="display-1"> Liste des offres</v-card-title>
          <v-data-table
              :headers="headers"
              :items="desserts"
              :items-per-page="5"
          ></v-data-table>
        </v-card>

      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import DashboardCard from "@/components/Dashboard/DashboardCard";

export default {
  name: "DashboardOffre",
  components: {DashboardCard}
}
</script>

<style scoped>

</style>