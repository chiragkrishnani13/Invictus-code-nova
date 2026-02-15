package com.hackathon_iste.demo.Repository;


import lombok.extern.slf4j.Slf4j;
import com.hackathon_iste.demo.model.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Slf4j
@Repository
public class AuthRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public users findbyemail(String email) {
        String query = "Select user_id,name,email,password,city,country,phone,active_yn " + "FROM users WHERE email=?";
        try {
            users users1 = jdbcTemplate.queryForObject(query, (ResultSet resultSet, int rowNum) -> {
                users users2 = new users();
                users2.setUser_id(resultSet.getLong("user_id"));
                users2.setName(resultSet.getString("name"));
                users2.setEmail(resultSet.getString("email"));
                users2.setPassword(resultSet.getString("password"));
                users2.setCity(resultSet.getString("city"));
                users2.setCountry(resultSet.getString("country"));
                users2.setPhone(resultSet.getString("phone"));
                users2.setActive_yn(resultSet.getInt("active_yn"));
                return users2;
            }, email);
            return users1;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    public void save(String name, String email, String password, String city, String country,String phone) {
        String query = "INSERT INTO users(name,email,password,city,country,phone,active_yn)" + "Values(?, ?, ?, ?, ?, ?, 1)";
        jdbcTemplate.update(query, name, email, password, city, country, phone);

    }
    public String findPasswordByEmail(String email) {

        String sql = """
            SELECT password
            FROM users
            WHERE email = ?
              AND active_yn = 1
        """;

        return jdbcTemplate.queryForObject(
                sql,
                String.class,
                email.trim()
        );
    }
}
