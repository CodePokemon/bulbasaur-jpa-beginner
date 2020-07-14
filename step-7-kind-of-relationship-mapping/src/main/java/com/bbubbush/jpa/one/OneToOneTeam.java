package com.bbubbush.jpa.one;

import javax.persistence.*;

@Entity
@Table(name = "ONE_TO_ONE_TEAM")
public class OneToOneTeam {
    @Id @GeneratedValue @Column(name = "TEAM_ID")
    private Long teamId;
    @Column(name = "TEAM_NAME")
    private String teamName;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private OneToOneMember member;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public OneToOneMember getMember() {
        return member;
    }

    public void setMember(OneToOneMember member) {
        this.member = member;
    }
}
