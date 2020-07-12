package com.bbubbush.jpa.many;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ManyToManyMember {
    @Id
    private Long memberId;

    @ManyToMany
    private List<ManyToManyTeam> teams = new ArrayList<ManyToManyTeam>();
}
