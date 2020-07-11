package com.bbubbush.jpa.many;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ManyToOneTeam {
    @Id
    private Long teamId;
}
