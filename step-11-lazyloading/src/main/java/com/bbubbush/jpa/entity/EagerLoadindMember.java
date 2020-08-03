package com.bbubbush.jpa.entity;

import javax.persistence.*;

@Entity
public class EagerLoadindMember {
    @Id @GeneratedValue
    private long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teamId")
    private EagerLoadingTeam team;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EagerLoadingTeam getTeam() {
        return team;
    }

    public void setTeam(EagerLoadingTeam team) {
        this.team = team;
    }
}
