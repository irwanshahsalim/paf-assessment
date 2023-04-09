package ibf2022.paf.assessment.server.controllers;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.UserRepository;
import ibf2022.paf.assessment.server.services.TodoService;

// TODO: Task 4, Task 8

@Controller
public class TasksController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private TodoService todoSvc;

    @PostMapping(path="/task")
    public ModelAndView processSave(@RequestBody String payload) throws ParseException {

        Boolean bContinue = true;
        Boolean bUpserted = false;

        Map<String, String> taskMap = Task.parseResultMap(payload);

        Integer num = (int) taskMap.keySet().stream().filter(t -> t.startsWith("description-")).count();

        String username = taskMap.get("username");

        if (username.contains(" "))
            bContinue = false;

        if (bContinue) {
            Optional<User> opt = userRepo.findUserByUsername(username);
            String userId = "";
            if (opt.isPresent()) {
                User user = opt.get();
                userId = user.getUserId();
            }

            List<Task> taskList = Task.createTaskList(num, taskMap, userId);

            bUpserted = todoSvc.upsertTask(taskList, username);
        }

        if (bUpserted || bContinue) {
            Map<String, String> models = new HashMap<>();
            models.put("taskCount", String.valueOf(num));
            models.put("username", username);
            return new ModelAndView("result.html", models, HttpStatus.valueOf(200));
        } else {
            return new ModelAndView("error.html", HttpStatus.valueOf(500));
        }
    }
}