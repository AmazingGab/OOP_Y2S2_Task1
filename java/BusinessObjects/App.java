package week3.java.BusinessObjects;

/** OOP Jan 2025
 * This App demonstrates the use of a Data Access Layer
 * to separate Business logic from Database specific logic.
 * It uses:
 * Data Access Objects (DAOs) to implement the logic required to access a database.
 * Data Transfer Objects (DTOs), to transfer data between layers, and a
 * DAO Interface to define a 'contract' between Business Objects and DAOs.
 *
 * "Use a Data Access Object (DAO) to abstract and encapsulate all
 * access to the data source. The DAO manages the connection with
 * the data source to obtain and store data" Ref: oracle.com
 *
 * Here, we use one DAO per database table.
 *
 * Use the SQL script "CreateUsers.sql" included with this project
 * to create the required MySQL user_database and User table.
 */

import week3.java.DAOs.MySqlUserDao;
import week3.java.DAOs.UserDaoInterface;
import week3.java.Exceptions.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        UserDaoInterface IUserDao = new MySqlUserDao();
        try
        {
            System.out.println("Pick one of the options below:");
            System.out.println("1 to add new task, 2 to delete task, 3 to complete a task 4 to list all tasks, 5 list all particular tag tasks, 6 list all completed tasks and 7 to exit");
            int input = keyboard.nextInt();
            while (input >= 1 && input <= 6) {
                keyboard.nextLine();
                String title, tag, dueDate;
                if (input == 1) {
                    System.out.println("Please enter a title:");
                    title = keyboard.nextLine();
                    System.out.println("Please enter a tag:");
                    tag = keyboard.nextLine();
                    System.out.println("Please enter a due date: YYYY-MM-DD");
                    dueDate = keyboard.nextLine();

                }
                else if (input == 2) {
                    System.out.println("Please enter a task ID:");
                    int id = keyboard.nextInt();

                }
                else if (input == 3) {
                    System.out.println("Please enter a task ID:");
                    int id = keyboard.nextInt();

                }
                else if (input == 4) {
                    ResultSet resultSet = statement.executeQuery("select * from tasks WHERE done = false");
                    display(resultSet);
                }
                else if (input == 5) {
                    System.out.println("Please enter a tag:");
                    tag = keyboard.nextLine();

                    ResultSet resultSet = statement.executeQuery("select * from tasks where tag LIKE '" + tag + "' and done = false");
                    display(resultSet);
                }
                else {
                    ResultSet resultSet = statement.executeQuery("select * from tasks where done = true");
                    display(resultSet);
                }


                System.out.println("Pick one of the options below:");
                System.out.println("1 to add new task, 2 to delete task, 3 to complete a task 4 to list all tasks, 5 list all particular tag tasks, 6 list all completed tasks and 7 to exit");
                input = keyboard.nextInt();
            }
//            System.out.println("\nCall findAllUsers()");
//            List<User> users = IUserDao.findAllUsers();     // call a method in the DAO
//
//            if( users.isEmpty() )
//                System.out.println("There are no Users");
//            else {
//                for (User user : users)
//                    System.out.println("User: " + user.toString());
//            }
//
//            // test dao with a username and password that we know are present in the database
//            // (Use phpMyAdmin to check that the database has a row with this data)
//            System.out.println("\nCall: findUserByUsernamePassword()");
//            String username = "smithj";
//            String password = "password";
//
//            User user = IUserDao.findUserByUsernamePassword(username, password);
//
//            if( user != null ) // null returned if userid and password not valid
//                System.out.println("User found: " + user);
//            else
//                System.out.println("Username with that password not found");
//
//            // test dao - with an invalid username (i.e. row not in database)
//            username = "madmax";
//            password = "thunderdome";
//
//            user = IUserDao.findUserByUsernamePassword(username, password);
//
//            if(user != null)
//                System.out.println("Username: " + username + " was found: " + user);
//            else
//                System.out.println("Username: " + username + ", password: " + password +" : NO match found");
//
//            System.out.println("\nCall findAllUsersLastNameContains()");
//            System.out.println("LASTNAME?");
//            String lastName = keyboard.nextLine();
//            users = IUserDao.findAllUsersLastNameContains(lastName);     // call a method in the DAO
//
//            if( users.isEmpty() )
//                System.out.println("There are no Users");
//            else {
//                for (User userLn : users)
//                    System.out.println("User: " + userLn.toString());
//            }
//
//            System.out.println("\nCall deleteUserById()");
//            System.out.println("ID?");
//            int id = keyboard.nextInt();
//            int result = IUserDao.deleteUserById(id);
//
//            if (result == 1) {
//                System.out.println("SUCCESS");
//            }
//            else {
//                System.out.println("FAILED");
//            }
//
//
//            System.out.println("\nCall updateUserPassword()");
//            System.out.println("ID?");
//            id = keyboard.nextInt();
//            keyboard.nextLine();
//            System.out.println("PASSWORD?");
//            String newPassword = keyboard.nextLine();
//            result = IUserDao.updateUserPassword(id, newPassword);
//
//            if (result == 1) {
//                System.out.println("SUCCESS");
//            }
//            else {
//                System.out.println("FAILED");
//            }
//
//            System.out.println("\nCall: registerUser()");
//            System.out.println("USERNAME?");
//            username = keyboard.nextLine();
//            System.out.println("FIRSTNAME?");
//            String firstName = keyboard.nextLine();
//            System.out.println("LASTNAME?");
//            lastName = keyboard.nextLine();
//            System.out.println("PASSWORD?");
//            password = keyboard.nextLine();
//
//            user = IUserDao.registerUser(username,lastName, firstName, password);
//
//            if( user != null ) // null returned if userid and password not valid
//                System.out.println("User Added: " + user);
//            else
//                System.out.println("FAILED");
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    public void display(ResultSet resultSet) {
        try {
            System.out.printf("\n%2s | %-20s | %-12s | %-10s | %s\n", "No", "Title", "Tag", "Due Date", "Done");
            System.out.println("-------------------------------------------------------------");
            while (resultSet.next()) {
                int taskId = resultSet.getInt("taskid");
                String title = resultSet.getString("title");
                String tag = resultSet.getString("tag");
                String duedate = resultSet.getString("duedate");
                boolean done = resultSet.getBoolean("done");

                System.out.printf("%2d | %-20s | [%-10s] | %s | %b\n", taskId, title, tag, duedate, done);
            }
            System.out.println();
        } catch (SQLException ex) {
            System.out.println("Failed to connect to database - check MySQL is running and that you are using the correct database details");
            ex.printStackTrace();
        }
    }
}

