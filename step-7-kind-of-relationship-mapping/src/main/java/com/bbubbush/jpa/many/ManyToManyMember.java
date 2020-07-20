package com.bbubbush.jpa.many;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MANY_TO_MANY_MEMBER")
public class ManyToManyMember {
    @Id @GeneratedValue @Column(name = "MEMBER_ID")
    private Long memberId;
    @Column(name = "MEMBER_NAME")
    private String memberName;

    @ManyToMany
    @JoinColumn(name = "TEAM_ID")
    @JoinTable(name = "CONNECTION_TABLE")
    private List<ManyToManyTeam> teams = new ArrayList<ManyToManyTeam>();

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public List<ManyToManyTeam> getTeams() {
        return teams;
    }

    public void setTeams(List<ManyToManyTeam> teams) {
        this.teams = teams;
    }
}
