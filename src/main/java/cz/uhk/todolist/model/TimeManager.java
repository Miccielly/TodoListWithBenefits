package cz.uhk.todolist.model;

//TŘÍDA OBSAHUJE ATRIBUTY POTŘEBNÉ K VÝPOČTU ČASŮ
public class TimeManager {
    //Popis
    protected String description;

    //ČAS
    protected float estimatedDeadline; //odhadovaný čas pro splnění všech úloh v tomto procesu
    protected float estimatedTimeSum; //suma času úloh
    protected float currentTime;  //uplynulý čas od založení procesu (zastaví se po dokončení celého procesu)



    //METODY
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getEstimatedDeadline() {
        return estimatedDeadline;
    }

    public void setEstimatedDeadline(int estimatedDeadline) {
        this.estimatedDeadline = estimatedDeadline;
    }

    public float getEstimatedTimeSum() {
        return estimatedTimeSum;
    }

    public void setEstimatedTimeSum(float estimatedTimeSum) {
        this.estimatedTimeSum = estimatedTimeSum;
    }

    public float getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(float currentTime) {
        this.currentTime = currentTime;
    }


}
