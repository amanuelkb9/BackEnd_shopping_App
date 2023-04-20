package edu.miu.shopmartbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("https://example.com", "https://www.example.com")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "PATCH")
            .allowedHeaders("*")
            .allowCredentials(true)
            .maxAge(3600);
  }
}
