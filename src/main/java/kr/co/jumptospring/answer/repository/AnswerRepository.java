package kr.co.jumptospring.answer.repository;

import kr.co.jumptospring.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
