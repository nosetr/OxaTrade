package com.nosetr.library.util.factories;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

/**
 * Custom PropertySourceFactory to read a YAML properties file using
 * the {@code @PropertySource} annotation.
 * <p><b>Examp.:</b>
 * <p>{@code @ConfigurationProperties(prefix = "yaml")
 * @PropertySource(value = "classpath:foo.yml", factory =
 * YamlPropertySourceFactory.class)}
 * 
 * @autor Nikolay Osetrov
 * @since 0.1.3
 * @see   https://www.baeldung.com/spring-yaml-propertysource
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {

	private static final Logger logger = Logger.getLogger(YamlPropertySourceFactory.class.getName());

	@Override
	public PropertySource<?> createPropertySource(String name, EncodedResource encodedResource)
			throws IOException {

		// Log the information that the YAML file is being loaded
		logger.info(
				"Loading YAML file: " + encodedResource.getResource()
						.getFilename()
		);

		YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
		factory.setResources(encodedResource.getResource());

		Properties properties = factory.getObject();

		return new PropertiesPropertySource(
				encodedResource.getResource()
						.getFilename(), properties
		);
	}

}
