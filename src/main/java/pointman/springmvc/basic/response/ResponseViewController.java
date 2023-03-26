package pointman.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * http 응답 데이터 3가지
 * 1.정적리소스: 웹브라우저에 정적인 HTML, CSS, js 를 제공하는 정적리소스
 * 2.뷰템플릿: 웹브라우저에 동적인 HTML제공 (thymeleaf)
 * 3.HTTP메시지: http 메세지 body에 json같은 형식의 데이터 전송
 *
 * 정적리소스 경록
 * /static, /public, /resources, /META-INF/resources
 *
 * src/main/resources/static/basic/hello-form.html
 * http://localhost:8088/basic/hello-form.html
 *
 */

@Controller
public class ResponseViewController {
    @RequestMapping("/response-view-v1")
    public ModelAndView responseVIewV1(){
        ModelAndView mav = new ModelAndView("/response/hello")
                .addObject("data","hello");
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseVIewV2(Model model){
        model.addAttribute("data","hello2");
        return "/response/hello";
    }

    /**
     * 권장하지 않는 방법
     */
    @RequestMapping("/response/hello") //return void 이름이 없으면  @RequestMapping("/response/hello") 이 이름의 view 로 간다  직관적이지 않다
    public void responseVIewV3(Model model){
        model.addAttribute("data","hello2");

    }
}
