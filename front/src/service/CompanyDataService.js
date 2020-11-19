import http from "../http-common";

class CompanyDataService {
    getAll() {
        return http.get("/companies");
    }

    count(){
        return http.get("/kpi/companies");
    }

    // get(id) {
    //     return http.get(`/tutorials/${id}`);
    // }
    //
    // create(data) {
    //     return http.post("/tutorials", data);
    // }
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

export default new CompanyDataService();