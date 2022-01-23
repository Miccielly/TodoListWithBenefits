package cz.uhk.todolist.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Projects")
public class Project extends WorkUnit {

    @Id
    private String id;

    //private List<String> processesIds = new ArrayList<>();
    @Transient
    private List<Process> processes = new ArrayList<>();   //seznam procesů v projektu
    private float elapsedTime;  //uplynulý čas od založení procesu (zastaví se po dokončení celého procesu)
    private String startDate = "";

    //KONSTRUKTORY
    public Project(String name, List<Process> processes, float deadline) {
        this.description = name;
        this.processes = processes;
        this.deadline = deadline;
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

    public float getNonAlocatedTime() {
        float nat = deadline;   //unalocated time

        if (processes.size() > 0) {
            for (int i = 0; i < processes.size(); i++) {
                if (processes.get(i).getDeadline() > processes.get(i).getDeadlineSum())
                    nat -= processes.get(i).getDeadline();
                else {
                    //System.out.println("deadlineSumVětší < deadline = " + processes.get(i).getDescription());
                    nat -= processes.get(i).getDeadlineSum();
                }
            }
            //System.out.println("Project: " + description + " nat: " + nat);
            return nat;
        }
        return nat;
    }

    public void calculateElapsedTime() {
        float et = 0;
        if (processes.size() > 0) {
            for (int i = 0; i < processes.size(); i++) {
                et += processes.get(i).getElapsedTime();
            }
            elapsedTime = et;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(float elapsedTime) {
        this.elapsedTime = elapsedTime;
    }


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
