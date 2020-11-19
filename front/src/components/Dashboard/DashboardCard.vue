<template>
  <v-card
      class="mx-auto"
      v-bind:color="color"
      dark
      max-width="300"
  >
    <v-card-title>
      <v-icon
          large
          left
      >
        {{ icon }}
      </v-icon>
      <span class="title font-weight-light">
        {{title}}
      </span>
    </v-card-title>

    <v-card-text class="headline font-weight-bold">
      <h1 class="text-center">
        {{text}}
      </h1>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  name: "DashboardCard",
  props: [
    'icon',
    'title',
    'text',
    'color'
  ]
}
</script>

<style scoped>

</style>