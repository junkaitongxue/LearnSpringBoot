package com.dreamkite.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * 1.@ApiParam ，是注解api的参数 ，也就是用于swagger提供开发者文档 ，文档中生成的注释内容 。
 * <p>
 * 2.@RequestParam ， 是获取前端传递给后端的参数，可以是get方式，也可以是post方式。其中如果前端传递的参数和后端你接受的参数起的名字字段是一致的可以省略不写，也可以直接写@RequestParam String title,如果不一致一定要完整写，
 * <p>
 * 3.@PathVariable ， 获取url后面参数，进行参数绑定
 */
@Api(value = "一个控制器")
@RestController
public class AController {


    /**
     * 浏览器输入：http://localhost:8080/hi
     *
     * @return hi
     */
    @ApiOperation(value = "测试Hi", notes = "测试Hi", httpMethod = "GET")
    @GetMapping("/hi")
    public String hi() {
        return "hi, springboot.";
    }

    /**
     * 浏览器输入：http://localhost:8080/hi/dreamkite
     *
     * @return hi
     */
    @ApiOperation(value = "测试HiWithParam", notes = "测试HiWithParam", httpMethod = "GET")
    @RequestMapping(value = "/hi/{myName}", method = RequestMethod.GET)
    public String hiWithParam(@ApiParam(name = "myName", value = "我的名字", required = true) @PathVariable String myName) {
        return "hi, springboot. I am " + myName + ".";
    }

}
