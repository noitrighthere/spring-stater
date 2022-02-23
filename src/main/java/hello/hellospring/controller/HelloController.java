package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    /* 정적 컨텐츠 */
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "spring!");
        return "hello"; // templates 폴더에 있는 hello로 가게 됨
    }

    /* MVC와 템플릿 엔진 */
    @GetMapping("hello-mvc")
    // required 기본 옵션이 true이기 때문에 localhost:8080/hello-mvc 만 쓰면 오류남, required=false를 해줘야 함
    public String helloMvc(@RequestParam(name = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    /* API */
    @GetMapping("hello-string")
    @ResponseBody   // http 응답 바디에 직접 넣어줌
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
