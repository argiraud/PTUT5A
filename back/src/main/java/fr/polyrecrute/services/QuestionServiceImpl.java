package fr.polyrecrute.services;

import fr.polyrecrute.models.Offer__;
import fr.polyrecrute.models.Question__;
import fr.polyrecrute.repository.QuestionRepository;
import fr.polyrecrute.responceType.Offer;
import fr.polyrecrute.responceType.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {


    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void create(Question__ question) {
        questionRepository.save(question);
    }

    @Override
    public List<Question__> findQuestionByNoQuestion(long questionId) {
        return questionRepository.findAllByNoQuestion(questionId);
    }


    @Override
    public List<Question> getTransactionalObjectList(List<Question__> questions){
        List<Question> question = new ArrayList<>();
        for (Question__ q : questions){
            question.add(q.getTransactionalObject());
        }
        return question;
    }
}
