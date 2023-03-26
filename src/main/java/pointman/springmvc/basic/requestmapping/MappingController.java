package pointman.springmvc.basic.requestmapping;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = {"/hello-basic","/hello-go"},method = RequestMethod.GET)
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }

    /** 편리한 축약 애노테이션
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @GDeleteMapping
     * @PatchMapping
     */

    @GetMapping(value = "/hello-get")
    public String mappingGetV2(){
        log.info("GetMapping");
        return "GetMapping";
    }

    /**
     * PathVariable (경로 변수)사용    vs localhost:8088/mapping?userId=userA (쿼리파라미터방식)
     * 변수명이 같으면 생략 가능
     *     @GetMapping(value = "/mapping/{userId}")   << 1
     *     public String mappingPath(@PathVariable String userId){   << 1과 변수명이 같으면 ("userId") 생략
     *         log.info("userId={}",data);
     *
     *         return "ok";
     *     }
     * @PathVariable("userId") String userId -> @PathVariable userId
     * /mapping/userA
     */
    @GetMapping(value = "/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){
        log.info("userId={}",data);

        return "ok";
    }

    @GetMapping(value = "/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId,@PathVariable String orderId){
        log.info("userId={}, orderId= {}",userId,orderId);

        return "ok";
    }

    /**
     * 특정 파라미터가 있어야 호출
     * params="mode"
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug"
     * parmas= {"mode=debug","data=good"} or 조건
     */

    //localhost:8088/mapping-param?mode=debug  쿼리파라미터에 mode=debug가 있어야 호출 가능 없으면 bad request 에러
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam(){
        log.info("mappingParam");
        return "ok";

    }

    /**
     * 특정 헤더로 추가 매핑
     * headers="mode"
     * headers="!mode" mode라는 header이름이 없을 때 (키가 없을 때)
     * headers="mode=debug"
     * headers="mode!=debug"
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public  String mappingHeader(){
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * consumes="text/plain"
     * consumes={"text/plain","application/*"}
     * consumes= MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
    public  String mappingConsumes(){
        log.info("mappingConsumes");
        return "ok";
    }


    /**
     * Accept 헤더 기반 Media Type Accept는 요청자(클라이언트)가 받아드릴 수 있는 타입
     * Accept=application/json 이고 produces=text/html 이면  클라이언트는 json을 받아드릴 수 있는데 서버에서는 html을 전송하기 때문에 서로 맞지 않는 타입이라 에러 406(Not Acceptable)
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce",produces = "text/html")
    public String mappingProduces(){
        log.info("mappingProduces");
        return "ok";
    }

}
