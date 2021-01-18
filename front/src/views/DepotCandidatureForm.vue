<template>
  <div>
    <h1> Depot de candidature </h1>

    <br>

    <v-btn large @click.stop="showScheduleForm=true" > Nouvelle Candidature </v-btn>
    <AjoutOffre v-model="showScheduleForm" />

    <br>
  </div>
</template>

<script>
export default {
name: "DepotCandidatureForm"
}
</script>

<style scoped>

</style>