package com.bbubbush.jpa.one;

import javax.persistence.*;

@Entity
@Table(name = "One_TO_MANY_MEMBER")
public class OneToManyMember {
    @Id @GeneratedValue @Column(name = "MEMBER_ID")
    private Long memberId;
    @Column(name = "MEMBER_NAME")
    private String memberName;

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
}
