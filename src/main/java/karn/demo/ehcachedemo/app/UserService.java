package karn.demo.ehcachedemo.app;

import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Cache<Long, User> myUserCache;

    public void save(User user) {
        System.out.println("making db call");
        myUserCache.put(user.getId(),user);
        userRepository.save(user);
    }

    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        for (Cache.Entry<Long, User> next : myUserCache) {
            result.add(next.getValue());
        }
        if (result.isEmpty()) {
            System.out.println("making db call");
            result = userRepository.findAll();
            for (User user : result) {
                myUserCache.put(user.getId(), user);
            }
        }

        return result;
    }

    public User getUserById(Long id) {
        User user = myUserCache.get(id);
        if (user == null) {
            System.out.println("making db call");
            Optional<User> byId = userRepository.findById(id);
            user = byId.orElse(null);
            if (user != null) {
                myUserCache.put(user.getId(), user);
            }
        }
        return user;
    }

}
