import http from "../http-common";

class FileDataService {
    uploadUserFile(file, onUploadProgress) {
        let formData = new FormData();

        formData.append("file", file);

        return http.post("/user/uploadFile", formData, {
            headers: {
                "Content-Type": "multipart/form-data"
            },
            onUploadProgress
        });
    }

    getFiles() {
        return http.get("/files");
    }
}

export default new FileDataService();