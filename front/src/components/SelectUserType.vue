<template>
    <div>
        <p>Type de compte</p>
        <input type="button" @click="ChangetoEntreprise" value="Entreprise">
        <input type="button" @click="ChangetoCandidat" value="Candidats">
    </div>
        
</template>

<script>
export default {
    name: 'SelectUserType',
    methods: {
        ChangetoEntreprise(){
            this.isEntreprise = true;
            this.EmitUserType();
        },
        ChangetoCandidat(){
            this.isEntreprise = false;
            this.EmitUserType();
        },
        EmitUserType(){
            this.$emit("change-user",{  UserType: this.isEntreprise });
        }
     },
    data: function () {
    return {
      isEntreprise: true
        }
    }
}
</script>