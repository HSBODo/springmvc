package pointman.springmvc.basic.request;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {
    @PostMapping("/request-body-String-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream= request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//Stream은 byte코드이다 그래서 byte코드를 문자로 바꿀 때에는 항상 encoding을 지정 해 줘야 한다. 설정 안하면 default 설정을 따라간다.
        log.info("messageBody={}",messageBody);
        response.getWriter().write("ok");

    }

    @PostMapping("/request-body-String-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//Stream은 byte코드이다 그래서 byte코드를 문자로 바꿀 때에는 항상 encoding을 지정 해 줘야 한다. 설정 안하면 default 설정을 따라간다.
        log.info("messageBody={}",messageBody);
        responseWriter.write("ok");

    }

    /**
     * HttpEntity
     * http요청 파라미터 조회 form 데이터 및 쿼리스트링 데이터에는 @RequestParam @ModelAttribute 사용하지만
     * body에 json xml text 정보들을 조회 할 때는 HttpEntity 사용
     * Http header,body 정보를 편리하게 조회가능
     * 응답에도 사용가능
     * view조회는 사용 불가능
     *
     * RequestEntity<> 요청 body 조회
     * ResponseEntity<></> 응답 status 코드 생성 가능
     */

    @PostMapping("/request-body-String-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}",messageBody);
        return new HttpEntity<>("ok");

    }
    @PostMapping("/request-body-String-v33")
    public HttpEntity<String> requestBodyStringV33(RequestEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}",messageBody);
        return new ResponseEntity<>("ok",HttpStatus.CREATED);

    }


    /**
     *  요청 파라미터 vs http 메시지 바디
     *  요청파라미터 조회하는 기능 (GET 쿼리스트링,x-www-form-urlencoded(html form)) @RequestParam, @ModelAttribute 사용
     *  HTTP 메시지 바디 직접 조회하는 기능 @ResponseBody 사용
     */

    @ResponseBody
    @PostMapping("/request-body-String-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {

        log.info("messageBody={}",messageBody);
        return "ok";

    }
}
