<template>
  <form id="signup-form">
        <div class="row">
            <div class="col-12 form-group">
                <label class="col-form-label col-form-label-lg">La communication sur l'événement a été :</label>
                <select class="form-control form-control-lg">
                    <option value="">Excellente</option>
                    <option value="">Bonne</option>
                    <option value="">Moyenne</option>
                    <option value="">Insuffisante</option>
                </select>
            </div>
            <div class="col-12 form-group">
                <label class="col-form-label col-form-label-lg">Les informations délivrées sur l'événement ont été : </label>
                <select class="form-control form-control-lg">
                    <option value="">Excellente</option>
                    <option value="">Bonne</option>
                    <option value="">Moyenne</option>
                    <option value="">Insuffisante</option>
                </select>
            </div>
            <div class="col-12 form-group">
                <label class="col-form-label col-form-label-lg">Globalement, est-ce que le Forum virtuel a correspondu à vos attentes ? </label>
                <select class="form-control form-control-lg">
                    <option value="">Tout a fait oui</option>
                    <option value="">Plutot oui</option>
                    <option value="">Plutot non</option>
                    <option value="">Pas du tout</option>
                </select>
            </div>
            <div class="col-12 form-group">
                <label class="col-form-label col-form-label-lg">Avez-vous trouvé des profils de candidats (entreprise) et des offres d'entreprise intéressantes (candidat)?</label>
                <select class="form-control form-control-lg">
                    <option value="">Tout a fait oui</option>
                    <option value="">Plutot oui</option>
                    <option value="">Plutot non</option>
                    <option value="">Pas du tout</option>
                </select>
            </div>
            <div class="col-12 form-group">
                <label class="col-form-label col-form-label-lg">Avez-vous trouvé l'application simple d'utilisation ?</label>
                <select class="form-control form-control-lg">
                    <option value="">Tout a fait oui</option>
                    <option value="">Plutot oui</option>
                    <option value="">Plutot non</option>
                    <option value="">Pas du tout</option>
                </select>
            </div>
            <div class="col-12 form-group">
                <label class="col-form-label col-form-label-lg">Avez-vous d'autres retours à partager sur l'application ? </label>
                <input type="text" class="form-control form-control-lg">
            </div>
            <div class="col-12 form-group">
                <label class="col-form-label col-form-label-lg">Etes-vous satisfait du déroulement de vos entretiens ? </label>
                <select class="form-control form-control-lg">
                    <option value="">Tout a fait oui</option>
                    <option value="">Plutot oui</option>
                    <option value="">Plutot non</option>
                    <option value="">Pas du tout</option>
                </select>
            </div>
            <div class="col-12 form-group">
                <label class="col-form-label col-form-label-lg">Avez-vous des remarques à partager sur le déroulement de vos entretiens ?</label>
                <input type="text" class="form-control form-control-lg">
            </div>
            <div class="col-12 form-group">
                <label class="col-form-label col-form-label-lg">Selon vous, quels sont les points positifs du Forum virtuel? </label>
                <input type="text" class="form-control form-control-lg">
            </div>
            <div class="col-12 form-group">
                <label class="col-form-label col-form-label-lg">Selon vous, quelles sont les améliorations à apporter ?</label>
                <input type="text" class="form-control form-control-lg">
            </div>
            <div class="col-12 form-group">
                <label class="col-form-label col-form-label-lg">Avez-vous d'autres retours/suggestions à partager concernant le Forum virtuel? </label>
                <input type="text" class="form-control form-control-lg">
            </div>
            <div class="col-12 form-group text-center">
                <button class="btn btn-vue btn-lg col-4">Valider</button>
            </div>
        </div>
    </form>
</template>

<script>
export default {
name: 'Form'
}
</script>

<style>
.btn-vue{
    background: #53B985;
    color: #31485D;
    font-weight: bold;
}
</style>