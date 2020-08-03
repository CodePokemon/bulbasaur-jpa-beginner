package com.bbubbush.jpa.entity;

import javax.persistence.*;

@Entity
public class LazyLoaindMember {
    @Id @GeneratedValue
    private long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamId")
    private LazyLoadingTeam team;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LazyLoadingTeam getTeam() {
        return team;
    }

    public void setTeam(LazyLoadingTeam team) {
        this.team = team;
    }
}
