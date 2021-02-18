<template>
  <v-container>
    <v-row>
      <v-col>
        <DashboardCard color="blue" icon="mdi-account" title="Entreprises" v-bind:text="nbCompanies"></DashboardCard>
      </v-col>
      <v-col>
        <DashboardCard color="red" icon="mdi-account" title="Etudiants" v-bind:text="nbStudents"></DashboardCard>
      </v-col>
      <v-col>
        <DashboardCard color="orange" icon="mdi-account" title="CV déposé" text="test"></DashboardCard>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <DashboardCard color="grey" icon="mdi-offer" title="Offres Entreprises" v-bind:text="nbOffers"></DashboardCard>
      </v-col>
      <v-col>
        <DashboardCard color="red" icon="mdi-offer" title="Offres libres" text="test"></DashboardCard>
      </v-col>
      <v-col>
        <DashboardCard color="purple" icon="mdi-account" title="Etudiants sans offres" text="test"></DashboardCard>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import DashboardCard from "@/components/Dashboard/DashboardCard";
import CompanyDataService from "@/service/CompanyDataService";
import OfferDataService from "@/service/OfferDataService";

export default {
  name: "DashboardGlobal",
  components: {DashboardCard},
  data: () => ({
    nbCompanies: 0,
    nbStudents: 0,
    nbOffers: 0,
  }),
  methods: {
    countCompanies() {
      CompanyDataService.count()
          .then(response => {
            this.nbCompanies = response.data.response;
          })
          .catch(e => {
            console.error(e);
          })
    },
    countStudents() {
      CompanyDataService.count()
          .then(response => {
            this.nbStudents = response.data.response;
          })
          .catch(e => {
            console.error(e);
          })
    },
    countOffers() {
      OfferDataService.count()
          .then(response => {
            this.nbOffers = response.data.response;
          })
          .catch(e => {
            console.error(e);
          })
    },
  },
  mounted() {
    this.countCompanies();
    this.countStudents();
    this.countOffers();
  }
}
</script>

<style scoped>

</style>