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
      <v-btn v-if="!isConnected">Se connecter</v-btn>
      </router-link>
      <router-link to="/adminDonnees">
        <v-btn v-if="isConnected" style="margin-right: 5px">{{currentUsername}}</v-btn>
      </router-link>
      <router-link to="/Connexion">
        <v-btn title="Se déconnecter" style="background: red" v-if="isConnected" @click="LogOut"><v-icon>exit_to_app</v-icon></v-btn>
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
          <v-list-item v-if="isConnected" to="adminDonnees">
            <v-list-item-icon>
              <v-icon>mdi-database-cog</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="white-space: normal">Administration des données</v-list-item-title>
          </v-list-item>
          <v-list-item v-if="isConnected" to="home2">
            <v-list-item-icon>
              <v-icon>mdi-account</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="white-space: normal">Dépôt d'offre d'apprentissage
              / de candidature
            </v-list-item-title>
          </v-list-item>
          <v-list-item v-if="isConnected" to="home3">
            <v-list-item-icon>
              <v-icon>mdi-clipboard-list</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="white-space: normal">Saisie des voeux</v-list-item-title>
          </v-list-item>
          <v-list-item v-if="isConnected" to="home4">
            <v-list-item-icon>
              <v-icon>mdi-calendar-month</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="white-space: normal">Planification d'entretiens</v-list-item-title>
          </v-list-item>
          <v-list-item v-if="isConnected" to="home5">
            <v-list-item-icon>
              <v-icon>mdi-desktop-mac-dashboard</v-icon>
            </v-list-item-icon>
            <v-list-item-title style="white-space: normal">Tableau de bord</v-list-item-title>
          </v-list-item>
        </v-list-item-group>
        <v-list-item v-if="isConnected" to="creationCompte">
          <v-list-item-icon>
            <v-icon>mdi-database-cog</v-icon>
          </v-list-item-icon>
          <v-list-item-title>Création du compte</v-list-item-title>
        </v-list-item>
        <v-list-item v-if="!isConnected" to="Connexion">
          <v-list-item-icon>
            <v-icon>mdi-database-cog</v-icon>
          </v-list-item-icon>
          <v-list-item-title>Connexion</v-list-item-title>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>
  </header>
</template>

<script>
  import {mapState} from 'vuex';
export default {
  name: 'home',
  components: {
    // Slide
  },
  data: () => ({
    drawer: false,
    group: null,
    CurrentUser: {
      UserId : null,
      UserName : null,
      UserRoleId : null,
      UserToken : null
    }
  }),
  created(){
      this.CurrentUser.UserId = window.sessionStorage.getItem("UserId");
      this.CurrentUser.UserName = window.sessionStorage.getItem("UserName");
      this.CurrentUser.UserRoleId = window.sessionStorage.getItem("UserRoleId");
      this.CurrentUser.UserToken = window.sessionStorage.getItem("UserToken");
      if(this.CurrentUser.UserId == '' || this.CurrentUser.UserId == null || this.CurrentUser.UserId === undefined ){
        this.$store.commit('CONNEXION_MANAGEMENT', false);
      }else{
        this.$store.commit('CONNEXION_MANAGEMENT', true);
      }
      this.$store.commit('SET_CURRENTUSERNAME', window.sessionStorage.getItem("UserName"));
  },
  methods: {
    LogOut(){
      window.sessionStorage.clear();
      this.CurrentUser.UserId = null;
      this.CurrentUser.UserName = null;
      this.CurrentUser.UserRoleId = null;
      this.CurrentUser.UserToken = null;
      this.$store.commit('CONNEXION_MANAGEMENT', false);
      this.$store.commit('SET_CURRENTUSERNAME', '');
    },
    forceRerender(){
      console.log()
      this.$forceUpdate();
    }
  },
  watch: {
    CurrentUsername: function(){

    }
  },
  computed: {
    ...mapState({
      isConnected: 'isConnected',
      currentUsername: 'currentUsername'
    }),
  }
}
</script>

<style>
  a{
    text-decoration: none;
  }
</style>
