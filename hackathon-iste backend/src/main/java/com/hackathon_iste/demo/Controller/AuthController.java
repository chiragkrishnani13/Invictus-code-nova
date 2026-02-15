package com.hackathon_iste.demo.Controller;

import com.hackathon_iste.demo.Exceptions.InvalidEmail;
import com.hackathon_iste.demo.Exceptions.UserNameNotNull;
import com.hackathon_iste.demo.Service.AuthService;
import com.hackathon_iste.demo.model.users;
import com.hackathon_iste.demo.security.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;



@RestController
@CrossOrigin(origins = "http://localhost:4200") // Yeh line frontend ko allow karegi

public class AuthController {
    @Autowired
    AuthService authService;

    private static Logger logger= LoggerFactory.getLogger(AuthController.class);



    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> register(@RequestBody users user){
        try{
            authService.register(user);
            return ResponseEntity.ok().body(Map.of("body","User registered successfully"));
        } catch (UserNameNotNull e) {
            return ResponseEntity.badRequest().body(Map.of("body","Username should be not null!"));
        }
        catch (InvalidEmail e){

            return ResponseEntity.badRequest().body(Map.of("body","Email exists"));
        }
        catch (Exception e) {
            logger.info(e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("body", "some error occured!"));
        }
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody users user) {
        try {
            users dbUser = authService.getUserByEmail(user.getEmail());

            if (dbUser == null) {
                return ResponseEntity
                        .badRequest()
                        .body(Map.of("body", "Invalid email or password"));
            }

            boolean success = authService.login(
                    user.getEmail(),
                    user.getPassword()
            );

            if (!success) {
                return ResponseEntity
                        .badRequest()
                        .body(Map.of("body", "Invalid email or password"));
            }

            // 🔥 JWT generate
            String token = JWTUtil.generateToken(
                    dbUser.getUser_id(),
                    dbUser.getEmail(),
                    dbUser.getName()
            );

            return ResponseEntity.ok().body(
                    Map.of(
                            "token", token,
                            "message", "Login successful"
                    )
            );

        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(Map.of("body", "Something went wrong"));
        }
    }


}
