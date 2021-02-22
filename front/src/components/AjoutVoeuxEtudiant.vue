<template>
  <v-dialog v-model="show" max-width="500px">

      <v-data-table
          v-model="selected"
          :headers="headers"
          :items="profiles"
          :single-expand="singleExpand"
          item-key="title"
          :items-per-page="5"
          class="elevation-1"
          show-expand
      >
        <template v-slot:top>
          <v-toolbar flat>
            <v-toolbar-title>Liste Offres</v-toolbar-title>
            <v-spacer></v-spacer>
          </v-toolbar>
        </template>
        <template v-slot:expanded-item="{item}">
          <tr>
            <td>
              <v-btn color="primary" @click="addWish(item.idOffer)">AJouter</v-btn>
            </td>
          </tr>
        </template>
      </v-data-table>

  </v-dialog>
</template>

<script>
import CompanyDataService from "@/service/CompanyDataService";
import WishDataService from "@/service/WishDataService";

export default {
  name: "AjoutVoeuxEtudiant",
  data () {
    return{
      singleExpand: true,
      selected: [],
      profiles: [
      ],
      headers: [
        {
          text: 'Nom entreprise',
          align: 'start',
          sortable: false,
          value: 'company.name',
        },
        {
          text: 'Titre',
          value: 'title',
        },
        {
          text: 'Mots-clés',
          value: 'keyWord',
        },
        {
          text: 'Description',
          value: 'description',
        },
      ],
    }

  },

  props: {
    value: Boolean
  },
  computed: {
    show: {
      get() {
        return this.value
      },
      set(value) {
        this.$emit('input', value)
      }
    },
  },

  methods : {

    APIGetOffers(){
      CompanyDataService.getAllOffers()
          .then(response => {
            this.profiles = response.data;
          })
          .catch(e => {
            console.error(e);
          })

    },

    addWish (idOffer) {
      WishDataService.createVoeuxEtudiant(idOffer).then(response => {
        switch (response.status) {
          case 200 :
            alert("Ajout du voeux effectuée");
            this.$emit("add-wish", "true")
            break;
        }
      })
          .catch(e => {
            console.error(e);
          })
    },

  },

  mounted: function() {
    this.APIGetOffers()
  },
}
</script>

<style scoped>

</style>