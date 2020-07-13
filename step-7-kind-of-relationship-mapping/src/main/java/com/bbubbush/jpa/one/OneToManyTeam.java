package com.bbubbush.jpa.one;

import javax.persistence.*;

@Entity
@Table(name = "ONE_TO_MANY_TEAM")
public class OneToManyTeam {
    @Id @GeneratedValue @Column(name = "TEAM_ID")
    private Long teamId;
    @Column(name = "TEAM_NAME")
    private String teamName;
}
