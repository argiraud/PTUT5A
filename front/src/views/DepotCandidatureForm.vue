<template>
  <div class="ml-5 mr-5">
    <h1 class="text-center"> Gestion des documents </h1>

    <br>

    <div>
      <div>
        <h4>Chargez vos documents, ils seront visibles sur votre profil.
          <br />
          <br />
          <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
        </h4>
        <br>
        <v-btn color="primary" v-on:click="submitFile()" :disabled="EmptyList()">Envoyer</v-btn>
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
          <v-toolbar-title>Candidature</v-toolbar-title>
          <v-spacer></v-spacer>
        </v-toolbar>
      </template>
        <template v-slot:[`item.actions`]="{ item }">
            <v-btn color="primary" @click="openItemById(item.idFile, item.name)">Ouvrir</v-btn>
        </template>
        <template v-slot:[`item.actions2`]="{ item }">
            <v-btn color="red" style="color: white" @click="deleteItemById(item.idFile, item.name)">Supprimer</v-btn>
        </template>
    </v-data-table>

    <br>

    <v-btn
        color="Valider"
        class="mr-4"
        @click="validate"
    >
      Valider
    </v-btn>
    <br>
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

  methods: {

    validate() {
      this.$emit("step2-finish", "true")
    },

    EmptyList() {
      console.log(this.candidatures.length)
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
      FileDataService.uploadUserFile(this.file, 1).then(response => {
        switch (response.status) {
          case 201 :
            alert("Ajout du document effectuée");
            this.APIGetCandidature();
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
  },

  mounted: function() {
    this.APIGetCandidature()
  },

}

</script>

<style scoped>

</style>