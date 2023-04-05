package ibf2022.paf.assessment.server.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.repositories.TaskRepository;
import ibf2022.paf.assessment.server.services.TodoService;

// TODO: Task 4 and Task 8

@Controller
public class TasksController {

    @Autowired
    TaskRepository taskRepo;

    @Autowired
    TodoService todoService;

    @PostMapping("/task")
    public ModelAndView saveTask(@RequestParam MultiValueMap<String,String> form) throws ParseException{

        Iterator<String> it = form.keySet().iterator();

        String usernamekey = it.next();
        String username = form.getFirst(usernamekey);
        System.out.println(username);

        List<Task> taskList = new ArrayList<>();
        Integer counter = 0;

        Task inputTask = new Task();

        while(it.hasNext()){

            counter++;
            String key = it.next();
            System.out.println(counter);
            System.out.println("key:" + key);
            System.out.println("data:" + form.getFirst(key));

            if(counter ==1){
                inputTask.setDescription(form.getFirst(key));
            }
            if(counter ==2){
                inputTask.setPriority(Integer.parseInt(form.getFirst(key)));
            }
            if(counter ==3){
                counter = 0;
                Date date1= new SimpleDateFormat("yyyy-MM-dd").parse(form.getFirst(key));
                inputTask.setDueDate(date1);
                System.out.println(inputTask.toString());
                taskList.add(inputTask);

                //taskRepo.insertTask(inputTask,"1b80114c");
                
                inputTask = new Task();
            }

        }
        try{
            todoService.upsertTask(username, taskList);
        } catch(Exception e){
            ModelAndView mav = new ModelAndView("error");
            mav.setStatus(HttpStatusCode.valueOf(500));
            return mav;
        }
        

        ModelAndView mav = new ModelAndView("result");

        mav.setStatus(HttpStatusCode.valueOf(200));
        mav.addObject("taskCount", taskList.size());
        mav.addObject("username", username);

        return mav;
        
    }
    
}
