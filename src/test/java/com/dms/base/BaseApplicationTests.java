package com.dms.base;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class BaseApplicationTests {

	@Autowired
	private DataSource dataSource;
	@Test
	public void testconnection(){
		try(Connection connection = dataSource.getConnection()){
			 System.out.println("Database connected: " + connection.getMetaData().getDatabaseProductName());
		} catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
	}

	@Test
	void contextLoads() {
	}

}
