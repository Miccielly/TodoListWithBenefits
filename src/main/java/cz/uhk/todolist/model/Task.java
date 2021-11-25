package cz.uhk.todolist.model;

public class Task extends TimeManager {

    //PRO ZAŘAZENÍ DO POSLOUPNOSTI TASKŮ
    protected int previousTaskPosition;
    protected int nextTaskPosition;

    //CONSTRUCTORY
    public Task(String description, float estimatedTime,int previousTaskId, int nextTaskId) {
        this.description = description;
        this.estimatedDeadline = estimatedTime;
        this.previousTaskPosition = previousTaskId;
        this.nextTaskPosition = nextTaskId;
    }

    public Task(String description, int previous, int next)
    {
        this.description = description;
        this.previousTaskPosition = previous;
        this.nextTaskPosition = next;
    }

    //METODY

    public int getPreviousTaskPosition() {
        return previousTaskPosition;
    }

    public void setPreviousTaskPosition(int previousTaskPosition) {
        this.previousTaskPosition = previousTaskPosition;
    }

    public int getNextTaskPosition() {
        return nextTaskPosition;
    }

    public void setNextTaskPosition(int nextTaskPosition) {
        this.nextTaskPosition = nextTaskPosition;
    }

}
