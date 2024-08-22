package kr.co.jumptospring.question.controller;

import jakarta.validation.Valid;
import kr.co.jumptospring.question.QuestionForm;
import kr.co.jumptospring.question.entity.Question;
import kr.co.jumptospring.question.repository.QuestionRepository;
import kr.co.jumptospring.question.service.QuestionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/list")
//    @ResponseBody
    public String list(Model model){
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id){
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(){
        return "question_form";
    }

//    @PostMapping("/create")
//    public String questionCreate(
//            @RequestParam(value = "subject") String subject,
//            @RequestParam(value = "content") String content){
//        this.questionService.create(subject,content);
//        return "redirect:/question/list";
//    }

    @PostMapping("/create")
    public String questionCreate(
            @Valid QuestionForm questionForm,
            BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "question_form";
        }

        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";
    }
}
