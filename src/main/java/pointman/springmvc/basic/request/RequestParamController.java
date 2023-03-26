package pointman.springmvc.basic.request;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pointman.springmvc.basic.HelloData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RequestParamController {
    /**
     * jar를 사용하면 webapp 경로를 사용할 수 없다
     * resources/static/  정적 리소스 관리
     *
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("username");
        int age =Integer.parseInt( request.getParameter("age"));
        log.info("userName = {}, age ={}",userName,age);

        response.getWriter().write("ok");

    }
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParmaV2(@RequestParam("username") String memberName, @RequestParam("age") int memberAge){
        log.info("userName = {}, age = {}",memberName,memberAge);
        return "ok";

    }
    //requset param 명과 변수 명이 같으면 @RequestParam("XXX") XXX생략가능
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParmaV3(@RequestParam String username, @RequestParam int age){
        log.info("userName = {}, age = {}",username,age);
        return "ok";

    }
    //requset param 명과 변수 명이 같으면 단순타입(String,int,Integer)이면  @RequestParam 생략가능 하지만 직관적이지 않아서 애노테이션 사용하여 직관적으로 표현하는 것도 좋은 방법
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParmaV4( String username,  int age){
        log.info("userName = {}, age = {}",username,age);
        return "ok";

    }

    /**
     * 필수 파라미터 설정
     *  @RequestParam(required = true) http 요청시 필수 파라미터 설정  기본으로 true
     *  @RequestParam(required = false) http 요청시 필수 파라미터가 아니다 없어도 된다
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParmaRequired( @RequestParam(required = true) String username,  @RequestParam(required = false) Integer age){
        // null 과 ""(빈문자)는 다르다 true로 설정해도 빈문자""는 통과 된다
        //int a = null 에러발생 int에는 null이 들어갈 수 없다
        //Integer a = null 성공 Integer는 객체형 이기 때문에 null이 들어 갈 수 있다.
        log.info("userName = {}, age = {}",username,age);

        return "ok";

    }
    /**
     * defaultValue 파라미터 설정
     *  파라미터에 값이 null이거나 ""(빈문자)이면 설정된 값을 대입해 준다
     *  defaultValue 가 있으면 required를 사용할 필요가 없다
     *
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParmaDefault( @RequestParam(required = true,defaultValue = "geust") String username,  @RequestParam(required = false,defaultValue = "-1") int age){
        log.info("userName = {}, age = {}",username,age);

        return "ok";

    }
    /**
     *  파라미터 map으로 조회
     *  Map(key=value) 1:1
     *  MultiValueMap (key=[value1,value2]) 키 한개에 값을 여러개 담을 수 있다 1:N
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParmaMap(@RequestParam Map<String, Objects> parmaMap){
        log.info("userName = {}, age = {}",parmaMap.get("username"),parmaMap.get("age"));

        return "ok";

    }

    /**
     * @ModelAttribute
     * 요청 파라미터 값을 객체로 바인딩해준다
     */

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("userName = {}, age = {}",helloData.getUsername(),helloData.getAge());

        return "ok";

    }
    /**
     * @ModelAttribute 생략도 가능하다
     *
     * @ModelAttribute 과 @RequestParam 둘다 생략가능하다
     * 그러면 어떤 규칙으로 값을 바인등 해줄까?
     * 단순타입("String, int, Integer")은 @RequestParam를 사용하고
     * 나머지 (객체)는 @ModelAttribute를 사용한다. (argument resolver로 지정해둔 타입은 제외 HttpServletRequest 등..)
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        log.info("userName = {}, age = {}",helloData.getUsername(),helloData.getAge());

        return "ok";

    }
}
