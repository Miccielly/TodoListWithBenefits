package cz.uhk.todolist.model;

import java.time.LocalDateTime;

//TŘÍDA OBSAHUJE ATRIBUTY SPOLEČNÉ PRO VŠECHNY TŘÍDY (PŮVODNĚ HLAVNĚ K VÝPOČTU ČASŮ)
public class WorkUnit {
    //Popis
    protected String description;

    //ČAS
    protected float deadline; //odhadovaný čas pro splnění všech úloh v tomto procesu

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

}
