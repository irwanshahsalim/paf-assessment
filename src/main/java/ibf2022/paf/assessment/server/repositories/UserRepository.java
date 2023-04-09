package ibf2022.paf.assessment.server.repositories;

// TODO: Task 3

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.paf.assessment.server.models.User;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String insertUser(User user) {
        String INSERT_USER_SQL = "insert into user (username, name) values (?, ?)";
        jdbcTemplate.update(INSERT_USER_SQL, user.getUsername(), user.getName());
        return findUserByUsername(user.getUsername()).get().getUserId();
    }

    public Optional<User> findUserByUsername(String username) {
        String SELECT_USER_SQL = "select user_id, username, name from user where username = ?";
        return jdbcTemplate.query(SELECT_USER_SQL, (rs, rowNum) -> {
            User user = new User();
            user.setUserId(rs.getString("user_id"));
            user.setUsername(rs.getString("username"));
            user.setName(rs.getString("name"));
            return user;
        }, username).stream().findFirst();
    }
}