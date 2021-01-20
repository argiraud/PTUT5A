import {axiosAPI} from "@/http-common";

class StudentDataService {
    getAll() {
        return axiosAPI.get("/students");
    }

    count(){
        return axiosAPI.get("/kpi/users");
    }

     getConnectedUser() {
         return axiosAPI.get(`/user/details`);
     }

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