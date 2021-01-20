<template>
  <v-dialog v-model="show" max-width="500px">

  <div>
    <h1> Liste Etudiant </h1>

    <br>

    <v-data-table
        v-model="selected"
        :headers="headers"
        :items="profiles"
        item-key="name"
        show-select
        :items-per-page="5"
        class="elevation-1"
    >
      <template v-slot="props">
        <td>
          <v-checkbox
              v-model="props.selected"
              primary
              hide-details
          ></v-checkbox>
        </td>
        <td>{{ props.item.name }}</td>
      </template>
    </v-data-table>

    <v-btn color="primary" @click="APIGetStudents">Etudiants</v-btn>
  </div>
  </v-dialog>
</template>

<script>
import StudentDataService from "@/service/StudentDataService";

export default {
name: "AjoutVoeux",
  data () {
  return{
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
        value: 'firstname',
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
      StudentDataService.getAll()
          .then(response => {
            this.profiles = response.data;
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