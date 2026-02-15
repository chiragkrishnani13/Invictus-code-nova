package com.hackathon_iste.demo.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dxji1sztz");
        config.put("api_key", "286349591738831");
        config.put("api_secret", "LHqhXL2P7gfT7B-6Y01fKpFFA10");
        return new Cloudinary(config);
    }
}
