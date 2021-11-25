package cz.uhk.todolist.model;

import java.util.ArrayList;
import java.util.List;

public class Process extends TimeManager {


    //ÚLOHY
    private List<Task> tasks = new ArrayList<>();   //seznam úloh v procesu



    public Process(String description, float estimatedTime)
    {
        this.description = description;
        this.estimatedDeadline = estimatedTime;
    }

    //Manipulace s tasky
    public void addTask(Task task)
    {
        tasks.add(task);
    }
    public List<Task> getTasks()
    {
        return tasks;
    }
}
