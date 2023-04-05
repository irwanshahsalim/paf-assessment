package ibf2022.paf.assessment.server.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ibf2022.paf.assessment.server.models.Task;
import ibf2022.paf.assessment.server.models.User;
import ibf2022.paf.assessment.server.repositories.TaskRepository;
import ibf2022.paf.assessment.server.repositories.UserRepository;

// TODO: Task 7

@Service
public class TodoService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    TaskRepository taskRepo;

    @Transactional
    public void upsertTask(String username, List<Task> taskList){

        Optional<User> result = userRepo.findUserByUsername(username);
    

		if(result.isEmpty()){
			System.out.println("User not found, creating user");
            
            //create new user
            User newUser = new User();
            newUser.setUsername(username);
            newUser.setName(username.toUpperCase());
            String newUserId = userRepo.insertUser(newUser);
            
            //add tasks
            for(Task task: taskList){
                System.out.println(task + newUserId);
                taskRepo.insertTask(task, newUserId);
            }

		} else{
			System.out.println(result.get());
            User foundUser = result.get();
            String userId = foundUser.getUserId();

            //add tasks
            for(Task task: taskList){
                System.out.println(task + userId);
                taskRepo.insertTask(task, userId);
            }
		}
        


    }

}

