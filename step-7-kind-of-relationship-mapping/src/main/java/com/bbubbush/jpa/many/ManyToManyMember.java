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
    private List<ManyToManyTeam> teams = new ArrayList<ManyToManyTeam>();
}
