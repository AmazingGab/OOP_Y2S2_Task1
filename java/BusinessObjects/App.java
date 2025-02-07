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
import week3.java.DTOs.Task;
import week3.java.Exceptions.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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

                    IUserDao.completeTask(id);
                }
                else if (input == 4) {
                    List<Task> tasks = IUserDao.getTasks();
                    display(tasks);
                }
                else if (input == 5) {
                    System.out.println("Please enter a tag:");
                    tag = keyboard.nextLine();

                    List<Task> tasks = IUserDao.getTasksByTag(tag);
                    display(tasks);
                }
                else {
                    List<Task> tasks = IUserDao.getCompletedTasks();
                    display(tasks);
                }


                System.out.println("Pick one of the options below:");
                System.out.println("1 to add new task, 2 to delete task, 3 to complete a task 4 to list all tasks, 5 list all particular tag tasks, 6 list all completed tasks and 7 to exit");
                input = keyboard.nextInt();
            }
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
    }

    public static void display(List<Task> tasks) {
        System.out.printf("\n%2s | %-20s | %-12s | %-10s | %s\n", "No", "Title", "Tag", "Due Date", "Done");
        System.out.println("-------------------------------------------------------------");
        for (Task t : tasks) {
            System.out.printf("%2d | %-20s | [%-10s] | %s | %b\n", t.getTaskID(), t.getTitle(), t.getTag(), t.getDueDate(), t.getDone());
        }
        System.out.println();
    }
}

