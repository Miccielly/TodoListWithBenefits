package cz.uhk.todolist.model;

import java.util.ArrayList;
import java.util.List;

public class Process extends TimeManager {
    //ÚLOHY
    private List<Task> tasks = new ArrayList<>();   //seznam úloh v procesu
    private float deadLineSum; //suma času úloh

    //CONSTRUCTORY
    public Process(String description, float estimatedTime) {
        this.description = description;
        this.deadline = estimatedTime;
    }

    //METODY
    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void computeEstimatedTimeSum()
    {
        float et = 0;
        for(int i = 0; i < tasks.size()-1; i++)
        {
            if(tasks.get(i).getDeadline() < tasks.get(i).getDeadline())
                et += tasks.get(i).getDeadline();
            else
                et += tasks.get(i).getCurrentTime();
        }
        deadLineSum = et;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public float getDeadLineSum() {
        return deadLineSum;
    }

    public float getTimeDifference()
    {
        updateCurrentTime();
        return (getCurrentTime()- getDeadline());
    }
}
