package cz.uhk.todolist.utils;

import cz.uhk.todolist.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectStore {
    private List<Project> projects = new ArrayList<>();

    //METODY
    public List<Project> getProjects()
    {
        return projects;
    }

    public ProjectStore(List<Project> projects)
    {
        this.projects = projects;
    }
}
