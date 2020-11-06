<template>
    <div id="questionnaire" class="h-100">
    <div class="container-fluid h-100">
      <div class="row h-100">
        <div class="col-md-3 vue-bg h-100 d-flex justify-content-center align-items-center">
          <img alt="Vue logo" src="../assets/logo.png" width="100">
        </div>
        <div class="col-md-9 h-100 d-flex justify-content-center align-items-center">
          <div class="col-md-8 rounded px-5 py-4 shadow bg-white text-left">
                      <SForm />
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import SForm from '../components/Form'

export default {
  name: 'App',
  components: {
    SForm,
  }

}
</script>

<style>
#questionnaire {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align:center;
  color: #2c3e50;
}
.vue-bg {
  background: rgb(0,156,222);
}
</style>