<template>
  <v-dialog v-model="show" max-width="900px">

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
      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title>Liste des étudiants disponibles</v-toolbar-title>
          <v-spacer></v-spacer>
        </v-toolbar>
      </template>
      <template v-slot:expanded-item="{item}">
        <tr>
          <td>
            <v-btn rounded outlined color="#009BDD" class="ma-2" @click="addWish(item.id)">Ajouter</v-btn>
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
import StudentDataService from "@/service/StudentDataService";
import WishDataService from "@/service/WishDataService";
import FileDataService from "@/service/FileDataService";

export default {
name: "AjoutVoeux",
  data () {
  return{
    singleExpand: false,
    selected: [],
    profiles: [
    ],
    headers: [
      {
        text: 'Nom du candidat',
        align: 'start',
        sortable: false,
        value: 'name',
      },
      {
        text: 'Prénom du candidat',
        value: 'firstName',
      },
      {
        text: 'Id',
        value: 'id',
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

    APIGetStudents(){
      StudentDataService.getAll().then(response => {
        this.profiles = response.data;
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

    addWish (idUser) {
      WishDataService.createVoeux(idUser).then(response => {
        switch (response.status) {
          case 200 :
            alert("Ajout du voeu effectué");
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
    this.APIGetStudents()
  },
}
</script>

<style scoped>

</style>