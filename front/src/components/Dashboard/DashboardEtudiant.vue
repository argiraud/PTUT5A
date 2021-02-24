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
          <v-card-title class="display-1"> Liste des étudiants</v-card-title>
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
import StudentDataService from "@/service/StudentDataService";

export default {
  name: "DashboardEtudiant",
  components: {DashboardCard},
  data() {
    return {
      students: [],
      nbStudents: 0,
      headers: [
        {text: "Id", align: "start", value: "idEntity", sortable: true},
        {text: "Nom", value: "name", sortable: true},
      ],
    }
  },
  methods: {
    getAllStudents() {
      StudentDataService.getAll()
          .then(response => {
            this.students = response.data;
          })
          .catch(e => {
            console.error(e);
          })
    },
    countStudents() {
      StudentDataService.count()
          .then(response => {
            this.nbStudents = response.data;
          })
          .catch(e => {
            console.error(e);
          })
    }
  },
  mounted() {
    this.getAllStudents();
    this.countStudents();
  }
}
</script>

<style scoped>

</style>