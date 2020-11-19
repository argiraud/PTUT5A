import axios from "axios";

export default axios.create({
    baseURL: "https://api.polyrecrute.tk",
    headers: {
        "Content-type": "application/json",
        "Authorization": "Bearer " + window.sessionStorage.getItem("UserToken")
    }
});