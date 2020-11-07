<template>
  <div>
  <h1> Saisie voeux</h1>

  <v-btn
      color="Valider"
      class="mr-4"
      @click="validate"
  >
    Validate
  </v-btn>
  </div>
</template>

<script>
export default {
  name: "SaisieVoeuxForm",

  methods: {
    validate() {
      this.$emit("step3-finish", "true")
      this.$refs.form.validate();

    },
  },
}
</script>

<style scoped>

</style>