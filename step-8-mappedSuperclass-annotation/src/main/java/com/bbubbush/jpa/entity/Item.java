package com.bbubbush.jpa.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public class Item {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private Long price;
}
