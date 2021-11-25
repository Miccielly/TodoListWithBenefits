package cz.uhk.todolist.services;

import cz.uhk.todolist.model.Process;
import cz.uhk.todolist.model.Project;
import cz.uhk.todolist.model.Task;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class ProjectService {
    private List<Project> projects = new ArrayList<>();   //seznam úloh v procesu

    public List<Project> getProjects()
    {
        return projects;
    }

    public ProjectService()
    {
        Project project = new Project("Projekt01", 200);

        Process process = new Process("Proces01", 10);

        process.addTask(new Task("Vynést koš", 5, -1, 1));
        process.addTask(new Task("Spát", 360, 0, 1));

        project.addProcess(process);

        projects.add(project);
    }

}
