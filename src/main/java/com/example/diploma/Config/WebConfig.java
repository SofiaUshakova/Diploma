package com.example.diploma.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
class WebConfig implements WebMvcConfigurer {
    @Value("${cors.origins}")
    private String origins;

    @Value("${cors.headers}")
    private String allowedHeaders;

    @Value("${cors.methods}")
    private String allowedMethods;

    @Value("${cors.credentials}")
    private boolean allowCredentials;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(allowCredentials)
                .allowedOrigins(origins)
                .allowedMethods(allowedMethods)
                .allowedHeaders(allowedHeaders);
    }
}
