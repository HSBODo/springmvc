package pointman.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//@Slf4j
//@Controller  return viewName
@RestController //return http body에 데이터 입력  API를 만들 때 사용

public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());   //@Slf4j 어노테이션 사용시 롬북이 자동으로 처리해줌

    @RequestMapping("/log-test")
    public  String logTest(){
        String name = "Spring";
        String name2 = "Spring2";

        System.out.println("name=" + name);
        //로그의 올바른 사용법
        log.trace("trace my log= {},{}="+name); //trace 로그가 필요 없는데 더하기 연산을 하여 필요없는 리소스를 사용한다 성능 저하 원인!!  연산 후 최종 로그를 만든 뒤 분기를 타서 호출 안함
        log.trace("trace log= {},{}",name,name2); //trace 로그가 필요 없는데 메소드만 호출해 파라미터만 넘김 연산하지 않음  성능 저하 하지않음 !! 메소드 호출 후 분기를 차서 호출 안함

        log.debug("debug log= {},{}",name,name2);
        log.info("info log={}",name);
        log.warn("warn log= {},{}",name,name2);
        log.error("error log= {},{}",name,name2);
        //2023-03-25 18:54:17.394  INFO 15244 --- [nio-8088-exec-1] p.springmvc.basic.LogTestController      : info log=Spring
        // 시간                        프로세스ID    현재 실행한 스레드
        return "ok";
    }
}
