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
              <v-btn rounded outlined color="#009BDD" class="ma-2" @click="addWish(item.idOffer)">Ajouter</v-btn>
            </td>
            <td>
              <v-btn color="primary" class="ma-2" :disabled="checkItem(item.files)" @click="openItemById(item.files[0].idFile, item.files[0].name)">Ouvrir Fichier</v-btn>
            </td>
          </tr>
        </template>
      </v-data-table>

  </v-dialog>
</template>

<script>
import CompanyDataService from "@/service/CompanyDataService";
import WishDataService from "@/service/WishDataService";
import FileDataService from "@/service/FileDataService";

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

    checkItem(fileTab){
      if (fileTab.length <= 0) {
        return true
      } else {
        return false
      }

    },

    openItemById (idFile, title) {
      FileDataService.getFileById(idFile).then(response => {
        const url = window.URL.createObjectURL(new Blob([response.data]))
        const link = document.createElement('a')
        link.href = url
        link.setAttribute('download', title)
        document.body.appendChild(link)
        link.click()
      }).catch(e => {
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