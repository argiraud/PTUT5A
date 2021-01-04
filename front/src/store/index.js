import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isConnected : false,
    currentUserName: '',
  },
  mutations: {
    CONNEXION_MANAGEMENT(state, payload){
      state.isConnected = payload;
    },
    // eslint-disable-next-line no-unused-vars
    SET_SESSION_FROM_JSON(state, payload){
      window.sessionStorage.setItem("UserToken", payload.tokenType + ' ' + payload.tokenJWT);
      window.sessionStorage.setItem("UserName", payload.name);
      window.sessionStorage.setItem("UserEmail", payload.email);
      window.sessionStorage.setItem("UserBirthDate", payload.birthDate);
      window.sessionStorage.setItem("UserId", payload.id);
      window.sessionStorage.setItem("UserPresentation", payload.presentation);
      window.sessionStorage.setItem("UserRoleId", payload.roles[0].idRole);
      window.sessionStorage.setItem("UserIsAdmin", payload.roles[1] != undefined ? true : false);
    },
    SET_CURRENTUSERNAME(state, payload){
      state.currentUserName = payload;
    }
  },
  actions: {
  },
  modules: {
  }
})
