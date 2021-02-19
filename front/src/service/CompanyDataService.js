import {axiosAPI} from "@/http-common";

class CompanyDataService {
    getAll() {
        return axiosAPI.get("/companies");
    }

    getAllOffers(){
        return axiosAPI.get("/company/offers");
    }

    count(){
        return axiosAPI.get("/kpi/companies");
    }

     get(id) {
         return axiosAPI.get(`/company/${id}/offers`);
    }

    create(data) {
         return axiosAPI.post("/company/offer", data);
     }
    UsersWhoWantedMe(userId){
        return axiosAPI.get(`/company/${userId}/userWhoWanted`)
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
}

export default new CompanyDataService();