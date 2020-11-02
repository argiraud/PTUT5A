<template>
  <header class="overflow-hidden">
    <v-app-bar
        color="primary lighten-1"
        dark
        fixed
    >
      <v-app-bar-nav-icon @click="drawer = true"></v-app-bar-nav-icon>

      <v-toolbar-title>Ptut</v-toolbar-title>
      <v-spacer></v-spacer>
      <router-link to="/Connexion">
      <v-btn >Se Connecter</v-btn>
      </router-link>
    </v-app-bar>
    <v-navigation-drawer
        width="300"
        style="position:fixed; top:0; left:0;"
        v-model="drawer"
        absolute
        temporary>
      <h1 class="text-center" style="margin-top: 10px">Ptut</h1>
      <v-list nav dense v-scroll:false>
        <v-list-item-group
            v-model="group"
            active-class="lighten-1--text text--accent-4">
          <v-list-item to="home">
            <v-list-item-icon>
              <v-icon>mdi-home</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="white-space: normal">Accueil</v-list-item-title>
          </v-list-item>
          <v-list-item to="adminDonnees">
            <v-list-item-icon>
              <v-icon>mdi-database-cog</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="white-space: normal">Administration des données</v-list-item-title>
          </v-list-item>
          <v-list-item to="home2">
            <v-list-item-icon>
              <v-icon>mdi-account</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="white-space: normal">Dépôt d'offre d'apprentissage
              / de candidature
            </v-list-item-title>
          </v-list-item>
          <v-list-item to="home3">
            <v-list-item-icon>
              <v-icon>mdi-clipboard-list</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="white-space: normal">Saisie des voeux</v-list-item-title>
          </v-list-item>
          <v-list-item to="home4">
            <v-list-item-icon>
              <v-icon>mdi-calendar-month</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="white-space: normal">Planification d'entretiens</v-list-item-title>
          </v-list-item>
          <v-list-item to="home5">
            <v-list-item-icon>
              <v-icon>mdi-desktop-mac-dashboard</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="white-space: normal">Tableau de bord</v-list-item-title>
          </v-list-item>
        </v-list-item-group>
      </v-list>
    </v-navigation-drawer>
  </header>
</template>

<script>
// import {Slide} from 'vue-burger-menu'

export default {
  name: 'home',
  components: {
    // Slide
  },
  data: () => ({
    drawer: false,
    group: null,
  }),
  methods: {
  }
}
</script>

<style>
</style>
