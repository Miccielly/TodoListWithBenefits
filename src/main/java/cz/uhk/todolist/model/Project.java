package cz.uhk.todolist.model;

import cz.uhk.todolist.model.Process;
import cz.uhk.todolist.model.TimeManager;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Projects")
public class Project extends TimeManager {

    @Id
    private String id;

    //private List<String> processesIds = new ArrayList<>();
    @Transient
    private List<Process> processes = new ArrayList<>();   //seznam procesů v projektu
    private float deadLineSum = 0; //suma času úloh


    //KONSTRUKTORY
    public Project(String name, List<Process> processes, float deadline) {
        this.description = name;
        this.processes = processes;
        this.deadline = deadline;

        //startDate = getCurrentDate();
    }

    public Project(String name, float deadline) {
        this.description = name;
        this.deadline = deadline;
    }

    public Project(String name) {
        this.description = name;
    }

    public Project() {


    }
    //METODY

    public void computeEstimatedTimeSum() {
        float et = 0;
        for (int i = 0; i < processes.size() - 1; i++) {
            processes.get(i).computeEstimatedTimeSum();
            if (processes.get(i).getDeadline() < processes.get(i).getDeadline())
                et += processes.get(i).getDeadline();
            else
                et += processes.get(i).getCurrentTime();
        }
        deadLineSum = et;
    }

    //GETTERY SETTERY

    public void addProcess(Process process) {
        processes.add(process);
    }

    public void setProcesses(List<Process> processes) {
        this.processes = processes;
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public String getName() {
        return description;
    }

    public void setName(String name) {
        this.description = name;
    }

    public float getTimeDifference() {
        updateCurrentTime();
        return (getCurrentTime() - getDeadline());
    }

    public float getNonAlocatedTime() {
        float nat = deadline;   //unalocated time

        if (processes.size() > 0) {
            for (int i = 0; i < processes.size(); i++) {
                if (processes.get(i).getDeadline() > processes.get(i).getDeadlineSum())
                    nat -= processes.get(i).getDeadline();
                else {
                    System.out.println("deadlineSumVětší < deadline = " + processes.get(i).getDescription());
                    nat -= processes.get(i).getDeadlineSum();
                }
            }
            System.out.println("Project: " + description + " nat: " + nat);
            return nat;
        }
        return nat;
    }

    public String getId() {
        return id;
    }

}
