package cz.uhk.todolist.model;

import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public class Project extends TimeManager {

    @Id
    private String id;

    private List<Process> processes = new ArrayList<>();   //seznam procesů v projektu
    private float deadLineSum; //suma času úloh

    //KONSTRUKTORY
    public Project(String name, List<Process> processes, float estimatedTime)
    {
        this.description = name;
        this.processes = processes;
        this.deadline = estimatedTime;

        startDate = getCurrentDate();
    }

    public Project(String name, float estimatedTime)
    {
        this.description = name;
        this.deadline = estimatedTime;

        startDate = getCurrentDate();

    }

    public Project(String name)
    {
        this.description = name;
    }

    public Project()
    {


    }
    //METODY

    public void computeEstimatedTimeSum()
    {
        float et = 0;
        for(int i = 0; i < processes.size()-1; i++)
        {
            processes.get(i).computeEstimatedTimeSum();
            if(processes.get(i).getDeadline() < processes.get(i).getDeadline())
                et += processes.get(i).getDeadline();
            else
                et += processes.get(i).getCurrentTime();
        }
        deadLineSum = et;
    }

    //GETTERY SETTERY

    public void addProcess(Process process)
    {
        processes.add(process);
    }

    public List<Process> getProcesses()
    {
        return processes;
    }

    public String getName() {
        return description;
    }

    public void setName(String name) {
        this.description = name;
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
