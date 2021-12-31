package cz.uhk.todolist.services;

import cz.uhk.todolist.model.Process;
import cz.uhk.todolist.model.Project;
import cz.uhk.todolist.model.Task;

import java.util.ArrayList;
import java.util.List;

public class ProjectService {
    private List<Project> projects = new ArrayList<>();

    public List<Project> getProjects()
    {
        return projects;
    }

    public ProjectService()
    {
        Project project = new Project("Projekt01", 400);

        Process process = new Process("Proces01", 10);
        process.addTask(new Task("Spát", 360, -1,0, 10));

        process.addTask(new Task("Koš", 5, 11,4, 9999));
        process.addTask(new Task(true));    //starting node

        process.addTask(new Task("Vstát", 20, 10,-1, 4));
        process.addTask(new Task("Záchod", 15, 4,69, 11));

        process.addTask(new Task("Instagram", 35, 69,-1, 4));
        process.addTask(new Task(false));   //ending node

        //2 - >
        process.calculateCriticalPath();
        project.addProcess(process);

        projects.add(project);
    }



}
