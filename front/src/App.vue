<template>
  <v-app>
    <Navigation style="margin-bottom: 80px"></Navigation>
    <v-main>
      <router-view></router-view>
    </v-main>
    <Footer style="margin-top: 50px"></Footer>
  </v-app>
</template>

<script>
import Navigation from '@/components/Navigation';
import Footer from "@/components/Footer";

export default {
  name: 'App',

  components: {
    Footer,
    Navigation,
  },

  data: () => ({
  }),
  methods: {
    forceRerender(){
      console.log("force update");
      this.$forceUpdate();
    }
  }
};
</script>
