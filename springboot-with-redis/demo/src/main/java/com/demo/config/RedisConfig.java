package com.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import com.demo.controller.EmployeeController;

@Configuration
public class RedisConfig {

	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	// Setting up the Jedis connection factory.
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		log.info("Redis connection factory...");
		return new JedisConnectionFactory();
	}

	// Setting up the Redis template object.
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		final RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		log.info("RedisTemplate...");
		return redisTemplate;
	}

}
