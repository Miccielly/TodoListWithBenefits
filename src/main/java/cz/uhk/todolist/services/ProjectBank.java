package cz.uhk.todolist.services;

import cz.uhk.todolist.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class ProjectBank {
    private List<Project> projects = new ArrayList<>();

    //METODY
    public List<Project> getProjects()
    {
        return projects;
    }
}
