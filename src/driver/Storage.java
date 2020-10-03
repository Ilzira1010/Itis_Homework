package driver;

import java.util.List;
import java.util.Optional;


public interface Storage<T> {
    List<T> findAll();
    List<T> findAllByAge(Integer age);
}

