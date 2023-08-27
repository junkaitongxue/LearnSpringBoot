package org.dreamkite.pg.feign;


//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

//@FeignClient(name = "common-service")
@Component
public interface CommonFeign {
    String test();
}
