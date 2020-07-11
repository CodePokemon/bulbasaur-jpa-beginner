package com.bbubbush.jpa.many;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ManyToManyTeam {
    @Id
    private Long teamId;
}
