<template>
  <div>
    <div class="about">
      <p> Nom : {{ nm }} <input type="text" v-model="nm"></p>
      <p> Prénom : {{ prnm }} <input type="text" v-model="prnm"></p>
      <p> Numéro Etudiant : {{ numEtu }} <input type="text" v-model="numEtu"></p>
      <p> Date de naissance : <input type="date"></p>
      <p> Mail : {{ mail }} <input type="text" v-model="mail"></p>
    </div>

    <v-simple-table>
      <template v-slot:default>
        <thead>
        <tr>
          <th class="text-left">Selection</th>
          <th class="text-left">Nom du document</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="doc in DOC" :key="doc.name">
          <td>
            <label class="form-checkbox">
              <input type="checkbox" :value="doc.id" v-model="selected">
              <doc class="form-icon"></doc>
            </label>
          </td>
          <td>{{ doc.name }}</td>
        </tr>
        </tbody>
      </template>
    </v-simple-table>


    <div>
      <!-- `greet` est le nom de la méthode définie ci-dessous -->
      <button v-on:click="validate">Valider</button>
    </div>
  </div>

</template>


<script>
export default {
  data() {
    return {
      DOC: [
        {
          name: 'Mon_CV.pdf',
        },
        {
          name: 'Portfolio.pdf',
        },
        {
          name: 'BG.pdf',
        },
      ],
    }
  },

  methods: {
    validate: function (event) {
      // `this` fait référence à l'instance de Vue à l'intérieur de `methods`
      this.$router.push('about')
      // `event` est l'évènement natif du DOM
      if (event) {
        //alert(event.target.tagName)
      }
    }
  }
}
</script>