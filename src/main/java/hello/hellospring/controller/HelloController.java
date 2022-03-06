package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버(view Resolver)가 화면을 찾아서 return 해준다.
 * resources : templates /  (name)  .html
 *
 * 콘솔로 build >  gradlew 파일을 빌드해주고 생성된 빌드 폴더에서 빌더 라이브러리를 실행시켜주면된다.
 * ./gradlew build
 * cd build/libs
 * java -jar hello-spring--0.0.1-SNAPSHOT.jar
 * 실행확인
 *
 * 파라미터 옵션 확인 : 컨트롤 + p
 *
 */
@Controller
public class HelloController {


    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("/hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }

    /**
     * @ResponseBody를 쓰면 내가 직접 HTTP의 BODY에 문자 내용을 직접 반환한다.
     * 문자, 데이터를 직접 반환
     * 문자는 그냥 문자그대로 넘기는데
     * 객체를 반환하면 json 방식으로 데이터를 만들어서 반환 (기본 정책)
     * 1.HttpMessageConverter가 먼저 동작하고
     * 2. 문자면 StringConverter, 객체면 JsonConverter가 동작해
     *
     * @param name
     * @return
     */
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        
        return "hello"+name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        // 컨트롤 + 시프트 + 엔터

        return hello;
    }

    static class Hello{
        /**
         *   Generator 단축키
         *   alt + insert
         */
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
