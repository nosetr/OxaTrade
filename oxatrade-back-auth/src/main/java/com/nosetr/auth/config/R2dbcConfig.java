package com.nosetr.auth.config;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

import io.r2dbc.spi.ConnectionFactory;

/**
 * R2dbc custom configuration
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@Configuration
public class R2dbcConfig extends AbstractR2dbcConfiguration {

//	@Value("${spring.r2dbc.host}")
//	private String host;
//
//	@Value("${spring.r2dbc.port}")
//	private int port;
//
//	@Value("${spring.r2dbc.username}")
//	private String username;
//
//	@Value("${spring.r2dbc.password}")
//	private String password;
//
//	@Value("${spring.r2dbc.database}")
//	private String databaseName;

//	@Bean
	@Override
	public ConnectionFactory connectionFactory() {
//		return MySqlConnectionFactory.from(
//				MySqlConnectionConfiguration.builder()
//						.host(host)
//						.port(port)
//						.username(username)
//						.password(password)
//						.database(databaseName)
//						.build()
//		);
		return null;
	}

	/*
	 * Add my customer converters to super
	 */
	@Bean
	@Override
	public List<Object> getCustomConverters() {

		List<Object> converters = new ArrayList<>();
		converters.add(new UUIDWriteConverter());
		converters.add(new UUIDReadConverter());
		//TODO To remove after error will be fix:
		converters.add(new AfterRegisterConverter());

		return converters;
	}

}

/**
 * Convert UUID to byte
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@WritingConverter
class UUIDWriteConverter implements Converter<UUID, byte[]> {
	@Override
	public byte[] convert(final UUID source) {
		final var bb = ByteBuffer.wrap(new byte[16]);
		bb.putLong(source.getMostSignificantBits());
		bb.putLong(source.getLeastSignificantBits());
		return bb.array();
	}
}

/**
 * Convert byte to UUID
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@ReadingConverter
class UUIDReadConverter implements Converter<byte[], UUID> {
	@Override
	public UUID convert(final byte[] source) {
		final var bb = ByteBuffer.wrap(source);
		final var firstLong = bb.getLong();
		final var secondLong = bb.getLong();
		return new UUID(firstLong, secondLong);
	}
}

/**
 * Without this converter it will be an Error by response after registration on
 * [POST] http://localhost:8083/api/v1/auth/register:
 * <p>{@code "No converter found capable of converting from type [java.lang.Long] to type [java.util.UUID]"}
 * <p>TODO fix error and remove this converter
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.0
 */
@ReadingConverter
class AfterRegisterConverter implements Converter<Long, UUID> {

	/*
	 * will exchange UUID from response to null
	 */
	@Override
	public UUID convert(Long source) {
		return null;
	}

}
