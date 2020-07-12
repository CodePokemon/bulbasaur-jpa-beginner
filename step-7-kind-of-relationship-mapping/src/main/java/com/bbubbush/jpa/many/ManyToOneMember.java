package com.bbubbush.jpa.many;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ManyToOneMember {
    @Id
    private Long memberId;

    @ManyToOne
    @JoinColumn(name = "member")
    private ManyToOneTeam team;
}
