package fr.polyrecrute.responceType;


public class Question {

    private Long noQuestion;
    private String answer;
    private User user;

    public Question(Long noQuestion, String answer, User user) {
        this.noQuestion = noQuestion;
        this.answer = answer;
        this.user = user;
    }

    public Long getNoQuestion() {
        return noQuestion;
    }

    public String getAnswer() {
        return answer;
    }

    public User getUser() {
        return user;
    }
}
