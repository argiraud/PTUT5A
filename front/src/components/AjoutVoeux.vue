<template>
  <v-dialog v-model="show" max-width="500px">

  <div>
    <h1> Liste Etudiant </h1>

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
            <v-btn color="primary" @click="addWish(item.id)">AJouter</v-btn>
          </td>
        </tr>
      </template>
    </v-data-table>

  </div>
  </v-dialog>
</template>

<script>
import StudentDataService from "@/service/StudentDataService";
//import CompanyDataService from "@/service/CompanyDataService";
import WishDataService from "@/service/WishDataService";

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
      alert("test")
      StudentDataService.get().then(response => {
        alert(response.data)
        this.profiles = response.data;
      })
          .catch(e => {
            console.error(e);
          })

    },

    addWish (idUser) {
      WishDataService.createVoeux(idUser).then(response => {
        switch (response.status) {
          case 200 :
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
    this.APIGetStudents()
  },
}
</script>

<style scoped>

</style>