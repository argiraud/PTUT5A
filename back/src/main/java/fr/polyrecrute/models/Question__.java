package fr.polyrecrute.models;

import fr.polyrecrute.responceType.Question;

import javax.persistence.*;

@javax.persistence.Entity(name = "Question")
@Table(name = "question")
public class Question__ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    private Long noQuestion;

    @Column(length = 500)
    private String answer;

    @ManyToOne(targetEntity = Entity__.class, fetch = FetchType.LAZY)
    private Entity__ entity;

    public Question__() {
    }

    public Question__(Long noQuestion, String answer, Entity__ entity) {
        this.noQuestion = noQuestion;
        this.answer = answer;
        this.entity = entity;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public Long getNoQuestion() {
        return noQuestion;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Question getTransactionalObject() {
        return new Question(noQuestion, answer, entity.getUser().getTransactionalObject());
    }
}
