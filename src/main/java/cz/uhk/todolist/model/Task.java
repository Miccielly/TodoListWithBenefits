package cz.uhk.todolist.model;

public class Task extends TimeManager {

    private float timeReserve = 0;

    //PRO ZAŘAZENÍ DO POSLOUPNOSTI TASKŮ
    private final int id;
    protected int previousTaskId;
    protected int nextTaskId;

    //VÝPOČET KRITICKÉ CESTY
    private int cost;
    private int criticalCost;

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

    public void setNextTaskId(int nextTaskId) {
        this.nextTaskId = nextTaskId;
    }

    public void setTimeReserve(float timeReserve) { this.timeReserve = timeReserve; }

    public float getTimeReserve() { return timeReserve; }

}
