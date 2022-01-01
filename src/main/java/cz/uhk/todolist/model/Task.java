package cz.uhk.todolist.model;

import org.springframework.data.annotation.Id;

public class Task extends TimeManager {

    private float timeReserve = 0;

    //PRO ZAŘAZENÍ DO POSLOUPNOSTI TASKŮ
    @Id
    private String id;

    private String parentId;

    protected String previousTaskId;
    protected String nextTaskId;
    protected boolean onlyNode = false;

    //VÝPOČET KRITICKÉ CESTY
    private float cost = 0;
    private float criticalCost = 9999999;

    //CONSTRUCTORY
    public Task(String description, float estimatedTime,  String previousTaskId, String nextTaskId) {
        this.description = description;
        this.deadline = estimatedTime;
        this.previousTaskId = previousTaskId;
        this.nextTaskId = nextTaskId;
    }

    public Task(String description, String previous, String next) {

        this.description = description;
        this.previousTaskId = previous;
        this.nextTaskId = next;
    }

    public Task(boolean startNode)
    {
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

    public String getTaskId() { return id; }

    public boolean isOnlyNode() { return onlyNode; }

    public void setNextTaskId(String nextTaskId) {
        this.nextTaskId = nextTaskId;
    }

    public void setTimeReserve(float timeReserve) { this.timeReserve = timeReserve; }

    public float getTimeReserve() { return timeReserve; }

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

}
