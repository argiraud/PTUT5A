import {axiosAPI} from "@/http-common";

class SurveyDataService {
    addAnswer(question) {
        return axiosAPI.post("/user/question", question);
    }

    getAnswer(questionId){
        return axiosAPI.post(`/user/question/${questionId}`)
    }
}

export default new SurveyDataService();