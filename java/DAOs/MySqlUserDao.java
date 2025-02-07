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
import java.util.Date;
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
            String query = "SELECT * FROM tasks WHERE done = false";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("taskid");
                String title = resultSet.getString("title");
                String tag = resultSet.getString("tag");
                Date duedate = resultSet.getDate("duedate");
                boolean done = resultSet.getBoolean("done");
                Task task = new Task(id, title, tag, duedate, done);
                taskList.add(task);
            }
        } catch (SQLException e) {
            throw new DaoException("getTasks() " + e.getMessage());
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
                throw new DaoException("getTasks() " + e.getMessage());
            }
        }
        return taskList;
    }

    public List<Task> getTasksByTag(String tags) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Task> taskList = new ArrayList<>();

        try {
            connection = this.getConnection();
            String query = "SELECT * FROM tasks WHERE tag LIKE ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tags);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("taskid");
                String title = resultSet.getString("title");
                String tag = resultSet.getString("tag");
                Date duedate = resultSet.getDate("duedate");
                boolean done = resultSet.getBoolean("done");
                Task task = new Task(id, title, tag, duedate, done);
                taskList.add(task);
            }
        } catch (SQLException e) {
            throw new DaoException("getTasksByTag() " + e.getMessage());
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
                throw new DaoException("getTasksByTag() " + e.getMessage());
            }
        }
        return taskList;
    }

    public List<Task> getCompletedTasks() throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Task> taskList = new ArrayList<>();

        try {
            connection = this.getConnection();
            String query = "SELECT * FROM tasks WHERE done = true";
            preparedStatement = connection.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("taskid");
                String title = resultSet.getString("title");
                String tag = resultSet.getString("tag");
                Date duedate = resultSet.getDate("duedate");
                boolean done = resultSet.getBoolean("done");
                Task task = new Task(id, title, tag, duedate, done);
                taskList.add(task);
            }
        } catch (SQLException e) {
            throw new DaoException("getCompletedTasks() " + e.getMessage());
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
                throw new DaoException("getCompletedTasks() " + e.getMessage());
            }
        }
        return taskList;
    }
}

