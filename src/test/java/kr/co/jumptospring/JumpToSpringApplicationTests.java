package kr.co.jumptospring;

import jakarta.transaction.Transactional;
import kr.co.jumptospring.answer.entity.Answer;
import kr.co.jumptospring.question.entity.Question;
import kr.co.jumptospring.answer.repository.AnswerRepository;
import kr.co.jumptospring.question.repository.QuestionRepository;
import kr.co.jumptospring.question.service.QuestionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JumpToSpringApplicationTests {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerRepository answerRepository;

    /*
    기본적으로 지연로딩으로 수행되기 때문에 Question 객체를 조회하는 순간 -> DB의 세션은 만료된다
    그러나, 실제 서버에서는 JPA 프로그램들을 실행 할 때 DB 세션이 종료되지 않아 정상적으로 작동 한다.
    Test에서도 정상적으로 작동시키고 싶으면 @Transcational 어노테이션을 사용해서 메소드가 종료될 때 까지 DB의 세션을 유지하면 된다.
     */
//    @Transactional
    @Test
    void testJpa() {
        for(int i = 1; i <= 300; i++){
            String subject = String.format("테스트 데이터입니다:[%03d]", i);
            String content = "더미";
            this.questionService.create(subject, content, null);
        }
    }

}
