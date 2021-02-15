<template>
  <v-dialog v-model="show" max-width="500px">

    <div>
      <h1> Liste Offres</h1>

      <br>

      <v-data-table
          v-model="selected"
          :headers="headers"
          :items="profiles"
          :single-expand="singleExpand"
          item-key="name"
          :items-per-page="5"
          class="elevation-1"
          show-expand
      >
        <template v-slot:expanded-item="{item}">
          <tr>
            <td>
              <v-btn color="primary" @click="addWish(item.idOffer)">AJouter</v-btn>
            </td>
          </tr>
        </template>
      </v-data-table>

    </div>
  </v-dialog>
</template>

<script>
import CompanyDataService from "@/service/CompanyDataService";
import WishDataService from "@/service/WishDataService";

export default {
  name: "AjoutVoeuxEtudiant",
  data () {
    return{
      singleExpand: false,
      selected: [],
      profiles: [
      ],
      headers: [
        {
          text: 'Nom entreprise',
          align: 'start',
          sortable: false,
          value: 'name',
        },
        {
          text: 'Id',
          value: 'idOffer',
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
          case 201 :
            alert("Ajout du voeux effectuée");
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