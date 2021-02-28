<template>
  <div id="header">
    <h1 class="text-center display-2"> Présentation </h1>
    <br>
    <p style="white-space: pre-line;">{{ message }}</p>
    <br>
      <template>
        <v-container fluid>
          <v-textarea
              clearable
              clear-icon="mdi-close-circle"
              name="input-7-1"
              filled
              label="Présentation"
              auto-grow
              color="#009BDD"
              placeholder="Présentez-vous"
              id="prez"
          ></v-textarea>
        </v-container>
      </template>
    <br>
    <v-btn
            rounded
            style="color: white"
        color="#009BDD"
        class="mr-4"
        @click="validate"
    >
      Valider
    </v-btn>
      <br/>
      <br/>
  </div>

</template>

<script>
import StudentDataService from "@/service/StudentDataService";

export default {
name: "Presentation",
  data: () => ({
    presentation:'',
  }),

  methods: {
    validate() {
      let currentUser = this.$store.state.currentUser

      if (currentUser.RoleId == 1){ //Etudiant
        let user = {
          "id" : currentUser.Id,
          "name" : currentUser.Name,
          "email" : currentUser.Email,
          "presentation" : document.getElementById('prez').value,
          "roles" : [],
          "files" : [],
          "enable" : "",
          "firstName" : currentUser.Firstname,
          "etudiantNumber" : currentUser.StudentNumber,
          "birthDate" : currentUser.BirthDate,
          "status" : currentUser.Status,
        };

        StudentDataService.updateUserInfos(user).then(response => {
          switch(response.status){
            case 200:
              break;
            case 400:
              console.log("Error Token");
              break;
          }
        }).catch(err => console.log("erreur : "+ err));
      } else if (currentUser.RoleId == 2) { //Entreprise

        let company = {
          "id" : currentUser.Id,
          "name" : currentUser.Name,
          "email" : currentUser.Email,
          "presentation" : document.getElementById('prez').value,
          "roles" : [],
          "files" : [],
          "enable" : "",
          "status" : currentUser.Status,
        };

        StudentDataService.updateUserInfos(company).then(response => {
          switch(response.status){
            case 200:
              alert("Mise à jour de la présentation réussie");
              break;
            case 400:
              console.log("Error Token");
              break;
          }
        })
      }
      this.$emit("step1-finish", "true")
    }
  },
}
</script>

<style scoped>

</style>