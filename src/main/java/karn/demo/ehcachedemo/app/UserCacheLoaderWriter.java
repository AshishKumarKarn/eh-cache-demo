package karn.demo.ehcachedemo.app;

import org.ehcache.spi.loaderwriter.CacheLoaderWriter;
import org.springframework.context.ApplicationContext;

public class UserCacheLoaderWriter implements CacheLoaderWriter<Long, User> {
    private UserRepository userRepository;

    public UserCacheLoaderWriter() {
        final ApplicationContext applicationContext = ApplicationContextStaticProvider.applicationContext;
        if (applicationContext != null)
            userRepository = applicationContext.getBean(UserRepository.class);
    }

    @Override
    public User load(Long key) {
        System.out.println("loaderWriter called ");
        return userRepository.findById(key).orElse(null);
    }


    @Override
    public void write(Long key, User value) throws Exception {
        System.out.println("making db call");
        userRepository.save(value);
    }

    @Override
    public void delete(Long key) throws Exception {
        userRepository.deleteById(key);
    }
}
