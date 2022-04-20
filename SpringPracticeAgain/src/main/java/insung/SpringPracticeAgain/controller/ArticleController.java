package insung.SpringPracticeAgain.controller;

import insung.SpringPracticeAgain.Repository.ArticleRepository;
import insung.SpringPracticeAgain.dto.ArticleForm;
import insung.SpringPracticeAgain.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("articles/new")
    public String newDtoForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        System.out.println(form.toString());
        Article article = form.toEntity();
        System.out.println(form.toString());
        Article saved = articleRepository.save(article);
        System.out.println(saved.toString());

        return "";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable long id, Model model){
        log.info("id = " + id);

        Article articleEntity = articleRepository.findById(id).orElse(null);
        //Optional 역할 : id 값 찾았는데 id 값이 없다면? null로 반환해줌(Optional은 null과 관련되어있음)

        //view에 닮아줄려면 model
        model.addAttribute("article", articleEntity);

        return "articles/show";
    }

}
