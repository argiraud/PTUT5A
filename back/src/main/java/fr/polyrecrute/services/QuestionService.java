package fr.polyrecrute.services;

import fr.polyrecrute.models.Entity__;
import fr.polyrecrute.models.File__;
import fr.polyrecrute.models.Question__;
import fr.polyrecrute.responceType.Question;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QuestionService {
    void create(Question__ question);
    List<Question__> findQuestionByNoQuestion(long questionId);

    List<Question> getTransactionalObjectList(List<Question__> questions);
}
