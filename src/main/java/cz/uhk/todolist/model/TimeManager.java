package cz.uhk.todolist.model;

//TŘÍDA OBSAHUJE ATRIBUTY POTŘEBNÉ K VÝPOČTU ČASŮ
public class TimeManager {
    //Popis
    protected String description;

    //ČAS
    protected float deadline; //odhadovaný čas pro splnění všech úloh v tomto procesu
    protected float currentTime;  //uplynulý čas od založení procesu (zastaví se po dokončení celého procesu)

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
}
