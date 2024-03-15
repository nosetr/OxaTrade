package com.nosetr.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import io.r2dbc.spi.ConnectionFactory;

/**
 * Transaction management with R2DBC
 * <p><b>@Transactional</b> annotation is always in effect with a reactive data
 * access layer with R2DBC and the functionality can be activated at any time
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.2
 * @see   https://blog.ippon.fr/2022/03/02/comment-bien-sentendre-avec-avec-r2dbc-ou-pas/
 */
@Configuration
@EnableTransactionManagement	// R2dbcTransactionManager
public class TransactionsConfig {

	private final ConnectionFactory connectionFactory; // Stellen Sie sicher, dass dies Ihre R2DBC ConnectionFactory ist

	public TransactionsConfig(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	@Bean
	public ReactiveTransactionManager transactionManager() {
		return new R2dbcTransactionManager(connectionFactory);
	}
}
