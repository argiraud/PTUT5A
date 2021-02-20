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
    },
    profileUserInfos : {
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
      console.log("SET_CURRENTUSER_FROM_JSON");
      state.currentUser.Id = payload.id != undefined ? payload.id : '';
      state.currentUser.Name = payload.name != undefined ? payload.name : '';
      state.currentUser.Firstname = payload.firstName != undefined ? payload.firstName : '';
      state.currentUser.StudentNumber = payload.etudiantNumber != undefined ? payload.etudiantNumber : '';
      state.currentUser.BirthDate = payload.birthDate != undefined ? payload.birthDate : '';
      state.currentUser.Email = payload.email != undefined ? payload.email : '';
      state.currentUser.Status = payload.status != undefined ? payload.status : '';
      state.currentUser.Presentation = payload.presentation != undefined ? payload.presentation : '';
      if(typeof(payload.roles) != undefined ){
        state.currentUser.RoleId = payload.roles[0] != undefined ? payload.roles[0].idRole : '';
        state.currentUser.IsAdmin = payload.roles[1] != undefined ? true : false;
      }else{
        state.currentUser.RoleId = '';
        state.currentUser.IsAdmin = false;
      }
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
