import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isConnected : false,
    currentUsername : '',
    currentUserRole : ''
  },
  mutations: {
    CONNEXION_MANAGEMENT(state, payload){
      state.isConnected = payload;
    },
    SET_CURRENTUSERNAME(state, payload){
      state.currentUsername = payload;
    },
    SET_CURRENTUSERROLE(state, payload) {
      state.currentUserRole = payload;
    }
  },
  actions: {
  },
  modules: {
  }
})
