package driver;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CrudRepository storage = new CrudRepository();
        storage.createConnection();

        // AllByAge
        List<User> userOptionalThree = storage.findAllByAge(19);
        for (User user : userOptionalThree) {
            System.out.println(user);
        }
        System.out.println();
    }
}


