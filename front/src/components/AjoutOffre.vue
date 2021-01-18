<template>
  <v-dialog v-model="show" max-width="500px">
    <v-card>
      <v-card-actions>
        <v-text-field
            v-model="title"
            :rules="titleRules"
            id="title"
            label="Titre de l'offre"
            required
            type="text"
            color="teal accent-3"
            prepend-inner-icon="person"
        ></v-text-field>
      </v-card-actions>

      <v-card-actions>
        <v-text-field
            v-model="keyWord"
            id="keyWord"
            label="Mots clés"
            type="text"
            color="teal accent-3"
            prepend-inner-icon="person"
        ></v-text-field>
      </v-card-actions>

      <v-card-actions>
        <v-text-field
            v-model="description"
            id="description"
            label="Description"
            type="text"
            color="teal accent-3"
            prepend-inner-icon="person"
        ></v-text-field>
      </v-card-actions>

      <v-card-actions>
        <v-btn color="primary" @click="AddOffer">Ajouter</v-btn>
        <v-btn color="red" flat @click.stop="show=false">Fermer</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import CompanyDataService from "@/service/CompanyDataService";

export default {
name: "AjoutOffre",
  data: () => ({
    valid: true,
    title: '',
    titleRules: [
      v => !!v || 'le titre est requis'
    ],
    keyWord: '',
    description: '',
  }),
  props: {
    value: Boolean
  },
  computed: {
    show: {
      get () {
        return this.value
      },
      set (value) {
        this.$emit('input', value)
      }
    }
  },
  methods: {
    AddOffer(){
        this.APIAddOffer();
    },
    APIAddOffer(){
      const offer = {
        title: document.getElementById('title').value.toString(),
        keyWord: document.getElementById('keyWord').value.toString(),
        description: document.getElementById('description').value.toString(),
      };
      CompanyDataService.create(offer).then(response => {
        switch (response.status) {
          case 201 :
            window.sessionStorage.setItem("idOffer", response.data.idOffer);
            window.sessionStorage.setItem("idEntity", response.data.company.idEntity);
            alert("Ajout du document effectuée");
            break;
        }
      })
          .catch(e => {
            console.error(e);
          })

    }
  },
}

</script>

<style scoped>

</style>