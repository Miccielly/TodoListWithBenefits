package cz.uhk.todolist.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Process extends TimeManager {
    //ÚLOHY
    private List<Task> tasks = new ArrayList<>();   //seznam úloh v procesu
    private float deadlinesum; //suma času úloh

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
        deadlinesum = et;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public float getDeadlinesum() {
        return deadlinesum;
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
        boolean endFound = false;
        int endNodeIndex = -1;
        //najití start nodu
        for (int i = 0; i < tasks.size(); i++)
        {
            if(tasks.get(i).isOnlyNode() && tasks.get(i).getDescription() == "Start Node")
            {
                sorted.add(tasks.get(i));   //přidání start node
                System.out.println(tasks.get(i).getDescription());
            }
            else if ( !endFound && tasks.get(i).isOnlyNode() && tasks.get(i).getDescription() == "End Node") {
                endNodeIndex = i;
                endFound = true;
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

        //Přidání end nodu do sortovaného listu tasků
        if(endNodeIndex >= 0 )
            sorted.add(tasks.get(endNodeIndex));

        tasks = sorted;
    }
    public void calculateCriticalPath()
    {
        //Funguje pouze na seřazeném listu tasků, od start node do end node
        //jak to vypočítat? -> projít tasky a podle návaznosti spočítat cesty k cíli -> cestou zpět naplnit časové rezervy
        //co má být výstup? -> taskům vypočtena hodnota časové rezervy
        sortTasks();
        System.out.println("CPM Calculation");

        //procházení od startu do konce
        for(int i = 0; i < tasks.size(); i++)
        {
            for (int j = 0; j < tasks.size(); j++)
            {
                if((    !(tasks.get(j).isOnlyNode() && tasks.get(i).isOnlyNode())   //start a ending node nesmí být spojený hranou
                        && tasks.get(j).getTaskId() != tasks.get(i).getTaskId()) // task nesmí mít hranu sám na sebe
                        && (tasks.get(j).getPreviousTaskId() == tasks.get(i).getTaskId()
                        || tasks.get(i).getNextTaskId() == tasks.get(j).getTaskId()))
                {
                    //System.out.println(tasks.get(i).getDescription() + " ---> " + tasks.get(j).getDescription());

                    tasks.get(j).setCost( tasks.get(i).getCost()); //přidat následujícímu taksu časovou náročnost předchozí úlohy
                }
            }
        }

        deadlinesum = tasks.get(tasks.size()-1).getCost();
        tasks.get(tasks.size()-1).setCriticalCost(deadlinesum- tasks.get(tasks.size()-1).getDeadline());
        System.out.println(tasks.get(tasks.size()-1).getDescription() + " CriticalCost = " + tasks.get(tasks.size()-1).getCriticalCost());
        //Procházení směrem od end do start
        for(int i = tasks.size()-1; i >= 0; i--)
        {
            System.out.println(i);
            for(int j = tasks.size()-1; j >= 0; j--) {
                //projít listu tásků směrem od cíle a odčítat jednotlivé deadliny od časové náročnosti celého modelu (časová náročnost posledního tasku, respektive endu)
                if ((!(tasks.get(j).isOnlyNode() && tasks.get(i).isOnlyNode())   //start a ending node nesmí být spojený hranou
                        && tasks.get(j).getTaskId() != tasks.get(i).getTaskId()) // task nesmí mít hranu sám na sebe
                        && (tasks.get(j).getNextTaskId() == tasks.get(i).getTaskId()
                        || tasks.get(i).getPreviousTaskId() == tasks.get(j).getTaskId()))
                {
                    System.out.println(tasks.get(i).getCriticalCost() + " - " + tasks.get(j).getDeadline());
                    tasks.get(j).setCriticalCost(tasks.get(i).getCriticalCost()-tasks.get(j).getDeadline());
                    //tasks.get(j).setTimeReserve(tasks.get(j).getCriticalCost() - tasks.get(j).getCost());
                    System.out.println(tasks.get(j).getDescription() + " : " +tasks.get(j).getCriticalCost());

                }
            }
        }
        System.out.println("CPM Calculated");

        //tasky které mají zpáteční cestu od cíle delší než cestu od startu získají hodnotu časové rezervy
    }

    public float getDeadlineSum()
    {
        return deadlinesum;
    }
}
