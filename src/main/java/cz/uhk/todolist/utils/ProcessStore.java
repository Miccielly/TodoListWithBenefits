package cz.uhk.todolist.utils;

import cz.uhk.todolist.model.Process;

import java.util.ArrayList;
import java.util.List;

public class ProcessStore {
    private List<Process> processes = new ArrayList<>();

    //METODY
    public List<Process> getProcesses()
    {
        return processes;
    }

    public ProcessStore(List<Process> processes)
    {
        this.processes = processes;
    }
}
