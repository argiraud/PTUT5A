<template>
  <div class="ml-5 mr-5">

    <v-progress-linear
        :active="loading"
        :indeterminate="loading"
        absolute
        bottom
        color="#009BDD"
    ></v-progress-linear>

    <h1 class="text-center display-2"> Gestion du document </h1>

    <br>

    <div>
      <div>
        <h4>Chargez votre document descriptif, ils sera visible sur votre profil.
          <br />
          <br />
          <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
        </h4>
        <br>
        <v-btn color="#009BDD" rounded style="color: white" v-on:click="submitFile()" :disabled="emptyList()">Envoyer</v-btn>
      </div>
    </div>

    <br>

    <v-data-table
        :headers="headers"
        :items="candidatures"
        item-key="name"
        :items-per-page="5"
        class="elevation-1"
    >
      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title class="display-1">Document</v-toolbar-title>
          <v-spacer></v-spacer>
        </v-toolbar>
      </template>
        <template v-slot:[`item.actions`]="{ item }">
            <v-btn color="#009BDD" rounded outlined @click="openItemById(item.idFile, item.name)">Ouvrir</v-btn>
        </template>
        <template v-slot:[`item.actions2`]="{ item }">
            <v-btn color="red" rounded outlined @click="deleteItemById(item.idFile, item.name)">Supprimer</v-btn>
        </template>
    </v-data-table>

    <br>

    <v-btn
        color="#009BDD"
        rounded
        style="color: white"
        class="mr-4"
        @click="validate"
        v-if="isCreationCompte"
    >
      Valider
    </v-btn>
    <br/>
    <br/>
  </div>
</template>

<script>
import StudentDataService from "@/service/StudentDataService";
import FileDataService from "@/service/FileDataService";

export default {
name: "DepotCandidatureForm",

  data () {
    return {
      file: '',
      loading: false,
      candidatures: [
      ],
      headers: [
        {
            text: 'Nom du fichier',
            align: 'start',
            sortable: false,
            value: 'name',
        },
        {
            text: '',
            value: 'actions',
            sortable: false
        },
        {
            text: '',
            value: 'actions2',
            sortable: false
        }
      ],
    }
  },
  props: [
    'isCreationCompte',
  ],

  methods: {

    validate() {
      this.$emit("step2-finish", "true")
    },

    reset() {
      this.$refs.file.value=null;
    },

    emptyList() {
      if (this.candidatures.length == 0){
        return false
      } else {
        return true
      }
    },

    deleteItemById (idFile) {
      if(confirm('Etes-vous sur de vouloir supprimer ce document ?')){
        FileDataService.deleteFile(idFile).then(response => {
          const index = this.candidatures.findIndex(candidatures => candidatures.idFile === idFile); // find the post index
          if (~index) // if the post exists in array
            this.candidatures.splice(index, 1) //delete the post

          switch (response.status) {
            case 201 :
              alert("Suppression effectuée");
              break;
          }
        }).catch(e => {
          console.error(e);
        })

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

    add: function(item) {
      this.candidatures.push(item);
    },

    APIGetCandidature(){
      StudentDataService.getConnectedUserDetails().then(response => {
        this.candidatures = response.data.files;
      })
          .catch(e => {
            console.error(e);
          })
    },

    handleFileUpload(){
      this.file = this.$refs.file.files[0];
    },

    submitFile(){
      this.loading = true;
      FileDataService.uploadUserFile(this.file, 1).then(response => {
        switch (response.status) {
          case 201 :
            alert("Ajout du document effectuée");
            this.APIGetCandidature();
            this.reset();
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
        this.loading = false;
      }).then(data => {
        console.log(data);
      }).catch(err => {
        console.log(err);
      });
    },
  },

  mounted: function() {
    this.APIGetCandidature()
  },

}

</script>

<style scoped>

</style>