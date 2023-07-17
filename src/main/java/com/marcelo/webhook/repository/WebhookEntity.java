package com.marcelo.webhook.repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "webhook")
public class WebhookEntity {
    @Id
    private String id;
    @Lob
    private String data;

    public WebhookEntity(){
    }
    public WebhookEntity(String id, String data){
        this.id = id;
        this.data = data;
    }

    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getData(){
        return this.data;
    }
    public void setData(String data){
        this.data = data;
    }

    @Override
    public String toString() {
        return "WebhookEntity [data=" + data + ", id=" + id + "]";
    }

}
