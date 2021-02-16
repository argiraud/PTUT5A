package fr.polyrecrute.repository;

import fr.polyrecrute.models.Offer__;
import fr.polyrecrute.models.Question__;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question__, Long> {
    Optional<Question__> findByQuestionId(Long questionId);
    List<Question__> findAllByNoQuestion(Long noQuestion);
}
