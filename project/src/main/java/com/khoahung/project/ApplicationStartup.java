package com.khoahung.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

@Component
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
  
	Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);
	

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logger.info("StartupListener is starting...");
		try {
			logger.info("Setup Database...");
			initDatabase();
		}catch(Exception ex) {
			logger.error("Setup database error:"+ex.getMessage());
		}
	}
	public void initDatabase() throws SQLException, LiquibaseException{
		Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5455/liquibase",
				"postgres", "123123a@");

		Database database = DatabaseFactory.getInstance()
				.findCorrectDatabaseImplementation(new JdbcConnection(c));

		Liquibase liquibase = new Liquibase("classpath:db-changelog.xml", new ClassLoaderResourceAccessor(), database);
		liquibase.update("");
		liquibase.close();
	}
}