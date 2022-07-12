package com.dreamkite.utTestUtils;


import com.dreamkite.pg.common.SpringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class CommonSqlUtils {

    /**
     * @param sqlPath sql文件的路径，如“sql/initData.sql”
     */
    public void execute(String sqlPath) throws SQLException {
        ClassPathResource classPathResource = new ClassPathResource(sqlPath);
        DataSource dataSource = SpringUtils.getBean(DataSource.class);
        Connection connection = dataSource.getConnection();
        ScriptUtils.executeSqlScript(connection, classPathResource);
    }
}
