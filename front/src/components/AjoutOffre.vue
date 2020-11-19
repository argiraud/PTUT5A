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
        <v-btn color="red" flat @click.stop="show=false">Fermer</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
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
    },
    APIAddDocument(){
      const offer = {
        file: this.filesObj,
        idOffer: window.sessionStorage.getItem("idOffer"),
      };
      alert("test");
      alert(window.sessionStorage.getItem("idOffer"));
      const options = {
        method: 'POST',
        body: JSON.stringify(offer),
        headers: {
          "accept": "*/*",
          "Authorization": window.sessionStorage.getItem("UserToken"),
          "Content-Type": "application/json"
        }
      };
      fetch("https://api.polyrecrute.tk/company/offer/uploadFile", options).then(response => {
        console.log(response);
        switch (response.status) {
          case 201 :
            alert("Ajout du document effectuée");
            break;
          case 400 :
            alert("Title, key word or description is too long");
            break;
          case 401 :
            alert("Authentification error");
            break;
          case 403:
            alert("No sufficient right");
            break;
        }
        return response.json();
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
      };
      const options = {
        method: 'POST',
        body: JSON.stringify(offer),
        headers: {
          "accept": "*/*",
          "Authorization": window.sessionStorage.getItem("UserToken"),
          "Content-Type": "application/json"
        }
      };
      fetch("https://api.polyrecrute.tk/company/offer/create", options).then(response => {
        console.log(response);
        switch (response.status) {
          case 201 :
            console.log("case 200")
            response.json().then(respjson => {
              window.sessionStorage.setItem("idOffer",respjson.idOffer);
            })
            alert("Ajout de l'offre effectuée");
            break;
          case 400 :
            alert("Title, key word or description is too long");
            break;
          case 409 :
            alert("le doc existe deja !");
            break;
        }
        return response.json();
      }).then(data => {
        console.log(data);
      }).catch(err => {
        console.log(err);
      });
    }
  },
}

</script>

<style scoped>

</style>