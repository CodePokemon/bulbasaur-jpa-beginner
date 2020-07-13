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
}
