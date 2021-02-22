<template>
  <v-container>
    <v-row>
      <v-col>
        <DashboardCard color="blue" icon="mdi-account" title="Entreprises" v-bind:text="nbCompanies"></DashboardCard>
      </v-col>
      <v-col>
        <DashboardCard color="green" icon="mdi-offer" title="Offres disponibles" v-bind:text="nbOffersInProgress"></DashboardCard>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-card
            elevation="10"
        >
          <v-card-title> Liste des entreprises
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
              :items="companies"
              :items-per-page="10"
              :search="search"
              :footer-props="{
                'items-per-page-text':'Entreprises par page'
              }"
          >
            <template v-slot:[`item.actions`]="{ item }">
              <PopUpOtherProfile :id-user-to-display="item.id"></PopUpOtherProfile>
            </template>
          </v-data-table>
        </v-card>

      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import DashboardCard from "@/components/Dashboard/DashboardCard";
import CompanyDataService from "@/service/CompanyDataService";
import PopUpOtherProfile from "@/components/PopUpOtherProfile";
import OfferDataService from "@/service/OfferDataService";

export default {
  name: "DashboardEntreprise",
  components: {DashboardCard, PopUpOtherProfile},
  data() {
    return {
      search: '',
      companies: [],
      nbCompanies: 0,
      nbOffersInProgress: 0,
      headers: [
        {text: "Nom", align: "start", value: "name", sortable: true},
        {text: '', align: "start", value: 'actions', sortable: false },
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
            this.nbCompanies = response.data.response;
          })
          .catch(e => {
            console.error(e);
          })
    },
    countOffersInProgress() {
      OfferDataService.countOffersInProgress()
          .then(response => {
            this.nbOffersInProgress = response.data.response;
          })
          .catch(e => {
            console.error(e);
          })
    },
  },
  mounted() {
    this.getAllCompanies();
    this.countCompanies();
    this.countOffersInProgress();
  }
}
</script>

<style scoped>
td {
  text-align: center !important;
}
</style>