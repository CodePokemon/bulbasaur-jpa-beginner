package com.bbubbush.jpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id @GeneratedValue
    private Long teamId;

    private String name;

    @OneToMany(mappedBy = "team"
            , cascade = CascadeType.ALL
//            , orphanRemoval = true
    )
    private List<Member> members = new ArrayList<Member>();

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMember(Member member) {
        members.add(member);
        member.setTeam(this);
    }

    public List<Member> getMembers() {
        return members;
    }
}
