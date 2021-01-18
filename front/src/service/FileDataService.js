import {axiosAPI} from "@/http-common";

class FileDataService {
    uploadUserFile(file, onUploadProgress) {
        let formData = new FormData();

        formData.append("file", file);

        return axiosAPI.post("/user/uploadFile", formData, {
            headers: {
                "Content-Type": "multipart/form-data"
            },
            onUploadProgress
        });
    }

    getFiles() {
        return axiosAPI.get("/files");
    }
}

export default new FileDataService();