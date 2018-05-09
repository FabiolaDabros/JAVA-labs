package app;

import java.io.Serializable;
import java.time.LocalDate;

public class Resource implements Serializable{

    private String title;
    private String priority;
    private LocalDate date = LocalDate.now();
    private String text;
    private String priorityColor;
    private Status status;

    public Resource(String title, String priority, String text, int year, int month, int day) {
        this.title = title;
        this.priority = priority;
        this.text = text;
        date = LocalDate.of(year, month, day);
        this.status=Status.toDo;

        if(priority.equals( "LOW PRIORITY") ) priorityColor ="pink";
        else if(priority.equals("MEDIUM PRIORITY") ) priorityColor ="red";
        else if(priority.equals("HIGH PRIORITY")) priorityColor ="black";
    }



    @Override
    public String toString() {
        return (title + ", " +  priority + ", " +text + ", " +
                date.getYear() + ", " + date.getMonthValue() + ", " +
                date.getDayOfMonth() + ", " + status);
    }
    public String getTitle() {
        return title;
    }
    public String getPriority() {
        return priority;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getText() {
        return text;
    }
    public String getPriorityColor() {
        return priorityColor;
    }
    public int getMonth(){ return date.getMonthValue(); }
    public int getDay(){ return date.getDayOfMonth(); }
    public void setStatus(Status status) { this.status = status; }
    public Status getStatus() { return status; }
    public int getYear() { return date.getYear();
    }


}
