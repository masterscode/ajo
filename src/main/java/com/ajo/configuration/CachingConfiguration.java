package com.ajo.configuration;

import net.sf.ehcache.config.CacheConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfiguration extends CachingConfigurerSupport {


    @Bean
    public net.sf.ehcache.CacheManager ehCacheManagerConfig(){

        CacheConfiguration configuration = new CacheConfiguration();
        configuration.setName("collection-cache");
        configuration.setMemoryStoreEvictionPolicy("LRU");
        configuration.setMaxEntriesLocalHeap(1024);
        configuration.setTimeToLiveSeconds(1000);

        net.sf.ehcache.config.Configuration ehCacheConfiguration = new net.sf.ehcache.config.Configuration();

        ehCacheConfiguration.addCache(configuration);

        return net.sf.ehcache.CacheManager.newInstance();

    }

    @Bean
    public @Override
    CacheManager cacheManager(){
        return new EhCacheCacheManager(ehCacheManagerConfig());
    }
}
