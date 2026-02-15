package com.hackathon_iste.demo.Service;


import com.hackathon_iste.demo.Exceptions.InvalidEmail;
import com.hackathon_iste.demo.Exceptions.PasswordIsNull;
import com.hackathon_iste.demo.Exceptions.UserNameNotNull;
import com.hackathon_iste.demo.Repository.AuthRepository;
import com.hackathon_iste.demo.model.users;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthService {
    @Autowired
    AuthRepository repository;
    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();


    public void register(users user){
        if(user.getName()==""){
            throw new UserNameNotNull();
        }
        if(!CheckIfEmailIsValid(user.getEmail())){
            throw new InvalidEmail();
        }
        if(repository.findbyemail(user.getEmail())!=null){
            throw new InvalidEmail();
        }
        if(user.getPassword()==""){
            throw new PasswordIsNull();
        }
        repository.save(
                user.getName(),
                user.getEmail(),
                passwordEncoder.encode(user.getPassword()),
                user.getCity(),
                user.getCountry(),
                user.getPhone()
        );
    }
    public boolean CheckIfEmailIsValid(String email){
        if(email==null) return false;

        String REGEX ="[A-Za-z0-9_+.-]+@[A-Za-z0-9_+.-]+\\.[a-z]{2,}$";

        Pattern pattern=Pattern.compile(REGEX);
        Matcher matcher=pattern.matcher(email);
        return matcher.matches();

    }
    public boolean login(String email, String password) {
        try {
            String dbHashedPassword =
                    repository.findPasswordByEmail(email);

            return passwordEncoder.matches(
                    password.trim(),
                    dbHashedPassword
            );

        } catch (Exception e) {
            return false; // email not found or any error
        }
    }
    public users getUserByEmail(String email) {
        return repository.findbyemail(email);
    }



}
