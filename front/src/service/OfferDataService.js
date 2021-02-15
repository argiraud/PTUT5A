import {axiosAPI} from "@/http-common";

class OfferDataService {
    getAll() {
        return axiosAPI.get("/companies");
    }

    count(){
        return axiosAPI.get("/kpi/companies");
    }

     get(id) {
         return axiosAPI.get(`/company/${id}/offer`);
     }

    getOffer() {
        return axiosAPI.get(`/company/offers`);
    }

    create(data) {
        return axiosAPI.post("/company/offer", data);
    }

    delete(id) {
        return axiosAPI.delete(`/company/offer?idOffer=${id}`);
    }

    uploadFile(formData, idOffer) {
        return axiosAPI.post("company/offer/uploadFile", formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        }, idOffer)
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

export default new OfferDataService();