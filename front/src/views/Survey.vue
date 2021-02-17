<template>
  <v-container>
    <h1 class="text-center" style="margin-bottom: 10px">Questionnaire</h1>
    <v-form ref="form"
            v-model="valid"
    >
      <p>{{ 'La communication sur l\'événement a été :' }}</p>
      <v-radio-group
          v-model="question1"
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
      <p>{{ 'Les informations délivrées sur l\'événement ont été :' }}</p>
      <v-radio-group
          v-model="question2"
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
      <p>{{ 'Globalement, est-ce que le Forum virtuel a correspondu à vos attentes ?' }}</p>
      <v-radio-group
          v-model="question3"
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
      <p>{{
          'Avez-vous trouvé des profils de candidats (entreprise) et des offres d\'entreprise intéressantes (candidat)?'
        }}</p>
      <v-radio-group
          v-model="question4"
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
      <p>{{ 'Avez-vous trouvé l\'application simple d\'utilisation ?' }}</p>
      <v-radio-group
          v-model="question5"
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
      <p>{{ 'Avez-vous trouvé l\'application simple d\'utilisation ?' }}</p>
      <v-textarea
          v-model="question6"
          :rules="questionRule"
          required
          name="input-7-1"
          filled
          label="Votre réponse"
          auto-grow
      ></v-textarea>
      <p>{{ 'Etes-vous satisfait du déroulement de vos entretiens ?' }}</p>
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
      <p>{{ 'Avez-vous des remarques à partager sur le déroulement de vos entretiens ?' }}</p>
      <v-textarea
          v-model="question8"
          :rules="questionRule"
          required
          name="input-7-1"
          filled
          label="Votre réponse"
          auto-grow
      ></v-textarea>
      <p>{{ 'Selon vous, quels sont les points positifs du Forum virtuel ?' }}</p>
      <v-textarea
          v-model="question9"
          :rules="questionRule"
          required
          name="input-7-1"
          filled
          label="Votre réponse"
          auto-grow
      ></v-textarea>
      <p>{{ 'Selon vous, quelles sont les améliorations à apporter ?' }}</p>
      <v-textarea
          v-model="question10"
          :rules="questionRule"
          required
          name="input-7-1"
          filled
          label="Votre réponse"
          auto-grow
          style="margin-right: 50px"
      ></v-textarea>
      <p>{{ 'Avez-vous d\'autres retours/suggestions à partager concernant le Forum virtuel ?' }}</p>
      <v-textarea
          v-model="question11"
          :rules="questionRule"
          required
          name="input-7-1"
          filled
          label="Votre réponse"
          auto-grow
      ></v-textarea>
    </v-form>
    <div class="text-center">
      <v-btn
          color="Valider"
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
export default {
  name: "Survey",
  data: () => ({
    valid: false,
    question1: '',
    question2: '',
    question3: '',
    question4: '',
    question5: '',
    question6: '',
    question7: '',
    question8: '',
    question9: '',
    question10: '',
    question11: '',
    questionRule: [
      v => !!v || 'Une réponse est requise'
    ],
  }),

  methods: {
    validate() {

    }
  }
}
</script>

<style scoped>
.radio-group {
  margin-left: 70px;
}
</style>