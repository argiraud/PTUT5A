import {axiosAPI} from "@/http-common";

class WishDataService {
    getVoeuxEntreprise(id){
        return axiosAPI.get(`/company/${id}/wantedUsers`);
    }

    getVoeuxEtudiant(id){
        return axiosAPI.get(`/user/${id}/wantedOffers`);
    }

    createVoeux(idUser) {
        return axiosAPI.post(`/company/wantedUser?idUser=${idUser}`);
    }

    createVoeuxEtudiant(idOffer) {
        return axiosAPI.post(`/user/wantedOffer?idOffer=${idOffer}`);
    }

    deleteVoeuxEntreprise(id) {
        return axiosAPI.delete(`/company/wantedUser?idUser=${id}`);
    }

    deleteVoeuxEtudiant(id) {
        return axiosAPI.delete(`/user/wantedOffer?idOffer=${id}`);
    }


    //
    // update(id, data) {
    //     return http.put(`/tutorials/${id}`, data);
    // }
    //
    // delete(id) {
    //     return http.delete(`/tutorials/${id}`);
    // }
    //
    // deleteAll() {
    //     return http.delete(`/tutorials`);
    // }
    //
    // findByTitle(title) {
    //     return http.get(`/tutorials?title=${title}`);
    // }
}

export default new WishDataService();