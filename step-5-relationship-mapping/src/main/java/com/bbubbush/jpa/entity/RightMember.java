package com.bbubbush.jpa.entity;

import javax.persistence.*;

@Entity
public class RightMember {
    @Id @GeneratedValue @Column(name = "MEMBER_ID")
    private Long memberId;

    @Column(name = "USER_NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private RightTeam team;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RightTeam getTeam() {
        return team;
    }

    public void setTeam(RightTeam team) {
        this.team = team;
    }
}
