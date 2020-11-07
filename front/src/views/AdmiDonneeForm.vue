<template>
  <v-form
      ref="form"
      v-model="valid"
      lazy-validation
  >
    <v-text-field
        v-model="name"
        :rules="nameRules"
        label="Nom"
        required
    ></v-text-field>

    <v-text-field
        v-model="firstname"
        :rules="firstnameRules"
        label="Prénom"
        required
    ></v-text-field>

    <v-text-field
        v-model="studentNumber"
        label="Numéro Etudiant"
    ></v-text-field>

    <v-menu
        ref="menu"
        v-model="menu"
        :close-on-content-click="false"
        :nudge-right="40"
        lazy
        transition="scale-transition"
        offset-y
        full-width
        min-width="290px"
    >
      <template v-slot:activator="{ on }">
        <v-text-field
            v-model="date"
            label="Date de naissance"
            readonly
            v-on="on"
        ></v-text-field>
      </template>
      <v-date-picker
          ref="picker"
          v-model="date"
          hint="MM/DD/YYYY format"
          persistent-hint
          :max="new Date().toISOString().substr(0, 10)"
          min="1950-01-01"
          @change="save"
      ></v-date-picker>
    </v-menu>

    <v-text-field
        v-model="email"
        :rules="emailRules"
        label="E-mail"
        required
    ></v-text-field>

    <br>

    <v-text-field
        v-model="presentation"
        label="Presentation"
    ></v-text-field>

    <div class="large-12 medium-12 small-12 cell">
      <label> Document
        <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
      </label>

      <button>   Ajouter </button>
    </div>

    <br>

    <br>

    <v-data-table
    :headers="headers"
    :items="doc"
    :items-per-page="5"
    class="elevation-1"
    >

    </v-data-table>

    <br>

    <br>

    <v-btn
        :disabled="!valid"
        color="Valider"
        class="mr-4"
        @click="validate"
    >
      Validate
    </v-btn>

  </v-form>

</template>


<script>
export default {
  props: ['isClick'],
  data: () => ({
    valid: true,
    name: '',
    nameRules: [
      v => !!v || 'Le nom est requis',
    ],
    email: '',
    emailRules: [
      v => !!v || 'E-mail is required',
      v => /.+@.+\..+/.test(v) || 'E-mail must be valid',
    ],
    firstname: '',
    firstnameRules: [
      v => !!v || 'Le prénom est requis',
    ],
    studentNumber : '',
    picker: new Date().toISOString().substr(0, 10),
    menu: false,
    date: null,
    presentation:'',
    file:''
  }),
  headers: [
    {
      text: 'Nom du document',
      align: 'start',
      sortable: false,
      value: 'name',
    },
    { text: '', value: 'del' },
  ],
  doc: [
      //A COMPLETER
    {
      name: 'Mon CV.pdf',
      del : false,
    },
  ],

  watch: {
    menu (val) {
      val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
    }
  },
  methods: {
    validate () {
      this.$emit("step1-finish", "true")
      this.$refs.form.validate();

    },

    handleFileUpload(){
      this.file = this.$refs.file.files[0];
    },
  },
}
</script>