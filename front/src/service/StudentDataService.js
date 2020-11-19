import http from "../http-common";

class StudentDataService {
    // getAll() {
    //     return http.get("/users");
    // }

    count(){
        return http.get("/kpi/users");
    }

    // get(id) {
    //     return http.get(`/user/details/${id}`);
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

export default new StudentDataService();