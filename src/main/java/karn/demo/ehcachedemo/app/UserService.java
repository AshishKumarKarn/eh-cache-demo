package karn.demo.ehcachedemo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cache<Long, User> myUserCache;

    public void save(User user) {
        myUserCache.put(user.getId(), user);
    }

    public List<User> getAll() {
        //need to fetch from db as we don't know id/key to verify cache miss
        List<User> all = userRepository.findAll();
        //also update cache
        Map<Long, User> userMap = new HashMap<>();
        all.forEach(user -> userMap.put(user.getId(), user));
        myUserCache.putAll(userMap);
        return all;
    }

    public User getUserById(Long id) {
        return myUserCache.get(id);
    }

}
