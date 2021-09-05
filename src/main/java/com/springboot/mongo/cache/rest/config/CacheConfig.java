package com.springboot.mongo.cache.rest.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.springboot.mongo.cache.rest.mongo.MongoCacheBuilder;
import com.springboot.mongo.cache.rest.mongo.MongoCacheManager;

@Configuration
public class CacheConfig extends CachingConfigurerSupport {
	@Autowired
	private MongoTemplate mongoTemplate;

	@Bean
	public CacheManager cacheManager() {
		// Create a "cacheName" cache that will use the collection "collectionName" with a TTL 7 days.
		MongoCacheBuilder cache = MongoCacheBuilder.newInstance("user", mongoTemplate, "users");
		cache.withTTL(604800);
		Collection<MongoCacheBuilder> caches = new ArrayList<>();
		caches.add(cache);

		// Create a manager which will make available the cache created previously.
		return new MongoCacheManager(caches);
	}

	@Bean("customKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new CustomKeyGenerator();
    }
}
