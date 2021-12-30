package cz.uhk.todolist.model;

public class Task extends TimeManager {

    private float timeReserve = 0;

    //PRO ZAŘAZENÍ DO POSLOUPNOSTI TASKŮ
    private final int id;
    protected int previousTaskId;
    protected int nextTaskId;
    protected boolean onlyNode = false;

    //VÝPOČET KRITICKÉ CESTY
    private float cost = 0;
    private float criticalCost = 9999999;

    //CONSTRUCTORY
    public Task(String description, float estimatedTime, int id, int previousTaskId, int nextTaskId) {
        this.description = description;
        this.deadline = estimatedTime;
        this.id = id;
        this.previousTaskId = previousTaskId;
        this.nextTaskId = nextTaskId;
    }

    public Task(String description, int id, int previous, int next) {
        this.id = id;
        this.description = description;
        this.previousTaskId = previous;
        this.nextTaskId = next;
    }

    public Task(boolean startNode)
    {
        if(startNode)
        {
            this.id = 0;
            this.description = "Start Node";
        }
        else
        {
            this.id = 9999;
            this.description = "End Node";
        }
        onlyNode = true;
    }

    //METODY

    public int getPreviousTaskId() {
        return previousTaskId;
    }

    public void setPreviousTaskId(int previousTaskId) {
        this.previousTaskId = previousTaskId;
    }

    public int getNextTaskId() {
        return nextTaskId;
    }

    public int getTaskId() { return id; }

    public boolean isOnlyNode() { return onlyNode; }

    public void setNextTaskId(int nextTaskId) {
        this.nextTaskId = nextTaskId;
    }

    public void setTimeReserve(float timeReserve) { this.timeReserve = timeReserve; }

    public float getTimeReserve() { return timeReserve; }

    //CPM
    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        /*float newCost = deadline + cost;

        //Cestou od Start k End node přidáváme do hodnoty pouze největší z možných hran (pokud je na jeden node připojeno víc hran)
        if(newCost > this.cost)
            this.cost = deadline + cost;

         */

        this.cost = cost;
    }

    public float getCriticalCost() {
        return criticalCost;
    }

    public void setCriticalCost(float criticalCost) {
        this.criticalCost = criticalCost;
    }

}
