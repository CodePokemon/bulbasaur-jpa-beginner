package com.bbubbush.jpa.entity;

import javax.persistence.Entity;

@Entity
public class Book extends Item {
    private String director;
    private String actor;
}
