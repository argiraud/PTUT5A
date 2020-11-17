<template>
  <div>
    <h1> Depot offre d'apprentissage</h1>

    <br>

    <div class="large-12 medium-12 small-12 cell">
      <label> Document
        <input type="file" id="file" ref="file" @change="onFileChange"/>
      </label>

      <v-btn color="primary" @click="AddDoc">   Ajouter</v-btn>
    </div>

    <br>

    <v-btn large @click.stop="showScheduleForm=true" > Nouvelle offre </v-btn>
    <AjoutOffre v-model="showScheduleForm" />

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
      <v-btn color="primary" @click="deleteItem"  :disabled='isDisabled' >Supprimer</v-btn>
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
import AjoutOffre from "@/components/AjoutOffre";

export default {
name: "DepotOffreForm",

  components:{
  AjoutOffre
  },
  data () {
    return {
      filesObj:{},
      fileName: '',
      showScheduleForm: false,
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
    AddDoc(){
      this.APIAddDocument();
    },
    APIAddDocument(){
      const offer = {
        file: this.filesObj,
        idOffer: window.sessionStorage.getItem("idOffer"),
      };
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
            // Alerte votre compte a bien été créer
            //redirige vers stepper 1
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
    validate() {
      this.$emit("step2-finish", "true")
      this.$refs.form.validate();

    },
    onFileChange(event){
      let fileData =  event.target.files[0];
      this.filesObj = fileData;
      console.log('file Object:==>',this.filesObj);
      this.fileName=fileData.name;
    },
    deleteItem () {
      if(confirm('Etes-vous sur de vouloir supprimer ce document ?')){
        for(var i = 0; i <this.selected.length; i++){
          const index = this.offre.indexOf(this.selected[i]);
          this.offre.splice(index, 1);
        }
      }
    },
    add: function(item) {
        alert("test");
        this.APIAddDocument();
        this.offre.push(item);
      }
    },
    mounted: function() {
    },
}

</script>

<style scoped>

</style>