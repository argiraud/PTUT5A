<template>
  <div>
<h1> Depot offre </h1>

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
name: "DepotOffreForm",

  methods: {
    validate() {
      this.$emit("step2-finish", "true")
      this.$refs.form.validate();

    },
  },
}
</script>

<style scoped>

</style>