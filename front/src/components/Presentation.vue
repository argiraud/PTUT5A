<template>
  <div id="header">
    <h1> Présentez-vous </h1>
    <br>
    <p style="white-space: pre-line;">{{ message }}</p>
    <br>
    <textarea v-model="message" placeholder="Présentation..."></textarea>
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
export default {
name: "Presentation",

  methods: {
    validate() {
      this.$emit("step1-finish", "true")
      this.$refs.form.validate();

    }
  },
}
</script>

<style scoped>

</style>