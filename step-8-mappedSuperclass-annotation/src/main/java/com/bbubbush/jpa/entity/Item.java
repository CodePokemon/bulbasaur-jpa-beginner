package com.bbubbush.jpa.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 전략을 변경하면서 확인할 수 있다.
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private Long price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
