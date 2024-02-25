package com.nosetr.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

import io.r2dbc.spi.ConnectionFactory;

/**
 * The Database Initializer / DDL Statements / Table Creation
 * <p>Unlike <a href="https://testcontainers.com/">Testcontainer</a> JDBC
 * module, R2DBC module does not support out-of-the-box mechanism to execute
 * initializer/bootstrap script. That is definitely a shortcoming and hope to
 * see this support in near future. Following code snippet can be used as a
 * workaround for this need.
 * 
 * @autor         Nikolay Osetrov
 * @since         0.1.4
 * @see           https://medium.com/@susantamon/r2dbc-with-testcontainers-for-spring-boot-webflux-integration-test-3822b7819039
 * @Configuration
 */
@Configuration
public class TestDatabaseConfiguration {

	@Bean
	public ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {
		ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
		initializer.setConnectionFactory(connectionFactory);

		CompositeDatabasePopulator populator = new CompositeDatabasePopulator();
		// The Database Initializer Script (schemaTestDB.sql)
		populator.addPopulators(
				new ResourceDatabasePopulator(
						new ClassPathResource("schemaTestDB.sql")
				)
		);
		initializer.setDatabasePopulator(populator);

		return initializer;
	}
}
