package ibf2022.paf.assessment.server.repositories;

// TODO: Task 3

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ibf2022.paf.assessment.server.models.User;

@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate template;

    private final String GET_USER_SQL = "select * from user where username = ?";

    private final String INSERT_USER_SQL = "insert into user (user_id,username,name) values (?,?,?)";

    public Optional<User> findUserByUsername (String username){

        User user;

        try{
            user = template.queryForObject(GET_USER_SQL, BeanPropertyRowMapper.newInstance(User.class), username);
        } catch(EmptyResultDataAccessException e){
            //e.printStackTrace();
            return Optional.empty();
        }

        return Optional.of(user);

    }

    public String insertUser (User user){

        String uuid = UUID.randomUUID().toString().substring(0, 8);

        Integer added = template.update(INSERT_USER_SQL, uuid,user.getUsername(),user.getName());

        if(added==1){
            return uuid;
        } else{
            return "Insertion failed";
        }

    }





}