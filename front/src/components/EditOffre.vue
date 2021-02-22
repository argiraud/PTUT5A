<template>
  <v-dialog v-model="show" max-width="500px">
    <v-card>
      <v-card-actions>
        <v-text-field
            v-model="title"
            :rules="titleRules"
            id="title"
            label="Titre de l'offre"
            required
            type="text"
            color="teal accent-3"
            prepend-inner-icon="person"
        ></v-text-field>
      </v-card-actions>

      <v-card-actions>
        <v-text-field
            v-model="keyWord"
            id="keyWord"
            label="Mots clés"
            type="text"
            color="teal accent-3"
            prepend-inner-icon="person"
        ></v-text-field>
      </v-card-actions>

      <v-card-actions>
        <v-text-field
            v-model="description"
            id="description"
            label="Description"
            type="text"
            color="teal accent-3"
            prepend-inner-icon="person"
        ></v-text-field>
      </v-card-actions>

      <v-select
          v-model="statusOffer"
          id="comboStatus"
          label="Statut offre"
          small-chips
          :items="items"
      >
        <template v-slot:selection="{ item, selected }">
          <v-chip
              :color="`${item.color} lighten-3`"
              :input-value="selected"
              label
              small
          >
                      <span class="pr-2">
                        {{ item.text }}
                      </span>
          </v-chip>
        </template>
        <template v-slot:item="{ item }">
          <v-chip
              :color="`${item.color} lighten-3`"
              dark
              label
              small
          >
            {{ item.text }}
          </v-chip>
        </template>
      </v-select>

      <v-card-actions>
        <v-btn color="primary" @click="save">Mise à jour</v-btn>
        <v-btn color="red" @click.stop="show=false">Fermer</v-btn>
      </v-card-actions>
    </v-card>

  </v-dialog>
</template>

<script>
import OfferDataService from "@/service/OfferDataService";

export default {
  name: "EditOffre",
  props: {
    idOfferSend: String,
    value: Boolean
  },

  data: () => ({
    items: [
      {
        text: 'Disponible',
        color: 'green',
        value: 'available'
      },
      {
        text: 'En cours',
        color: 'orange',
        value: 'inprogress'
      },
      {
        text: 'Terminé',
        color: 'red',
        value: 'finished'
      }],
      filesObj: {},
      valid: true,
      title: '',
      titleRules: [
        v => !!v || 'le titre est requis'
      ],
      keyWord: '',
      description: '',
      statusOffer:'',
  }),

  computed: {
    show: {
      get() {
        return this.value
      },
      set(value) {
        this.$emit('input', value)
      }
    }
  },

  methods:{
    save(){
      let offer = {
        "idOffer": this.idOfferSend,
        "title": this.title,
        "description": this.description,
        "keyWord": this.keyWord,
        "state": this.statusOffer
      };
      OfferDataService.update(offer).then(response => {
        switch(response.status){
          case 200:
            console.log("mise à jour terminée")
            this.$emit("edit-finish", "true")
            break;
          case 401:
            break;
          case 400:
            break;
        }
      });
    },

  },

  mounted(){
    OfferDataService.getOfferById(this.idOfferSend).then(response => {
      switch (response.status) {
        case 201 :
          this.title = response.data.title,
          this.description = response.data.description,
          this.keyWord = response.data.keyWord
          this.statusOffer = response.data.state
          break;
        case 400 :
          break;
        case 401 :
          break;
        case 403:
          break;
      }
    }).catch(err => {
      console.log(err);
    });
  },
}
</script>

<style scoped>

</style>