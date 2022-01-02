package karn.demo.ehcachedemo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.net.URI;

@Configuration
public class MyConfiguration {

    @Value("${cache.xml.path}")
    private String xmlPath;

    @Bean
    public CacheManager cacheManager() {
        CachingProvider cachingProvider = Caching.getCachingProvider();
        return cachingProvider.getCacheManager(URI.create(xmlPath), getClass().getClassLoader());
    }

    @Bean("myUserCache")
    public Cache<Long, User> getMyUserCache(@Autowired CacheManager cacheManager){
        return cacheManager.getCache("myUserCache",Long.class, User.class);
    }
}
