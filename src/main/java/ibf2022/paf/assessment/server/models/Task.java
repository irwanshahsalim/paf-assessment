package ibf2022.paf.assessment.server.models;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// TODO: Task 4

public class Task {

    private String userId;
    private String description;
    private Integer priority;
    private Date dueDate;

    public Task() {}

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getPriority() { return priority; }
    public void setPriority(Integer priority) { this.priority = priority; }
    public Date getDueDate() { return dueDate; }
    public void setDueDate(Date dueDate) { this.dueDate = dueDate; }

    public static Map<String, String> parseResultMap(String payload) {
        Map<String, String> resultMap = new TreeMap<>();

        String[] params = payload.split("&");
        for (String param : params) {

            String[] parts = param.split("=");

            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].replace("+", " ");
            }

            if (parts.length != 2) { continue; }

            String key = parts[0];
            String value = parts[1];
            resultMap.put(key, value);
        }
        return resultMap;
    }

    public static List<Task> createTaskList(Integer count, Map<String, String> resultMap, String userId) throws ParseException {

        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Task task = new Task();
            task.setUserId(userId);
            task.setDescription(resultMap.get("description-" + i));
            task.setPriority(Integer.parseInt(resultMap.get("priority-" + i)));
            task.setDueDate(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(resultMap.get("dueDate-" + i)).getTime()));
            taskList.add(task);
        }
        return taskList;
    }
}
