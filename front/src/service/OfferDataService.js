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

    getOfferById(idOffer) {
        return axiosAPI.get(`/company/${idOffer}`);
    }

    create(data) {
        return axiosAPI.post("/company/offer", data);
    }

    delete(id) {
        return axiosAPI.delete(`/company/offer?idOffer=${id}`);
    }

    update(offer){
        return axiosAPI.patch(`/company/offer`, offer);
    }

    uploadFile(file, idOffer) {
        let formData = new FormData();

        formData.append("file", file);

        return axiosAPI.post(`company/${idOffer}/uploadFile`, formData, {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        })
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