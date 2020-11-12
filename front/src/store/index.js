import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isConnected : false,
    currentUsername : ''
  },
  mutations: {
    CONNEXION_MANAGEMENT(state, payload){
      state.isConnected = payload;
    },
    SET_CURRENTUSERNAME(state, payload){
      state.currentUsername = payload;
    }
  },
  actions: {
  },
  modules: {
  }
})
