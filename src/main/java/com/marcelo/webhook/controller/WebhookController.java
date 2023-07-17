package com.marcelo.webhook.controller;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.marcelo.webhook.repository.WebhookEntity;
import com.marcelo.webhook.repository.WebhookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("webhook")
class WebhookController {
    private static final Logger logger = LoggerFactory.getLogger(WebhookController.class);
    private final WebhookRepository webhookRepository;

    WebhookController(WebhookRepository webhookRepository) {
        this.webhookRepository = webhookRepository;
    }

    @GetMapping(value = "/{id}", produces = { "application/json" })
    ResponseEntity<String> getMessage(@PathVariable("id") String id) {
        try{
            WebhookEntity webhookEntity =  webhookRepository.findById(id).orElseThrow();
            return ResponseEntity.ok(webhookEntity.getData());
        }catch(Exception e){
            logger.error("Fail to retrieve webhook message", e);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = { "application/json" })
    void saveWebhookMessage(@RequestBody String payload) {
        try{
            Object parseJson = Configuration.defaultConfiguration().jsonProvider().parse(payload);
            String taskId = JsonPath.read(parseJson, "$._p.analytics.meta.data.taskId");
            WebhookEntity webhookEntity = new WebhookEntity(taskId, payload);
            WebhookEntity savedEntity = webhookRepository.save(webhookEntity);
            logger.info("Stored webhook entity {}", savedEntity);
        }catch(Exception e){
            logger.error("Fail to store webhook message", e);
        }
    }
}