package cz.uhk.todolist.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

//TŘÍDA OBSAHUJE ATRIBUTY POTŘEBNÉ K VÝPOČTU ČASŮ
public class TimeManager {
    //Popis
    protected String description;

    //ČAS
    protected float deadline; //odhadovaný čas pro splnění všech úloh v tomto procesu
    protected float currentTime;  //uplynulý čas od založení procesu (zastaví se po dokončení celého procesu)


    //Dates
    //protected LocalDateTime startDate;
    protected String startDate = "";
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

    public void setDeadline(float deadline) { this.deadline = deadline; }

    public float getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(float currentTime) { this.currentTime = currentTime; }

    public void updateCurrentTime()
    {
        //TODO přidat někde brát čas
    }

    public LocalDateTime getCurrentDate() { return LocalDateTime.now(); }

    public void getDateDifference()
    {
        //Duration duration = Duration.between(startDate, getCurrentDate());

        //System.out.println("timeElapsed: " + duration);
    }
    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

}
