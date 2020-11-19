<template>
  <v-container>
    <v-row>
      <v-col>
        <DashboardCard color="blue" icon="mdi-account" title="Entreprises" v-bind:text="nbCompanies"></DashboardCard>
      </v-col>
      <v-col>
        <DashboardCard color="green" icon="mdi-account" title="Offres disponibles" text="28"></DashboardCard>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-card
            elevation="10"
        >
          <v-card-title> Liste des entreprises</v-card-title>
          <v-data-table
              :headers="headers"
              :items="companies"
              :items-per-page="5"
          ></v-data-table>
        </v-card>

      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import DashboardCard from "@/components/Dashboard/DashboardCard";
import CompanyDataService from "@/service/CompanyDataService";

export default {
  name: "DashboardEntreprise",
  components: {DashboardCard},
  data() {
    return {
      companies: [],
      nbCompanies: 0,
      headers: [
        {text: "Id", align: "start", value: "idEntity", sortable: true},
        {text: "Nom", value: "name", sortable: true},
      ],
    }
  },
  methods: {
    getAllCompanies() {
      CompanyDataService.getAll()
          .then(response => {
            this.companies = response.data;
          })
          .catch(e => {
            console.error(e);
          })
    },
    countCompanies() {
      CompanyDataService.count()
          .then(response => {
            this.nbCompanies = response.data;
          })
          .catch(e => {
            console.error(e);
          })
    }
  },
  mounted() {
    this.getAllCompanies();
    this.countCompanies();
  }
}
</script>

<style scoped>

</style>