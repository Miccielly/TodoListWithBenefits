package cz.uhk.todolist.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Process extends TimeManager {
    //ÚLOHY
    private List<Task> tasks = new ArrayList<>();   //seznam úloh v procesu
    private float deadLineSum; //suma času úloh

    //CONSTRUCTORY
    public Process(String description, float estimatedTime) {
        this.description = description;
        this.deadline = estimatedTime;
    }

    //METODY
    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void computeEstimatedTimeSum()
    {
        float et = 0;
        for(int i = 0; i < tasks.size()-1; i++)
        {
            //je překročen deadline?
            if(tasks.get(i).getDeadline() > tasks.get(i).getCurrentTime())
                et += tasks.get(i).getDeadline();
            else
                et += tasks.get(i).getCurrentTime();
        }
        deadLineSum = et;
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
    public void sortTasksOld()
    {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                if(task1.getPreviousTaskId() > task2.getPreviousTaskId())
                    return 1;
                if(task1.getPreviousTaskId() < task2.getPreviousTaskId())
                    return -1;
                return 0;
            }
        });
    }

    public void sortTasks()
    {
        List<Task> sorted = new ArrayList<>();

        //najití start nodu
        for (int i = 0; i < tasks.size(); i++)
        {
            if(tasks.get(i).isOnlyNode() && tasks.get(i).getDescription() == "Start Node")
            {
                sorted.add(tasks.get(i));   //přidání start node
                System.out.println(tasks.get(i).getDescription());
            }
        }
        //projití tasků napojených na start a pak další přidané tasky
        for (int i = 0; i < sorted.size(); i++)
        {
            for (int j = 0; j < tasks.size(); j++)
            {
                if(!tasks.get(j).isOnlyNode() && sorted.get(i).getTaskId() == tasks.get(j).getPreviousTaskId())
                {
                    sorted.add(tasks.get(j));   //přidání tasků, které navazují na již seřazené tasky
                }
            }
        }

        tasks = sorted;
    }
    public void calculateCriticalPath()
    {
        //definovat start nebo od konce najít cestu do startu
        //https://stackoverflow.com/questions/2985317/critical-path-method-algorithm

        //co má být vstup? -> list tasků v procesu
        //jak to vypočítat? -> projít tasky a podle návaznosti spočítat cesty k cíli -> cestou zpět naplnit časové rezervy
        //co má být výstup? -> taskům vypočtena hodnota časové rezervy
        sortTasks();
        System.out.println("Critical Path Calculation");
        for(int i = 0; i < tasks.size(); i++)
        {
            for (int j = 0; j < tasks.size(); j++)
            {
                if((    !(tasks.get(j).isOnlyNode() && tasks.get(i).isOnlyNode())   //start a ending node nesmí být spojený hranou
                        && tasks.get(j).getTaskId() != tasks.get(i).getTaskId()) // task nesmí mít hranu sám na sebe
                        && (tasks.get(j).getPreviousTaskId() == tasks.get(i).getTaskId()
                        || tasks.get(i).getNextTaskId() == tasks.get(j).getTaskId()))
                {
                    System.out.println(tasks.get(i).getDescription() + " ---> " + tasks.get(j).getDescription());

                    tasks.get(j).setCost( tasks.get(i).getCost()); //přidat následujícímu taksu časovou náročnost předchozí úlohy
                    //přidat sčítání předchozích costů
                    //přidat porovnání pro přidání největšího čísla pokud je víc k dispozici
                }
            }

            //projít tasky směrem od začátku k cíli a přičítat čas cesty
        }

        for(int i = tasks.size()-1; i >= 0; i--)
        {
            //projít tasky směrem od začátku k cíli a přičítat čas cesty
            //projít tasky směrem od cíle a z připojených cest přidávat taskům zpáteční nejdelší cestu
        }
        //tasky které mají zpáteční cestu od cíle delší než cestu od startu získají hodnotu časové rezervy
    }
}
