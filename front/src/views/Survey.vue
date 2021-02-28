<template>
  <v-container>
    <h1 class="text-center display-2" style="margin-bottom: 10px">Questionnaire</h1>
    <v-form ref="form"
            v-model="valid"
    >
      <v-card>
        <v-card-title>
          {{ 'La communication sur l\'événement a été :' }}
        </v-card-title>
        <v-radio-group
            v-model="answers[0]"
            mandatory
            class="radio-group"
        >
          <v-radio
              label="Excellente"
              value="excellente"
          ></v-radio>
          <v-radio
              label="Bonne"
              value="bonne"
          ></v-radio>
          <v-radio
              label="Moyenne"
              value="moyenne"
          ></v-radio>
          <v-radio
              label="Insuffisante"
              value="insuffisante"
          ></v-radio>
        </v-radio-group>
      </v-card>
      <br />
      <v-card>
        <v-card-title>
          {{ 'Les informations délivrées sur l\'événement ont été :' }}
        </v-card-title>
        <v-radio-group
            v-model="answers[1]"
            mandatory
            class="radio-group"
        >
          <v-radio
              label="Excellentes"
              value="excellente"
          ></v-radio>
          <v-radio
              label="Bonnes"
              value="bonne"
          ></v-radio>
          <v-radio
              label="Moyennes"
              value="moyenne"
          ></v-radio>
          <v-radio
              label="Insuffisantes"
              value="insuffisante"
          ></v-radio>
        </v-radio-group>
      </v-card>
      <br/>
      <v-card>
        <v-card-title>
          {{ 'Globalement, est-ce que le Forum virtuel a correspondu à vos attentes ?' }}
        </v-card-title>
        <v-radio-group
            v-model="answers[2]"
            mandatory
            class="radio-group"
        >
          <v-radio
              label="Tout à fait"
              value="excellente"
          ></v-radio>
          <v-radio
              label="Plutôt oui"
              value="bonne"
          ></v-radio>
          <v-radio
              label="Plutôt non"
              value="moyenne"
          ></v-radio>
          <v-radio
              label="Pas du tout"
              value="insuffisante"
          ></v-radio>
        </v-radio-group>
      </v-card>
      <br/>
      <v-card>
        <v-card-title>
          {{
            'Avez-vous trouvé des profils de candidats (entreprise) et des offres d\'entreprise intéressantes (candidat)?'
          }}
        </v-card-title>
        <v-radio-group
            v-model="answers[3]"
            mandatory
            class="radio-group"
        >
          <v-radio
              label="Tout à fait"
              value="excellente"
          ></v-radio>
          <v-radio
              label="Plutôt oui"
              value="bonne"
          ></v-radio>
          <v-radio
              label="Plutôt non"
              value="moyenne"
          ></v-radio>
          <v-radio
              label="Pas du tout"
              value="insuffisante"
          ></v-radio>
        </v-radio-group>
      </v-card>
      <br/>
      <v-card>
        <v-card-title>
          {{ 'Avez-vous trouvé l\'application simple d\'utilisation ?' }}
        </v-card-title>
        <v-radio-group
            v-model="answers[5]"
            mandatory
            class="radio-group"
        >
          <v-radio
              label="Tout à fait"
              value="excellente"
          ></v-radio>
          <v-radio
              label="Plutôt oui"
              value="bonne"
          ></v-radio>
          <v-radio
              label="Plutôt non"
              value="moyenne"
          ></v-radio>
          <v-radio
              label="Pas du tout"
              value="insuffisante"
          ></v-radio>
        </v-radio-group>
      </v-card>
      <br />
      <v-card>
        <v-card-title>
          {{ 'Avez-vous trouvé l\'application simple d\'utilisation ?' }}
        </v-card-title>
        <v-textarea
            style="margin: 1%"
            v-model="answers[6]"
            :rules="questionRule"
            required
            name="input-7-1"
            filled
            label="Votre réponse"
            auto-grow
        ></v-textarea>
      </v-card>
      <br />
      <v-card>
        <v-card-title>
          {{ 'Etes-vous satisfait du déroulement de vos entretiens ?' }}
        </v-card-title>
        <v-radio-group
            v-model="question7"
            mandatory
            class="radio-group"
        >
          <v-radio
              label="Tout à fait"
              value="excellente"
          ></v-radio>
          <v-radio
              label="Plutôt oui"
              value="bonne"
          ></v-radio>
          <v-radio
              label="Plutôt non"
              value="moyenne"
          ></v-radio>
          <v-radio
              label="Pas du tout"
              value="insuffisante"
          ></v-radio>
        </v-radio-group>
      </v-card>
      <br/>
      <v-card>
        <v-card-title>
          {{ 'Avez-vous des remarques à partager sur le déroulement de vos entretiens ?' }}
        </v-card-title>
        <v-textarea
            style="margin: 1%"
            v-model="question8"
            :rules="questionRule"
            required
            name="input-7-1"
            filled
            label="Votre réponse"
            auto-grow
        ></v-textarea>
      </v-card>
      <br />
      <v-card>
        <v-card-title>
          {{ 'Selon vous, quels sont les points positifs du Forum virtuel ?' }}
        </v-card-title>
        <v-textarea
            style="margin: 1%"
            v-model="question9"
            :rules="questionRule"
            required
            name="input-7-1"
            filled
            label="Votre réponse"
            auto-grow
        ></v-textarea>
      </v-card>
      <br/>
      <v-card>
        <v-card-title>
          {{ 'Selon vous, quelles sont les améliorations à apporter ?' }}
        </v-card-title>
        <v-textarea
            style="margin: 1%"
            v-model="question10"
            :rules="questionRule"
            required
            name="input-7-1"
            filled
            label="Votre réponse"
            auto-grow
        ></v-textarea>
      </v-card>
      <br/>
      <v-card>
        <v-card-title>
          {{ 'Avez-vous d\'autres retours/suggestions à partager concernant le Forum virtuel ?' }}
        </v-card-title>
        <v-textarea
            style="margin: 1%"
            v-model="answers[11]"
            :rules="questionRule"
            required
            name="input-7-1"
            filled
            label="Votre réponse"
            auto-grow
        ></v-textarea>
      </v-card>
      <br />

    </v-form>
    <div class="text-center">
      <v-btn
          color="#009BDD"
          rounded
          class="mr-4"
          :disabled="!valid"
          @click="validate"
      >
        Valider
      </v-btn>
    </div>
  </v-container>
</template>

<script>
import SurveyDataService from "@/service/SurveyDataService";
import {mapState} from "vuex";

export default {
  name: "Survey",
  data: () => ({
    valid: false,
    answers: [],
    questionRule: [
      v => !!v || 'Une réponse est requise'
    ],
  }),
  methods: {
    validate() {
      for (let i = 0; i < this.answers.length; i++) {
        const data = {
          noQuestion: i,
          answer: this.answers[i],
          user: this.currentUser
        };
        SurveyDataService.addAnswer(data);
      }
    }
  },
  computed: {
    ...mapState({
      currentUser: 'currentUser'
    }),
  }
}
</script>

<style scoped>
.radio-group {
  margin-left: 70px;
}
</style>