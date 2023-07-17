package com.marcelo.webhook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WebhookRepository extends JpaRepository<WebhookEntity, String> {
}

