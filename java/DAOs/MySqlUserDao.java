package week3.java.DAOs;

/**
 * OOP Feb 2024
 * <p>
 * Data Access Object (DAO) for User table with MySQL-specific code
 * This 'concrete' class implements the 'UserDaoInterface'.
 * <p>
 * The DAO will contain the SQL query code to interact with the database,
 * so, the code here is specific to a MySql database.
 * No SQL queries will be used in the Business logic layer of code, thus, it
 * will be independent of the database specifics. Changes to code related to
 * the database are all contained withing the DAO code base.
 * <p>
 * <p>
 * The Business Logic layer is only permitted to access the database by calling
 * methods provided in the Data Access Layer - i.e. by calling the DAO methods.
 * In this way, the Business Logic layer is seperated from the database specific code
 * in the DAO layer.
 */

import week3.java.DTOs.Task;
import week3.java.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlUserDao extends MySqlDao implements UserDaoInterface {

    @Override
    public List<Task> getTasks() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Task> taskList = new ArrayList<>();

        try {
            connection = this.getConnection();
//
            String query = "SELECT * FROM tasks";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("USER_ID");
                String username = resultSet.getString("USERNAME");
                String password = resultSet.getString("PASSWORD");
                String lastname = resultSet.getString("LAST_NAME");
                String firstname = resultSet.getString("FIRST_NAME");
                Task u = new Task(userId, firstname, lastname, username, password);
                usersList.add(u);
            }
        } catch (SQLException e) {
            throw new DaoException("findAllUseresultSet() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllUsers() " + e.getMessage());
            }
        }
        return taskList;
    }

}

