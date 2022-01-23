package cz.uhk.todolist.utils;

import java.sql.Time;

public class TimeStore {
    private float minutes = 33;
    private float seconds = 33;
    private ProcessStore processStore;
    private String selectedProcessId;
    public TimeStore(float minutes, float seconds, ProcessStore processStore)
    {
        this.minutes = minutes;
        this.seconds = seconds;
        this.processStore = processStore;
    }

    public TimeStore(){}

    public float getMinutes() {
        return minutes;
    }

    public void setMinutes(float minutes) {
        this.minutes = minutes;
    }

    public float getSeconds() {
        return seconds;
    }

    public void setSeconds(float seconds) {
        this.seconds = seconds;
    }

    public ProcessStore getProcessStore() {
        return processStore;
    }

    public void setProcessStore(ProcessStore processStore) {
        this.processStore = processStore;
    }

    public String getSelectedProcessId() {
        return selectedProcessId;
    }

    public void setSelectedProcessId(String selectedProcessId) {
        this.selectedProcessId = selectedProcessId;
    }
}
