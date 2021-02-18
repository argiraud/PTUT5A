<template>
  <v-container>
    <v-row>
      <v-col>
        <DashboardCard color="blue" icon="mdi-account" title="Offres" v-bind:text="nbOffers"></DashboardCard>
      </v-col>
      <v-col>
        <DashboardCard color="green" icon="mdi-account" title="Offres disponibles" text="test"></DashboardCard>
      </v-col>
      <v-col>
        <DashboardCard color="orange" icon="mdi-account" title="Offres à valider"
                       v-bind:text="nbPendingOffers"></DashboardCard>
      </v-col>
    </v-row>
    <v-row>
      <v-col>
        <v-card
            elevation="10"
        >
          <v-card-title> Liste des offres
            <v-spacer></v-spacer>
            <v-text-field
                v-model="search"
                append-icon="mdi-magnify"
                label="Search"
                single-line
                hide-details
            ></v-text-field>
          </v-card-title>
          <v-data-table
              :headers="headers"
              :items="offers"
              :items-per-page="10"
              :search="search"
          >
            <template v-slot:[`item.state`]="{ item }">
              <v-chip
                  :color="getColor(item.state)"
                  dark
              >
                {{ item.state === 'available' ? 'Disponible' : item.state === 'pending' ? 'En attente' : 'Terminée'}}
              </v-chip>
            </template>
            <template v-slot:[`item.validate`]="{ item }">
              <v-simple-checkbox
                  v-model="item.validate"
                  disabled
              ></v-simple-checkbox>
            </template>
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
import OfferDataService from "@/service/OfferDataService";

export default {
  name: "DashboardOffre",
  components: {DashboardCard},
  data() {
    return {
      search: '',
      offers: [],
      nbOffers: 0,
      nbPendingOffers: 0,
      headers: [
        {text: "TITRE", align: "start", value: "title", sortable: true},
        {text: "ENTREPRISE", value: "company.name", sortable: true},
        {text: "VALIDEE", value: "validate", sortable: true},
        {text: "ETAT", value: "state", sortable: true},
        {text: '', value: 'actions', sortable: false },
      ],
    }
  },
  methods: {
    getAllOffers() {
      OfferDataService.getAll()
          .then(response => {
            this.offers = response.data;
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
    countPendingOffers() {
      OfferDataService.countPendingOffers()
          .then(response => {
            this.nbPendingOffers = response.data.response;
          })
          .catch(e => {
            console.error(e);
          })
    },
    getColor (state) {
      if (state === 'available') return 'green'
      else if (state === 'pending') return 'orange'
      else return 'red'
    },
  },
  mounted() {
    this.getAllOffers();
    this.countOffers();
    this.countPendingOffers();
  }
}
</script>

<style scoped>

</style>