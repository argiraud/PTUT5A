<template>
  <div class="ml-5 mr-5">

    <h1 class="text-center display-2"> Saisie des voeux </h1>

    <br>

    <v-btn rounded outlined color="#009BDD" large @click.stop="showScheduleForm=true" >+ Ajouter un voeu </v-btn>
    <AjoutVoeux v-model="showScheduleForm" @add-wish="refresh"/>

    <br>
    <br>

    <v-data-table
        v-model="selected"
        :headers="headers"
        :items="wish"
        item-key="name"
        :single-expand="singleExpand"
        :items-per-page="5"
        class="elevation-1"
        show-expand
    >
      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title class="display-1">Mes voeux</v-toolbar-title>
          <v-spacer></v-spacer>
        </v-toolbar>
      </template>
      <template v-slot:expanded-item="{item}">
        <tr>
          <td>
            <v-btn rounded outlined class="ma-2" color="red" @click="deleteItemById(item.id)">Supprimer</v-btn>
          </td>
        </tr>
      </template>
    </v-data-table>


    <br>

  <v-btn
      color="#009BDD"
      rounded
      style="color: white"
      class="mr-4"
      @click="validate"
      to="/"
  >
    Valider
  </v-btn>
  </div>
</template>

<script>
import AjoutVoeux from "@/components/AjoutVoeux";
import WishDataService from "@/service/WishDataService";

export default {
  name: "SaisieVoeuxForm",
  components:{
    AjoutVoeux
  },
  data () {
    return {
      expanded: [],
      singleExpand: true,
      showScheduleForm: false,
      selected: [],
      headers: [
        {
          text: 'ID',
          align: 'start',
          sortable: false,
          value: 'id',
        },
        {
          text: 'Nom',
          align: 'start',
          sortable: false,
          value: 'name',
        },
        {
          text: 'Prénom',
          align: 'start',
          sortable: false,
          value: 'firstName',
        },
      ],
      wish: [
      ],
    }
  },

  methods: {
    validate() {
      this.$emit("step3-finish", "true")

    },

    refresh(){
      this.APIGetVoeux();
    },


    deleteItemById (id) {
      if(confirm('Etes-vous sur de vouloir supprimer ce voeu ?')){
          const index = this.wish.findIndex(wish => wish.id === id); // find the post index
          if (~index) // if the post exists in array
            this.wish.splice(index, 1) //delete the post

          WishDataService.deleteVoeuxEntreprise(id).then(response => {
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

    APIGetVoeux(){
        let idEntity = this.$store.state.currentUser.Id;
        WishDataService.getVoeuxEntreprise(idEntity).then(response => {
          this.wish = response.data;
        })
            .catch(e => {
              console.error(e);
            })

    }

  },

  mounted: function() {
    this.APIGetVoeux()
  },
}
</script>

<style scoped>

</style>