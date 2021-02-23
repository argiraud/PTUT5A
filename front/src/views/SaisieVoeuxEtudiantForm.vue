<template>
  <div>

    <br>

    <v-btn rounded color="#009BDD" style="color: white" large @click="showScheduleForm=true" > Ajouter un Voeux </v-btn>
    <AjoutVoeuxEtudiant v-model="showScheduleForm" @add-wish="refresh"/>

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
            <v-btn rounded color="red" style="color: white" @click="deleteItemById(item.idOffer)">Supprimer</v-btn>
          </td>
        </tr>
      </template>
    </v-data-table>


    <br>

    <v-btn
            style="color: white"
            rounded
        color="#009BDD"
        class="mr-4"
        @click="validate"
        to="home"
    >
      Valider
    </v-btn>
  </div>
</template>

<script>
import AjoutVoeuxEtudiant from "@/components/AjoutVoeuxEtudiant";
import WishDataService from "@/service/WishDataService";

export default {
name: "SaisieVoeuxEtudiantForm",
  components:{
    AjoutVoeuxEtudiant,
  },
  data () {
    return {
      expanded: [],
      singleExpand: false,
      showScheduleForm: false,
      selected: [],
      headers: [
          {
              text: 'Entreprise',
              align: 'start',
              sortable: true,
              value: `company.name`,
          },
        {
          text: 'Titre',
          align: 'start',
          sortable: true,
          value: 'title',
        },
        {
          text: 'Mots-clés',
          align: 'start',
          sortable: false,
          value: 'keyWord',
        },
        {
          text: 'Description',
          align: 'start',
          sortable: false,
          value: 'description',
        },
      ],
      wish: [
      ],
    }
  },

  methods: {
    validate() {
      this.$emit("step3-finish", "true")
      this.$refs.form.validate();

    },


    deleteItemById (id) {
      if(confirm('Etes-vous sur de vouloir supprimer ce voeux ?')){
          const index = this.wish.findIndex(wish => wish.idOffer === id); // find the post index
          if (~index) // if the post exists in array
            this.wish.splice(index, 1) //delete the post

          WishDataService.deleteVoeuxEtudiant(id).then(response => {
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

    refresh(){
      this.APIGetVoeux();
    },

    APIGetVoeux(){
        let idEntity = this.$store.state.currentUser.Id;
        console.log(idEntity)
        WishDataService.getVoeuxEtudiant(idEntity).then(response => {
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