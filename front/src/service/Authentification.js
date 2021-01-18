import http from "../http-common";

class Authentification {

    signin(email, mdp){
        return http.get(`/auth/signin?email=${email}&password=${mdp}`);
    }

    companySignUp(data){
        return http.post("/auth/company/signup",data);
    }

    userSignUp(data){
        return http.post("/auth/user/signup",data);
    }
}

export default new Authentification();