package karn.demo.ehcachedemo;

import karn.demo.ehcachedemo.app.User;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.xml.XmlConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import static org.ehcache.config.builders.CacheManagerBuilder.newCacheManager;

@SpringBootApplication
//@EnableCaching --we don't need this for current configuration
public class EhCacheDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EhCacheDemoApplication.class, args);
    }

    @Bean
    public org.ehcache.CacheManager cacheManager() {
        org.ehcache.CacheManager cacheManager = newCacheManager(
                new XmlConfiguration(getClass().getResource("/dev/ehcache.xml")));
        cacheManager.init();
        return cacheManager;
    }

    @Bean("myUserCache")
    public Cache getMyUserCache(@Autowired CacheManager cacheManager){
        return cacheManager.getCache("myUserCache",Long.class, User.class);
    }
}
