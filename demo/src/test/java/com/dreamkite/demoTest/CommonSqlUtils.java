package com.dreamkite.demoTest;

import com.dreamkite.demo.context.SpringContextHolder;

public class CommonSqlUtils {

    public void execute(String sqlPath) {
        SpringContextHolder.getBean(JdbcTemplate.class);

    }
}
