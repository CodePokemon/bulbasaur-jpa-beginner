package com.bbubbush.jpa.many;

import javax.persistence.*;

@Entity
@Table(name = "MANY_TO_ONE_MEMBER")
public class ManyToOneMember {
    @Id @GeneratedValue @Column(name = "MEMBER_ID")
    private Long memberId;
    @Column(name = "MEMBER_NAME")
    private String memberName;

    @ManyToOne
    @JoinColumn(name = "member")
    private ManyToOneTeam team;

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

    public ManyToOneTeam getTeam() {
        return team;
    }

    public void setTeam(ManyToOneTeam team) {
        this.team = team;
    }
}
