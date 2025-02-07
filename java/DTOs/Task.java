package week3.java.DTOs;


import java.sql.Date;

/**                                                     OOP Feb 2022
 *  Data Transfer Object (DTO)
 *
 * This POJO (Plain Old Java Object) is called the Data Transfer Object (DTO).
 * (or, alternatively, the Model Object or the Value Object).
 * It is used to transfer data between the DAO and the Business Objects.
 * Here, it represents a row of data from the User database table.
 * The DAO creates and populates a User object (DTO) with data retrieved from
 * the resultSet and passes the User object to the Business Layer.
 *
 * Collections of DTOs( e.g. ArrayList<User> ) may also be passed
 * between the Data Access Layer (DAOs) and the Business Layer objects.
 */

public class Task
{
    private int taskID;
    private String title;
    private String tag;
    private Date dueDate;
    private boolean done;

    public Task(int taskID, String title, String tag, Date dueDate, boolean done)
    {
        this.taskID = taskID;
        this.title = title;
        this.tag = tag;
        this.dueDate = dueDate;
        this.done = done;
    }

    public Task(String firstName, String title, String tag, Date dueDate, boolean done)
    {
        this.taskID = 0;
        this.title = title;
        this.tag = tag;
        this.dueDate = dueDate;
        this.done = done;
    }

    public Task()
    {
    }

    public int getTaskID()
    {
        return taskID;
    }

    public void setTaskID(int taskID)
    {
        this.taskID = taskID;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTag()
    {
        return tag;
    }

    public void setTag(String tag)
    {
        this.tag = tag;
    }

    public Date getDueDate()
    {
        return dueDate;
    }

    public void setDueDate(Date dueDate)
    {
        this.dueDate = dueDate;
    }

    public boolean getDone()
    {
        return done;
    }

    public void setDone(boolean done)
    {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskID=" + taskID +
                ", title='" + title + '\'' +
                ", tag='" + tag + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", done=" + done +
                '}';
    }
}
