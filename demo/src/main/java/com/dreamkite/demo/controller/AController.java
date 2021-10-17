package com.dreamkite.demo.controller;

import com.dreamkite.demo.model.AModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 1.@ApiParam ，是注解api的参数 ，也就是用于swagger提供开发者文档 ，文档中生成的注释内容 。
 * <p>
 * 2.@RequestParam ， 是获取前端传递给后端的参数，可以是get方式，也可以是post方式。其中如果前端传递的参数和后端你接受的参数起的名字字段是一致的可以省略不写，也可以直接写@RequestParam String title,如果不一致一定要完整写，
 * <p>
 * 3.@PathVariable ， 获取url后面参数，进行参数绑定
 */
@Api(value = "一个控制器")
@RestController
@Slf4j
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

    /**
     * 浏览器输入：http://localhost:8080/hi/model/1
     *
     */
    @ApiOperation(value = "测试hiWithModelRet", notes = "测试hiWithModelRet", httpMethod = "GET")
    @RequestMapping(value = "/hi/model/{id}", method = RequestMethod.GET)
    public AModel hiWithModelRet(@ApiParam(name = "id", value = "model Id", required = true) @PathVariable String id) {
        AModel aModel = new AModel(id, "");
        aModel.setId(id);
        return aModel;
    }

    /**
     * usage: 浏览器输入：http://localhost:8080/hi/model/1
     * 注意：应该要加上@Valid ,否则不会验证AModel的参数是否符合@NonNull!！
     *
     */
    @ApiOperation(value = "测试hiWithModeBody", notes = "测试hiWithModeBody", httpMethod = "POST")
    @RequestMapping(value = "/hi/model", method = RequestMethod.POST)
    public String hiWithModeBody(@Valid @RequestBody AModel model) {
        log.info("Start to update " + model.toString());
        return "success";
    }
}
