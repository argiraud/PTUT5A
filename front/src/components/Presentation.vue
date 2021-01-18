<template>
  <div id="header">
    <h1> Présentation </h1>
    <br>
    <p style="white-space: pre-line;">{{ message }}</p>
    <br>
      <template>
        <v-container fluid>
          <v-textarea
              clearable
              clear-icon="mdi-close-circle"
              name="input-7-1"
              filled
              label="Présentation"
              auto-grow
              placeholder="Présentez-vous"
          ></v-textarea>
        </v-container>
      </template>
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