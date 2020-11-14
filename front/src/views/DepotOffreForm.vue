<template>
  <div>
    <h1> Depot offre </h1>

    <br>

    <div class="large-12 medium-12 small-12 cell">
      <label> Document
        <input type="file" id="file" ref="file" @change="selectedFile"/>
      </label>

      <v-btn color="primary" @click="add({name:files})">   Ajouter</v-btn>
    </div>

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
        <td>{{ props.offre.name }}</td>
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
    >
      Valider
    </v-btn>
  </div>
</template>

<script>
export default {
name: "DepotOffreForm",
  data () {
    return {
      files: '',
      selected: [],
      headers: [
        {
          text: 'Nom du document',
          align: 'start',
          sortable: false,
          value: 'name',
        },
      ],
      offre: [
      ],

    }
  },

  methods: {
    validate() {
      this.$emit("step2-finish", "true")
      this.$refs.form.validate();

    },
    selectedFile(e) {
      this.files = e.target.files;

    },
    deleteItem () {
      if(confirm('Are you sure you want to delete this item?')){
        for(var i = 0; i <this.selected.length; i++){
          const index = this.offre.indexOf(this.selected[i]);
          this.offre.splice(index, 1);
        }
      }
    },
    add: function(item) {
        this.offre.push(item);
      }
    },
    mounted: function() {
      this.add({name:'CV.pdf'});
      this.add({name:'Portfolio.pdf'});
    },

}
</script>

<style scoped>

</style>