package cz.uhk.todolist.model;

//TŘÍDA OBSAHUJE ATRIBUTY POTŘEBNÉ K VÝPOČTU ČASŮ
public class TimeManager {
    //Popis
    protected String description;

    //ČAS
    protected float deadline; //odhadovaný čas pro splnění všech úloh v tomto procesu
    protected float deadLineSum; //suma času úloh
    protected float currentTime;  //uplynulý čas od založení procesu (zastaví se po dokončení celého procesu)
    //protected float currentTimeSum; //suma aktuálních časů



    //METODY
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public float getDeadLineSum() {
        return deadLineSum;
    }

    public float getCurrentTime() {
        return currentTime;
    }

    public void updateCurrentTime()
    {
        //TODO přidat někde brát čas
    }

    public float getTimeDifference()
    {
        updateCurrentTime();
        return (getCurrentTime()- getDeadline());
    }

}
