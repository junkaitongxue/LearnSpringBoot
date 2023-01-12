package com.dreamkite.demo.controller;

import com.dreamkite.demo.common.utils.ExportUtils;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "导出导入控制器")
@RestController
@RequestMapping("/export")
public class ExportController {

    @GetMapping("/getDemo")
    public void getDemo() {
        ExportUtils.exportDemo("classpath:templates/XXX_import_demo.xlsx");
    }


}
