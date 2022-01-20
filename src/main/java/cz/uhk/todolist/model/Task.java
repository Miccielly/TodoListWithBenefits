package cz.uhk.todolist.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Tasks")
public class Task extends TimeManager {

    private float timeReserve = 0;

    //PRO ZAŘAZENÍ DO POSLOUPNOSTI TASKŮ
    @Id
    private String id;

    private String parentId;

    protected String previousTaskId = "";
    protected String nextTaskId = "";
    protected boolean onlyNode = false;

    //VÝPOČET KRITICKÉ CESTY
    private float cost = 0;
    private float criticalCost = 9999999;

    //CONSTRUCTOR

    public Task(String description, float deadline, String parentId) {
        this.description = description;
        this.deadline = deadline;
        this.parentId = parentId;
    }

    public Task()
    {


    }
    public Task(boolean startNode, String parentId)
    {
        this.parentId = parentId;
        if(startNode)
        {
            this.description = "Start Node";
        }
        else
        {
            this.description = "End Node";
        }
        onlyNode = true;
    }

    //METODY

    public String getPreviousTaskId() {
        return previousTaskId;
    }

    public void setPreviousTaskId(String previousTaskId) {
        this.previousTaskId = previousTaskId;
    }

    public String getNextTaskId() {
        return nextTaskId;
    }

    public void setNextTaskId(String nextTaskId) {
        this.nextTaskId = nextTaskId;
    }


    public String getTaskId() { return id; }

    public boolean isOnlyNode() { return onlyNode; }

    //CPM
    public float getCost() {
        return cost;
    }

    public void setCost(float cost) { this.cost = cost; }

    public float getCriticalCost() {
        return criticalCost;
    }

    public void setCriticalCost(float criticalCost) {
        this.criticalCost = criticalCost;
    }

    public void setTimeReserve(float timeReserve) { this.timeReserve = timeReserve; }

    public float getTimeReserve() { return timeReserve; }

    public String getParentId() { return parentId; }

    public void setParentId(String parentId) { this.parentId = parentId; }

}
