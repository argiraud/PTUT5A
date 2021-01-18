<template>
  <div>
    <h1> Depot offre d'apprentissage</h1>

    <br>


    <v-btn large @click.stop="showScheduleForm=true" > Nouvelle offre </v-btn>
    <AjoutOffre v-model="showScheduleForm" />

    <br>

    <v-data-table
        v-model="offers"
        :headers="headers"
        :items="offers"
        item-key="name"
        show-select
        :items-per-page="5"
        class="elevation-1"
    >
      <template v-slot="props">
        <td>
          <v-checkbox
              v-model="props.offers"
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
//import OfferDataService from "@/service/OfferDataService";
import CompanyDataService from "@/service/CompanyDataService";

export default {
name: "DepotOffreForm",

  components:{
  AjoutOffre
  },
  data () {
    return {
      fileName: '',
      showScheduleForm: false,
      offers: [
      ],
      headers: [
        {
          text: 'Nom du document',
          align: 'start',
          sortable: false,
          value: 'title',
        },
        {
          text: 'Mots clés',
          value: 'keyWord',
        },
        {
          text: 'Description',
          value: 'description',
        },
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
          const index = this.offers.indexOf(this.selected[i]);
          this.offers.splice(index, 1);
        }
      }
    },
    add: function(item) {
        this.offers.push(item);
      },

    APIGetOffer(){
      let idEntity = window.sessionStorage.getItem("idEntity");
      console.log("idEntity : " + idEntity)
      CompanyDataService.get(idEntity).then(response => {
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