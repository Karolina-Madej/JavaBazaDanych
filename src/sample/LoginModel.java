package sample;

import java.sql.Connection;
import java.sql.*;

public class LoginModel {

    Connection connection;


    public LoginModel() {

        connection = SqlConnector.Connector();
        if (connection == null) {
            System.out.println("blad");
            System.exit(1);

        }
    }

    public boolean isDBConneted() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    boolean isLogin(String username, String password) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM employee WHERE username = ? and password = ?";

        preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return true;
        } else
            return false;

    }

}
