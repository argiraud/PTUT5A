<template>
  <div>
    <h1> Depot de candidature </h1>

    <br>

    <div>
      <div>
        <h3>Charger documents
          <br>
          <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
        </h3>
        <br>
        <v-btn color="primary" v-on:click="submitFile()">Envoyer</v-btn>
      </div>
    </div>

    <br>

    <v-data-table
        :headers="headers"
        :items="candidatures"
        item-key="name"
        show-expand
        :items-per-page="5"
        :single-expand="singleExpand"
        class="elevation-1"
    >
      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title>Candidature</v-toolbar-title>
          <v-spacer></v-spacer>
        </v-toolbar>
      </template>
      <template v-slot:expanded-item="{item}">
        <tr>
          <td>
            <v-btn color="primary" @click="deleteItemById(item.idFile)">Supprimer</v-btn>
          </td>
          <td>
            <v-btn color="primary" @click="openItemById(item.idFile, item.name)">Ouvrir</v-btn>
          </td>
        </tr>
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
      singleExpand: true,
      file: '',
      candidatures: [
      ],
      headers: [
        {
          text: 'Nom du fichier',
          align: 'start',
          sortable: false,
          value: 'name',
        }
      ],

    }
  },

  methods: {

    validate() {
      this.$emit("step2-finish", "true")
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
        console.log(title)
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
        console.log(response.data.files);
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
        console.log(response);
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