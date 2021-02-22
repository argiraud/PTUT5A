<template>
  <div class="ml-5 mr-5">
    <h1 class="text-center"> Depot offre d'apprentissage</h1>

    <br>


    <v-btn large @click.stop="showScheduleForm=true" > Nouvelle offre </v-btn>
    <AjoutOffre v-model="showScheduleForm" @add-doc="refresh" />

    <br>
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
      <template v-slot:[`item.state`]="{ item }">
          {{item.state}}
      </template>
      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title>Liste des offres</v-toolbar-title>
          <v-spacer></v-spacer>
        </v-toolbar>
      </template>
      <template v-slot:expanded-item="{item}">
        <tr>
          <td>
            <v-btn color="primary" class="ma-2" @click="deleteItemById(item.idOffer)">Supprimer</v-btn>
          </td>
          <td>
            <v-btn color="primary" class="ma-2" @click="showEditForm=true" > Modifier Offre </v-btn>
            <EditOffre v-model="showEditForm" v-bind:id-offer-send=item.idOffer @edit-finish="refresh"/>
          </td>
          <td>
            <v-btn color="primary" class="ma-2" @click="openItemById(item.files[0].idFile, item.files[0].name)">Ouvrir fichier</v-btn>
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
import CompanyDataService from "@/service/CompanyDataService";
import OfferDataService from "@/service/OfferDataService";
import FileDataService from "@/service/FileDataService";
import EditOffre from "@/components/EditOffre";

export default {
name: "DepotOffreForm",

  components:{
    EditOffre,
    AjoutOffre
  },
  data () {
    return {
      singleExpand: true,
      fileName: '',
      showScheduleForm: false,
      showEditForm: false,
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
          color: "blue",
        },
        {
          text: 'Nom du document',
          sortable: true,
          value: 'files[0].name',
        },
      ],

    }
  },


  methods: {

    validate() {
      this.$emit("step2-finish", "true")
    },


    deleteItemById (idOffer) {
      if(confirm('Etes-vous sur de vouloir supprimer cette offre ?')){
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
        this.offers.push(item);
      },

  refresh(){
      this.APIGetOffer();
    },

    APIGetOffer(){
      let idEntity = this.$store.state.currentUser.Id;
      CompanyDataService.get(idEntity).then(response => {

        for(let i= 0; i < response.data.length; i++)
        {
          switch (response.data[i].state)
          {
            case "available":
              response.data[i].state = "Disponible"
              break
            case "inprogress":
              response.data[i].state = "En cours"
              break
            case "finished":
              response.data[i].state = "Terminé"
              break
          }
        }

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