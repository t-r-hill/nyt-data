package co.LabsProjects.nytdata.configuration;

import co.LabsProjects.nytdata.model.Doc;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;
import java.time.Duration;

@Configuration
public class CacheConfiguration {

    @Bean
    public CacheManager ehCacheManager(){
//        return CacheManagerBuilder.newCacheManagerBuilder()
//                .withCache("docs",
//                        CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Doc.class, ResourcePoolsBuilder.heap(10)))
//                .build(true);

        org.ehcache.config.CacheConfiguration<String, Doc> cacheConfig = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class,
                        Doc.class,
                        ResourcePoolsBuilder.newResourcePoolsBuilder()
                                .offheap(10, MemoryUnit.MB)
                                .build())
                .withExpiry(ExpiryPolicyBuilder.timeToIdleExpiration(Duration.ofSeconds(10)))
                .build();

        CachingProvider cachingProvider = Caching.getCachingProvider();
        javax.cache.CacheManager cacheManager = cachingProvider.getCacheManager();

        javax.cache.configuration.Configuration<String, Doc> configuration = Eh107Configuration.fromEhcacheCacheConfiguration(cacheConfig);
        cacheManager.createCache("cacheStore", configuration);
        return cacheManager;
    }
}
