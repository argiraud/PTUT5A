<template>
  <v-container>
    <v-row>
      <v-col>
        <DashboardCard color="blue" icon="mdi-account" title="Etudiants" text="28"></DashboardCard>
      </v-col>
      <v-col>
        <DashboardCard color="orange" icon="mdi-account" title="Etudiants sans offres" text="28"></DashboardCard>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-card
            elevation="10"
        >
          <v-card-title> Liste des étudiants</v-card-title>
          <v-data-table
              :headers="headers"
              :items="etudiants"
              :items-per-page="10"
              class="elevation-1"
          ></v-data-table>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>

import DashboardCard from "@/components/Dashboard/DashboardCard";

export default {
  name: "DashboardEtudiant",
  components: {DashboardCard},
  data() {
    return {
      headers: [
        {
          text: 'Nom',
          align: 'start',
          sortable: false,
          value: 'name',
        },
        {text: 'Prenom', value: 'prenom'},
        {text: 'Statut', value: 'statut'},
      ],
      etudiants: [
        {
          name: 'Baudin',
          prenom: 'Maxime',
          statut: 'Disponible'
        },
        {
          name: 'Giroud',
          prenom: 'Olivier',
          statut: 'En contact'
        },
        {
          name: 'Blaise',
          prenom: 'Matuidi',
          statut: 'Terminé'
        },
        {
          name: 'Baudin',
          prenom: 'Maxime',
          statut: 'Disponible'
        },
        {
          name: 'Baudin',
          prenom: 'Maxime',
          statut: 'Disponible'
        },
        {
          name: 'Baudin',
          prenom: 'Maxime',
          statut: 'Disponible'
        },
        {
          name: 'Baudin',
          prenom: 'Maxime',
          statut: 'Disponible'
        },
      ],
    }
  },
}
</script>

<style scoped>

</style>