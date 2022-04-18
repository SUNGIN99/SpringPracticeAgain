import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//main 클래스
// 프로젝트 가장 상단에 위치

// template에 html 파일이 들어갈거임(view파일)
@SpringBootApplication
public class SpringbootPracApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringbootPracApplication.class, args);
    }

}
