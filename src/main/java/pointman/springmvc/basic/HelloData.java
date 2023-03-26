package pointman.springmvc.basic;

import lombok.Data;


/**
 * @Data를 사용하면
 * 아래 애노테이션을 자동으로 등록해준다
 * @Getter
 * @Setter
 * @ToString
 * @EqualsAndHashCode
 * @RequredArgsConstructor
 */

@Data
public class HelloData {
    private String username;
    private  int age;
}
