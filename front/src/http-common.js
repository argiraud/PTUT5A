import axios from "axios";
import {router} from "./main"

export const axiosAPI = axios.create({
    baseURL: "https://api.polyrecrute.tk",
    headers: {
        "Access-Control-Allow-Origin": "*",
        "Content-type": "application/json",
        "Authorization": window.sessionStorage.getItem("UserToken")
    }
});

// Add a request interceptor
axiosAPI.interceptors.request.use(
    config => {
        const token = window.sessionStorage.getItem("UserToken");
        if (token) {
            config.headers['Authorization'] = token;
        }
        config.headers['Access-Control-Allow-Origin'] = '*';
        config.headers['Content-Type'] = 'application/json';
        return config;
    },
    error => {
        Promise.reject(error)
    });

//Add a response interceptor

axiosAPI.interceptors.response.use((response) => {
    return response
}, function (error) {
    const originalRequest = error.config;

    if (error.response.status === 401) {
        console.log("connexion");
        router.push('Connexion').catch(err=>err);
    return Promise.reject(error);
}

if (error.response.status === 401 && !originalRequest._retry) {

    originalRequest._retry = true;
    // const refreshToken = localStorageService.getRefreshToken();
    // return axios.post('/auth/token',
    //     {
    //         "refresh_token": refreshToken
    //     })
    //     .then(res => {
    //         if (res.status === 201) {
    //             localStorageService.setToken(res.data);
    //             axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorageService.getAccessToken();
    //             return axios(originalRequest);
    //         }
    //     })
}
return Promise.reject(error);
})
;