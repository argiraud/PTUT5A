<template>
  <div>
  <h1> Saisie voeux</h1>

    <br>

    <v-btn large @click.stop="showScheduleForm=true" > Ajouter Voeux </v-btn>
    <AjoutVoeux v-model="showScheduleForm" />

    <br>

    <v-data-table
        v-model="selected"
        :headers="headers"
        :items="offre"
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

    <br>

    <div>
      <v-btn color="primary" @click="deleteItem">Supprimer</v-btn>
    </div>

    <br>

  <v-btn
      color="Valider"
      class="mr-4"
      @click="validate"
      to="home"
  >
    Valider
  </v-btn>
  </div>
</template>

<script>
import AjoutVoeux from "@/components/AjoutVoeux";

export default {
  name: "SaisieVoeuxForm",
  components:{
    AjoutVoeux
  },
  data () {
    return {
      showScheduleForm: false,
      selected: [],
      headers: [
        {
          text: 'Numéro',
          align: 'start',
          sortable: false,
          value: 'number',
        },
        {
          text: 'Nom',
          align: 'start',
          sortable: false,
          value: 'name',
        },
      ],
      offre: [
        {
          number: 1,
          name: 'ISIA',
        },
        {
          number: 2,
          name: 'Aperam',
        },
      ],
    }
  },

  methods: {
    validate() {
      this.$emit("step3-finish", "true")
      this.$refs.form.validate();

    },
    deleteItem () {
      if(confirm('Etes-vous sur de vouloir supprimer ce document ?')){
        for(var i = 0; i <this.selected.length; i++){
          const index = this.offers.indexOf(this.selected[i]);
          this.offers.splice(index, 1);
        }
      }
    },
  },
}
</script>

<style scoped>

</style>