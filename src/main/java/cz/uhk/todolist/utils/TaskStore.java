package cz.uhk.todolist.utils;

import cz.uhk.todolist.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskStore {
    private List<Task> tasks = new ArrayList<>();

    //METODY
    public List<Task> getTasks()
    {
        return tasks;
    }

    public TaskStore(List<Task> projects)
    {
        this.tasks = projects;
    }}
