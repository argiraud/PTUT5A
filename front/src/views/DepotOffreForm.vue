<template>
  <div>
    <h1> Depot offre d'apprentissage</h1>

    <br>


    <v-btn large @click.stop="showScheduleForm=true" > Nouvelle offre </v-btn>
    <AjoutOffre v-model="showScheduleForm" />

    <br>

    <v-data-table
        :headers="headers"
        :items="offers"
        item-key="title"
        show-expand
        :items-per-page="5"
        :single-expand="singleExpand"
        class="elevation-1"
    >
      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title>Liste des offres</v-toolbar-title>
          <v-spacer></v-spacer>
        </v-toolbar>
      </template>
      <template v-slot:expanded-item="{item}">
        <tr>
          <td>
            <v-btn color="primary" @click="deleteItemById(item.idOffer)">Supprimer</v-btn>
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

  </div>
</template>

<script>
import AjoutOffre from "@/components/AjoutOffre";
//import OfferDataService from "@/service/OfferDataService";
import CompanyDataService from "@/service/CompanyDataService";
import OfferDataService from "@/service/OfferDataService";

export default {
name: "DepotOffreForm",

  components:{
  AjoutOffre
  },
  data () {
    return {
      singleExpand: false,
      fileName: '',
      showScheduleForm: false,
      offers: [
      ],
      headers: [
        {
          text: 'Nom offre',
          align: 'start',
          sortable: true,
          value: 'title',
        },
        {
          text: 'Mots clés',
          sortable: true,
          value: 'keyWord',
        },
        {
          text: 'Description',
          sortable: true,
          value: 'description',
        },
        {
          text: 'Statut',
          sortable: true,
          value: 'state',
        },
      ],

    }
  },


  methods: {

    validate() {
      this.$emit("step2-finish", "true")
      this.$refs.form.validate();

    },


    deleteItemById (idOffer) {
      if(confirm('Etes-vous sur de vouloir supprimer ce document ?')){
        OfferDataService.delete(idOffer).then(response => {
          const index = this.offers.findIndex(offers => offers.idOffer === idOffer); // find the post index
          if (~index) // if the post exists in array
            this.offers.splice(index, 1) //delete the post

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


    add: function(item) {
        this.offers.push(item);
      },

    APIGetOffer(){
      let idEntity = this.$store.state.currentUser.Id;
      console.log("idEntity : " + idEntity)
      CompanyDataService.get(idEntity).then(response => {
        console.log(response.data);
        this.offers = response.data;
      })
          .catch(e => {
            console.error(e);
          })

    }

  },

  mounted: function() {
    this.APIGetOffer()
  },

}

</script>

<style scoped>

</style>