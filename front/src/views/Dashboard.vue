<template>
  <div>
    <h1 class="text-center" style="margin-bottom: 10px">Tableau de bord</h1>
    <v-tabs
        v-model="tabs"
        fixed-tabs
        background-color="#4B7DEA"
        dark
    >
      <v-tabs-slider color="yellow"></v-tabs-slider>

      <v-tab
          v-for="(item, index) in items"
          :key="index"
      >
        {{ item.tab }}
      </v-tab>
    </v-tabs>

    <v-tabs-items v-model="tabs">
      <v-tab-item
          v-for="(item, index) in items"
          :key="index"
      >
        <component v-bind:is="item.component"></component>
      </v-tab-item>
    </v-tabs-items>
  </div>
</template>

<script>
import DashboardGlobal from "@/components/Dashboard/DashboardGlobal";
import DashboardEntreprise from "@/components/Dashboard/DashboardEntreprise";
import DashboardEtudiant from "@/components/Dashboard/DashboardEtudiant";
import DashboardOffre from "@/components/Dashboard/DashboardOffre";

export default {
  name: 'dashboard',
  components: {DashboardEtudiant, DashboardEntreprise, DashboardGlobal},
  data() {
    return {
      tabs: null,
      items: [
        {tab: 'Global', component: DashboardGlobal},
        {tab: 'Offres', component: DashboardOffre},
        {tab: 'Entreprises', component: DashboardEntreprise},
        {tab: 'Etudiants', component: DashboardEtudiant},
      ],
    }
  },
}
</script>

<style>

</style>