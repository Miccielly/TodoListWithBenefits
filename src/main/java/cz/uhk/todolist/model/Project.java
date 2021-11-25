package cz.uhk.todolist.model;

import java.util.ArrayList;
import java.util.List;

public class Project extends TimeManager {

    private List<Process> processes = new ArrayList<>();   //seznam procesů v projektu

    public Project(String name, List<Process> processes, float estimatedTime)
    {
        this.description = name;
        this.processes = processes;
        this.estimatedDeadline = estimatedTime;
    }

    public Project(String name, float estimatedTime)
    {
        this.description = name;
        this.estimatedDeadline = estimatedTime;
    }

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
}
