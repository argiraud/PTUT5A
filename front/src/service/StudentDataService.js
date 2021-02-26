import {axiosAPI} from "@/http-common";

class StudentDataService {
    getAll() {
        return axiosAPI.get("/students");
    }

    count(){
        return axiosAPI.get("/kpi/students");
    }

     getUserById(userId){
         return axiosAPI.get(`/user/details/${userId}`);
     }

    getConnectedUserDetails() {
        return axiosAPI.get(`/user/details/`);
    }

    setNewUserPassword(password) {
        return axiosAPI.patch(`/user/password?password=${password}`);
    }

    updateUserInfos(user){ //On envoie un utilisateur au format JSON
        return axiosAPI.patch(`/user`,user);
    }

    CompaniesWhoWantedMe(userId) {//Retourne la liste des entreprises qui m'ont mis dans leurs voeux
        return axiosAPI.get(`/user/${userId}/cpompanyWhowanted`);
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