<template>
  <div>
    <h1> Depot offre d'apprentissage</h1>

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
        {
          text: 'Mots clés',
          value: 'keyword',
        },
        {
          text: 'Description',
          value: 'description',
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
    deleteItem () {
      if(confirm('Etes-vous sur de vouloir supprimer ce document ?')){
        for(var i = 0; i <this.selected.length; i++){
          const index = this.offre.indexOf(this.selected[i]);
          this.offre.splice(index, 1);
        }
      }
    },
    add: function(item) {
        this.offre.push(item);
      },

    APIGetOffer(){
      let idEntity = window.sessionStorage.getItem("idEntity");
      let url = "https://api.polyrecrute.tk/company/"+idEntity+"/offers";
      fetch(url,{
        method: "GET",
        headers:{
          "accept": "application/json",
          "Authorization": window.sessionStorage.getItem("UserToken"),
        }
      }).then(response => {
        console.log("response : ")
        console.log(response)
        switch (response.status) {
          case 201 :
            console.log("case 201")
            response.json().then(respjson => {
              for (let pas = 0; pas < respjson.length; pas++){
                this.add({name:respjson[pas].title, keyword:respjson[pas].keyWord, description:respjson[pas].description});
              }

            })
            break;
          case 401 :
            alert("Authentification error");
            break;
          case 403 :
            alert("No sufficient right");
            break;
          case 404 :
            alert("Company not found");
            break;
        }
        return response.json();
      }).catch(err => {
        console.log("erreur : " + err);
      });
    },


  APIGetCompany(){
    let url = "https://api.polyrecrute.tk/companies";
    fetch(url,{
      method: "GET",
      headers:{
        "accept": "application/json",
        "Authorization": window.sessionStorage.getItem("UserToken"),
      }
    }).then(response => {
      console.log("response : ")
      console.log(response)
      switch (response.status) {
        case 200 :
          console.log("case 200")
          response.json().then(respjson => {
            console.log("json " + respjson);
            window.sessionStorage.setItem("idEntity",respjson[0].idEntity);

          })
          break;
        case 401 :
          alert("Authentification error");
          break;
          case 403:
            alert("No sufficient right");
            break;
      }
      return response.json();
    }).catch(err => {
      console.log("erreur : " + err);
    });
  },

  },

  mounted: function() {
    this.APIGetCompany();
    this.APIGetOffer()
  },

}

</script>

<style scoped>

</style>