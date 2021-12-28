package karn.demo.ehcachedemo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class Controller {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUser() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }
    @PostMapping("/save")
    public ResponseEntity save(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok("Saved");
    }
}
