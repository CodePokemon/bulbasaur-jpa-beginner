package com.bbubbush.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Member {
    @Id
    private Long id;
    private String name;
    private LocalDateTime birthDay;

    // 사용하지 않지만 JPA 내부적으로 기본 생성자가 필요하기 때문에 생성
    protected Member() {}

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDateTime birthDay) {
        this.birthDay = birthDay;
    }
}
