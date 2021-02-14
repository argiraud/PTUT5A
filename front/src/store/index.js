import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    isConnected : false,
    currentUser : {
      Id: '',
      Name : '',
      Firstname : '',
      StudentNumber : '',
      BirthDate: '',
      Email: '',
      Status: '',
      Presentation: '',
      RoleId: '',
      IsAdmin: ''
    }
  },
  mutations: {
    CONNEXION_MANAGEMENT(state, payload){
      state.isConnected = payload;
    },
    // eslint-disable-next-line no-unused-vars
    SET_CURRENTUSER_FROM_JSON(state, payload){
      console.log("payload :" + payload);
      state.currentUser.Id = payload.id != undefined ? payload.id : '';
      state.currentUser.Name = payload.name != undefined ? payload.name : '';
      state.currentUser.Firstname = payload.firstName != undefined ? payload.firstName : '';
      state.currentUser.StudentNumber = payload.etudiantNumber != undefined ? payload.etudiantNumber : '';
      state.currentUser.BirthDate = payload.birthDate != undefined ? payload.birthDate : '';
      state.currentUser.Email = payload.email != undefined ? payload.email : '';
      state.currentUser.Status = payload.status != undefined ? payload.status : '';
      state.currentUser.Presentation = payload.presentation != undefined ? payload.presentation : '';
      state.currentUser.RoleId = payload.roles[0].idRole != undefined ? payload.roles[0].idRole : '';
      state.currentUser.IsAdmin = payload.roles[1] != undefined ? true : false;

      /*
      window.sessionStorage.setItem("UserName", payload.name);
      window.sessionStorage.setItem("UserEmail", payload.email);
      window.sessionStorage.setItem("UserId", payload.id);
      window.sessionStorage.setItem("UserPresentation", payload.presentation);
      window.sessionStorage.setItem("UserRoleId", payload.roles[0].idRole);
      window.sessionStorage.setItem("UserIsAdmin", payload.roles[1] != undefined ? true : false);*/
    },
    SET_CURRENTUSERNAME(state, payload){
      state.currentUser.Name = payload;
    }
  },
  actions: {
  },
  modules: {
  }
})
