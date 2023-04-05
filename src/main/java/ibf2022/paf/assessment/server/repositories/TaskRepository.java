package ibf2022.paf.assessment.server.repositories;

// TODO: Task 6

import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.Task;

// TODO: Task 6

@Repository
public class TaskRepository {

    @Autowired
    JdbcTemplate template;

    private final String INSERT_TASK_SQL = "insert into task (description, priority, due_date, user_id) values (?,?,?,?)";

    public void insertTask(Task task, String userId){

        Date sqlDate = new Date(task.getDueDate().getTime()); 

        int added = template.update(INSERT_TASK_SQL, task.getDescription(), task.getPriority(), sqlDate,userId);

        System.out.println(added);

    }

}