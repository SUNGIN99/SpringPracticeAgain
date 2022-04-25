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

import java.util.List;
import java.util.Optional;

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

        return "redirect:/articles/" + saved.getId();
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

    @GetMapping("/articles")
    public String index(Model model) {
        List<Article> articleEntityList = articleRepository.findAll();
        model.addAttribute("articleList", articleEntityList);
        return "/articles/index";
    }

    @GetMapping("/articles/revise/{id}") // 22.04.25 내가 하려고한거.. show 페이지에서 revise 페이지로 넘어가면됨
    public String revise(@PathVariable long id, Model model){
        log.info("id = " + id);
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);

        return"/articles/revise";
    }

    // 22.04.25 강사님의 -------------------------------------------------------------------
    // url로 id를 받고 edit파일을 만듬
    @GetMapping("/articles/{id}/edit")// controller 에서는 괄호 하나만 사용할것
    public String edit(@PathVariable long id, Model model){
        Article articleEntity =articleRepository.findById(id).orElse(null);
        model.addAttribute("article", articleEntity);

        return "articles/edit";
    }
    //https://dejavuhyo.github.io/posts/get-http-post-body-in-springboot/
    // @PatchMapping => form 에서는 patch방식을 지원하지 않아서 사용안함,...
    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
        Article articleEntity = form.toEntity();
        log.info(articleEntity.toString());
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        log.info(target.toString());

        if(target != null){
            articleRepository.save(articleEntity);
        }

        return ("redirect:/articles/" + articleEntity.getId());
    }
}
