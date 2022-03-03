package com.zeng;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class Springboot04DataApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass()); //class com.zaxxer.hikari.HikariDataSource

        Connection connection = dataSource.getConnection();

        System.out.println(connection);

        // xxx Template  :springboot已经配置好了模板的bean，开箱即用 CRUD


        connection.close();
    }

}
