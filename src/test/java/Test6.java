import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Slf4j
public class Test6 {
    static {
        log.debug(System.getProperty("java.class.path").replace(";","\n"));
    }
    // Optional
     public static void main(String[] args) {
         // 获得Optional的对象
         Optional<String> optional = Optional.empty();
         String str2 = "hello world";
         Optional<String> strOpt = Optional.of(str2);
         String orElseResult = strOpt.orElse("orElseResult：bb");// strOpt里是null时返回 默认值”bb“
         String orElseGet = strOpt.orElseGet(()-> "orElseGet:"+str2);// strOpt里时null时返回 一个经过supply的默认值
         String orElseThrow = strOpt.orElseThrow(IllegalAccessError::new);// 。。
         strOpt.filter(s -> {return s.equals("aa");});
         strOpt.ifPresent(str->{
             log.info(String.format("ifPresent:%s",str));
         });
         Optional<String> optionalMap= strOpt.map(s -> s+"ccccccc");
         log.info(optionalMap.get());
         String str1 = null;

         Optional<String> optionalnull = Optional.ofNullable(str1);
         log.info(optionalnull.orElse("bb"));


     }
}
