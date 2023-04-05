package ibf2022.paf.assessment.server.models;

// TODO: Task 4


import java.util.Date;

// TODO: Task 4

public class Task {

    private String description;
    private Integer priority;
    private Date dueDate;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getPriority() {
        return priority;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    @Override
    public String toString() {
        return "Task [description=" + description + ", priority=" + priority + ", dueDate=" + dueDate + "]";
    }

    
    
    
}