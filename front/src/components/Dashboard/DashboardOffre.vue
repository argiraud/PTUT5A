<template>
  <v-container>
    <v-row>
      <v-col>
        <DashboardCard color="blue" icon="mdi-offer" title="Offres" v-bind:text="nbOffers"></DashboardCard>
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
          <v-card-title> Liste des offres
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
              :items="offers"
              :items-per-page="10"
              :search="search"
              :footer-props="{
                'items-per-page-text':'Offres par page'
              }"
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
      nbOffersInProgress:0,
      headers: [
        {text: "TITRE", align: "start", value: "title", sortable: true},
        {text: "ENTREPRISE", align: "start", value: "company.name", sortable: true},
        {
          text: 'MOTS-CLES',
          align: 'start',
          sortable: false,
          value: 'keyWord',
        },
        {
          text: 'DESCRIPTION',
          align: 'start',
          sortable: false,
          value: 'description',
        },
        {text: "ETAT", align: "start", value: "state", sortable: true},
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
    countOffersInProgress() {
      OfferDataService.countOffersInProgress()
          .then(response => {
            this.nbOffersInProgress = response.data.response;
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
    this.countOffersInProgress();
  }
}
</script>

<style scoped>

</style>