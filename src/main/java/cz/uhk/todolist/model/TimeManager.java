package cz.uhk.todolist.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

//TŘÍDA OBSAHUJE ATRIBUTY POTŘEBNÉ K VÝPOČTU ČASŮ
public class TimeManager {
    //Popis
    protected String description;

    //ČAS
    protected float deadline = 0; //odhadovaný čas pro splnění všech úloh v tomto procesu
    protected float currentTime = 0;  //uplynulý čas od založení procesu (zastaví se po dokončení celého procesu)

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

    public float getCurrentTime() {
        return currentTime;
    }

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

}
