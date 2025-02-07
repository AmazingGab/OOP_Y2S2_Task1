package week3.java.DAOs;

/** OOP Feb 2022
 * UserDaoInterface
 *
 * Declares the methods that all UserDAO types must implement,
 * be they MySql User DAOs or Oracle User DAOs etc...
 *
 * Classes from the Business Layer (users of this DAO interface)
 * should use reference variables of this interface type to avoid
 * dependencies on the underlying concrete classes (e.g. MySqlUserDao).
 *
 * More sophisticated implementations will use a factory
 * method to instantiate the appropriate DAO concrete classes
 * by reading database configuration information from a
 * configuration file (that can be changed without altering source code)
 *
 * Interfaces are also useful when testing, as concrete classes
 * can be replaced by mock DAO objects.
 */

import week3.java.DTOs.Task;
import week3.java.Exceptions.DaoException;
import java.util.List;

public interface UserDaoInterface
{
    public void addTask(Task task) throws DaoException;

    public void deleteTask(Task task) throws DaoException;

    public void completeTask(int taskID) throws DaoException;

    public List<Task> getTasks() throws DaoException;

    public List<Task> getTasksByTag(String tag) throws DaoException;

    public List<Task> getCompletedTasks() throws DaoException;
}

