package com.marcelo.webhook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class WebhookApplication {

    private static final Logger logger = LoggerFactory.getLogger(WebhookApplication.class);

    public static void main(final String[] args) {
        String port = System.getenv("PORT");
        if (port == null) {
            port = "8080";
            logger.warn("$PORT environment variable not set, defaulting to 8080");
        }
        SpringApplication app = new SpringApplication(WebhookApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", port));

        app.run(args);
        logger.info("Webhook started");
    }
}
