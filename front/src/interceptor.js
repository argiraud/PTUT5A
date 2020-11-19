import http from '@/http-common';

export default function setup() {
    http.interceptors.request.use(function (config) {
        const token = window.sessionStorage.getItem("UserToken");
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    }, function (err) {
        return Promise.reject(err);
    });
}