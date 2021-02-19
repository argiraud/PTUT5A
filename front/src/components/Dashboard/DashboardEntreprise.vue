<template>
  <v-container>
    <v-row>
      <v-col>
        <DashboardCard color="blue" icon="mdi-account" title="Entreprises" v-bind:text="nbCompanies"></DashboardCard>
      </v-col>
      <v-col>
        <DashboardCard color="green" icon="mdi-account" title="Offres disponibles" text="test"></DashboardCard>
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
import CompanyDataService from "@/service/CompanyDataService";

export default {
  name: "DashboardEntreprise",
  components: {DashboardCard},
  data() {
    return {
      search: '',
      companies: [],
      nbCompanies: 0,
      headers: [
        {text: "Nom", align: "start", value: "name", sortable: true},
        {text: '', value: 'actions', sortable: false },
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