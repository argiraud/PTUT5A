<template>
  <v-container>
    <v-row>
      <v-col>
        <DashboardCard color="blue" icon="mdi-account" title="Etudiants" v-bind:text="nbStudents"></DashboardCard>
      </v-col>
      <v-col>
        <DashboardCard color="orange" icon="mdi-account" title="Etudiants sans offres" text="test"></DashboardCard>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-card
            elevation="10"
        >
          <v-card-title> Liste des étudiants
            <v-spacer></v-spacer>
            <v-text-field
                v-model="search"
                append-icon="mdi-magnify"
                label="Rechercher"
                single-line
                hide-details
            ></v-text-field>
          </v-card-title>
          <v-data-table
              :headers="headers"
              :items="students"
              :items-per-page="10"
              class="elevation-1"
              :search="search"
              :footer-props="{
                'items-per-page-text':'Etudiants par page'
              }"
          >
            <template v-slot:[`item.actions`]="{ item }">
              <v-icon
                  small
                  class="mr-2"
                  @click="editItem(item)"
              >
                mdi-pencil
              </v-icon>
            </template>
          </v-data-table>
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
      search: '',
      students: [],
      nbStudents: 0,
      headers: [
        {text: "Nom", align: "start", value: "name", sortable: true},
        {text: "Prénom", value: "firstName", sortable: true},
        {text: "Email", value: "email", sortable: true},
        {text: '', value: 'actions', sortable: false },
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
            this.nbStudents = response.data.response;
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