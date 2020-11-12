<template>
  <div>
  <h1> Saisie voeux</h1>

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
export default {
  name: "SaisieVoeuxForm",
  data () {
    return {
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
  },
}
</script>

<style scoped>

</style>