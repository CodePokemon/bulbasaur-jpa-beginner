package com.bbubbush.jpa.many;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MANY_TO_MANY_TEAM")
public class ManyToManyTeam {
    @Id @GeneratedValue @Column(name = "TEAM_ID")
    private Long teamId;
    @Column(name = "TEAM_NAME")
    private String teamName;

    @ManyToMany
    private List<ManyToManyMember> teams = new ArrayList<ManyToManyMember>();

}
