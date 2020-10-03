package driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrudRepository implements Storage {

    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "ilzira1010";
    private static Connection connection;

    public void createConnection() {

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now" + "\n");
        } else System.out.println("Failed to make connection to database");
    }

    @Override
    public List<User> findAllByAge(Integer age) {
        List<User> resultAge = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSetAge = statement.executeQuery("select * from driver where age=" + age.toString());
            while (resultSetAge.next()) {
                resultAge.add(new User(
                        resultSetAge.getLong("id"),
                        resultSetAge.getShort("age"),
                        resultSetAge.getString("first_name"),
                        resultSetAge.getString("last_name")
                ));
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return resultAge;
    }

    @Override
    public List<User> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from driver");
            List<User> result = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getShort("age"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"));
                result.add(user);
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}