package com.store.webstore.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

     @Entity // This tells Hibernate to make a table out of this class
public class Automobile {
        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private Integer id;
        private String name;
        private Integer Cost;
        private String Body;

         public String getBody() {
             return Body;
         }

         public void setBody(String body) {
             Body = body;
         }

         public Integer getId() {
             return id;
         }

         public void setId(Integer id) {
             this.id = id;
         }


         public String getName() {
             return name;
         }

         public void setName(String name) {
             this.name = name;
         }

         public Integer getCost() {
             return Cost;
         }

         public void setCost(Integer cost) {
             Cost = cost;
         }
     }

