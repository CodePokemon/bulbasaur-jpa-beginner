package com.bbubbush.jpa.one;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "One_TO_MANY_MEMBER")
public class OneToManyMember {
    @Id @GeneratedValue @Column(name = "MEMBER_ID")
    private Long memberId;
    @Column(name = "MEMBER_NAME")
    private String memberName;

    @OneToMany
    private List<OneToManyTeam> teams = new ArrayList<OneToManyTeam>();
}
