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
      <div class="large-12 medium-12 small-12 cell">
        <label> Document
          <input type="file" id="file" ref="file" @change="onFileChange"/>
        </label>

      </div>
      </v-card-actions>

      <v-card-actions>
        <v-btn color="primary" @click="AddOffer">Ajouter</v-btn>
        <v-btn color="primary" @click="this.APIAddDocument">Ajouter File</v-btn>
        <v-btn color="red" @click.stop="show=false">Fermer</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
import CompanyDataService from "@/service/CompanyDataService";
import OfferDataService from "@/service/OfferDataService";

export default {
name: "AjoutOffre",
  data: () => ({
    filesObj:{},
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
        //this.APIAddDocument();
        this.show = false;
    },


    APIAddDocument(){
      let formData = new FormData();
      formData.append('file', this.filesObj);
      console.log(window.sessionStorage.getItem("idOffer"));
      OfferDataService.uploadFile(formData, window.sessionStorage.getItem("idOffer")).then(response => {
        console.log(response);
        switch (response.status) {
          case 201 :
            alert("Ajout du document effectuée");
            break;
          case 400 :
            alert("Titre, mots-clés ou description trop long");
            break;
          case 401 :
            console.log("Authentification error");
            break;
          case 403:
            console.log("No sufficient right");
            break;
        }
      }).then(data => {
        console.log(data);
      }).catch(err => {
        console.log(err);
      });
    },

    onFileChange(event){
      let fileData =  event.target.files[0];
      this.filesObj = fileData;
      console.log('file Object:==>',this.filesObj);

    },


    APIAddOffer(){
      const offer = {
        title: document.getElementById('title').value.toString(),
        keyWord: document.getElementById('keyWord').value.toString(),
        description: document.getElementById('description').value.toString(),
        state : "available",
      };
      CompanyDataService.create(offer).then(response => {
        switch (response.status) {
          case 201 :
            window.sessionStorage.setItem("idOffer", response.data.idOffer);
            window.sessionStorage.setItem("idEntity", response.data.company.idEntity);
            alert("Ajout du document effectuée");
            console.log(response.data)
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