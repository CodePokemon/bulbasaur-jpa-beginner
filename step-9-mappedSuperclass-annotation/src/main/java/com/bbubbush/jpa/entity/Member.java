package com.bbubbush.jpa.entity;

import javax.persistence.*;

@Entity
public class Member extends BaseEntity {
    @Id @GeneratedValue
    private long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private Team team;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
