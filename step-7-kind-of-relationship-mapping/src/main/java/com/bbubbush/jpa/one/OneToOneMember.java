package com.bbubbush.jpa.one;

import javax.persistence.*;

@Entity
@Table(name = "ONE_TO_ONE_MEMBER")
public class OneToOneMember {
    @Id @GeneratedValue @Column(name = "MEMBER_ID")
    private Long memberId;
    @Column(name = "MEMBER_NAME")
    private String memberName;

    @OneToOne(mappedBy = "member")
    private OneToOneTeam team;

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

    public OneToOneTeam getTeam() {
        return team;
    }

    public void setTeam(OneToOneTeam team) {
        this.team = team;
    }
}
