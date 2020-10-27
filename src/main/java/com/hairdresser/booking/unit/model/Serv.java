package com.hairdresser.booking.unit.model;

import org.springframework.data.annotation.Id;

import java.util.UUID;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Value
//@Entity
//@Table

// this things above, it's a better way to do all getters, setter and constructors, but it doesn't work yet
public class Serv {

    @Id
    private UUID id;
    private String name;
    private String description;
    private int time;
    private float price;
    //private String imgUrl;        maybe in future

    public Serv(UUID id, String name, String description, int time, float price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.time = time;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
