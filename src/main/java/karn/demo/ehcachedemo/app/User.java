package karn.demo.ehcachedemo.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class User implements Serializable {

    @Id
    private Long id;
    private String name;
    private int number;
}
