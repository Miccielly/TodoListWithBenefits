package cz.uhk.todolist.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.*;

@Document(collection = "Processes")
public class Process extends WorkUnit {

    @Id
    private String id;

    private String parentId;
    //ÚLOHY

    // private List<String> tasksId = new ArrayList<>();
    @Transient
    private List<Task> tasks = new ArrayList<>();   //seznam úloh v procesu
    private float deadlinesum = 0; //suma času úloh
    private float elapsedTime;  //uplynulý čas od založení procesu (zastaví se po dokončení celého procesu)
    private String startDate = "";


    //CONSTRUCTORY
    public Process(String description, float deadline, String parentId) {
        this.description = description;
        this.deadline = deadline;
        this.parentId = parentId;

        //tasks.add(new Task(true));
        //tasks.add(new Task(false));
    }

    public Process() {
    }

    ; //"Defaultní" konstruktor

    //METODY
    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }


    public void sortTasks() {
        List<Task> sorted = new ArrayList<>();
        boolean endFound = false;
        int endNodeIndex = -1;
        //najití start nodu
        for (int i = 0; i < tasks.size(); i++) {
//            System.out.println(i + " description: " + tasks.get(i).getDescription());
            if (tasks.get(i).isOnlyNode() && tasks.get(i).getDescription().equals("Start Node")) {
                sorted.add(tasks.get(i));   //přidání start node
//                System.out.println(tasks.get(i).getDescription());
            } else if (!endFound && tasks.get(i).isOnlyNode() && tasks.get(i).getDescription().equals("End Node")) {
                endNodeIndex = i;
                endFound = true;
            }
        }
        //projití tasků napojených na start a pak další přidané tasky
        for (int i = 0; i < sorted.size(); i++) {
            for (int j = 0; j < tasks.size(); j++) {
                if (!tasks.get(j).isOnlyNode() && sorted.get(i).getId().equals(tasks.get(j).getPreviousTaskId())) {
                    sorted.add(tasks.get(j));   //přidání tasků, které navazují na již seřazené tasky
                }
            }
        }

        //Přidání end nodu do sortovaného listu tasků
        if (endNodeIndex >= 0)
            sorted.add(tasks.get(endNodeIndex));

        tasks = sorted;
    }

    public void calculateCriticalPath() {
        //TODO vyřešit problém kdy nejsou tasky napojeny jak mají, tak aby bylo vypsáno že chybí napojení v UI
        //Funguje pouze na seřazeném listu tasků, od start node do end node
        //jak to vypočítat? -> projít tasky a podle návaznosti spočítat cesty k cíli -> cestou zpět naplnit časové rezervy
        //co má být výstup? -> taskům vypočtena hodnota časové rezervy
        sortTasks();

        if (!(tasks.size() > 0)) {
            return;
        }
        //procházení od startu do konce
        for (int i = 0; i < tasks.size(); i++) {
            for (int j = 0; j < tasks.size(); j++) {
                if (!(tasks.get(j).isOnlyNode() && tasks.get(i).isOnlyNode())   //start a ending node nesmí být spojený hranou
                        && !tasks.get(j).getId().equals(tasks.get(i).getId()) // task nesmí mít hranu sám na sebe
                        && (tasks.get(j).getPreviousTaskId().equals(tasks.get(i).getId())
                        || tasks.get(i).getNextTaskId().equals(tasks.get(j).getId()))
                ) {
                    //TODO Přidat táskům boolean navštívenost a nepovolit jít do dalšího nenavštíveného tasku pokud existuje přechozí nenavštívený (přechozí lze určit podle indexu)!

                    //System.out.println(tasks.get(i).getDescription() + " ---> " + tasks.get(j).getDescription());

                    float newCost = tasks.get(i).getDeadline() + tasks.get(i).getCost();
                    if (newCost > tasks.get(j).getCost())
                        tasks.get(j).setCost(tasks.get(i).getDeadline() + tasks.get(i).getCost()); //přidat následujícímu tasku časovou náročnost předchozí úlohy
                }
            }
        }

        deadlinesum = tasks.get(tasks.size() - 1).getCost();
        tasks.get(tasks.size() - 1).setCriticalCost(deadlinesum - tasks.get(tasks.size() - 1).getDeadline());
        //System.out.println(tasks.get(tasks.size()-1).getDescription() + " CriticalCost = " + tasks.get(tasks.size()-1).getCriticalCost());

        //Procházení směrem od end do start
        for (int i = tasks.size() - 1; i >= 0; i--) {
            //System.out.println(i);
            for (int j = tasks.size() - 1; j >= 0; j--) {
                //projít listu tásků směrem od cíle a odčítat jednotlivé deadliny od časové náročnosti celého modelu (časová náročnost posledního tasku, respektive endu)
                if (!(tasks.get(j).isOnlyNode() && tasks.get(i).isOnlyNode())   //start a ending node nesmí být spojený hranou
                        && !tasks.get(j).getId().equals(tasks.get(i).getId()) // task nesmí mít hranu sám na sebe
                        && (tasks.get(j).getNextTaskId().equals(tasks.get(i).getId())
                        || tasks.get(i).getPreviousTaskId().equals(tasks.get(j).getId()))
                ) {
                    //System.out.println(tasks.get(i).getCriticalCost() + " - " + tasks.get(j).getDeadline());

                    float newCriticalCost = tasks.get(i).getCriticalCost() - tasks.get(j).getDeadline();
                    if (newCriticalCost < tasks.get(j).getCriticalCost())
                        tasks.get(j).setCriticalCost(newCriticalCost);

                    tasks.get(j).setTimeReserve(tasks.get(j).getCriticalCost() - tasks.get(j).getCost());
                }
            }
        }
        System.out.println("CPM Calculated");

        //tasky které mají zpáteční cestu od cíle delší než cestu od startu získají hodnotu časové rezervy
    }
/*
    public void startTimer()
    {
        startDate = LocalDateTime.now().toString();
        System.out.println("StartTime: " + startDate);
    }

    public void endTimer()
    {
        System.out.println("StartTime: " + LocalDateTime.now().toString());
    }
*/
    public LocalDateTime getCurrentDate() { return LocalDateTime.now(); }

    public void getDateDifference() {
        //Duration duration = Duration.between(startDate, getCurrentDate());

        //System.out.println("timeElapsed: " + duration);
    }

    public float getDeadlineSum()
    {
        return deadlinesum;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(float elapsedTime) { this.elapsedTime = elapsedTime; }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getId()
    {
        return id;
    }

    public String getParentId() { return parentId; }

    public void setParentId(String parentId) { this.parentId = parentId; }

    public void addTasks (List<Task> tasks) { this.tasks = tasks; }

    public void addTasks (Task[] tasks) { this.tasks = Arrays.asList(tasks); }



}
