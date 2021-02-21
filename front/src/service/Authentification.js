import {axiosAPI} from "@/http-common";

class Authentification {

    signin(email, mdp){
        return axiosAPI.get(`/auth/signin?email=${email}&password=${mdp}`);
    }

    companySignUp(data){
        return axiosAPI.post("/auth/company/signup",data);
    }

    userSignUp(data){
        return axiosAPI.post("/auth/user/signup",data);
    }
}

export default new Authentification();