package com.bbubbush.jpa.many;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ManyToManyTeam {
    @Id
    private Long teamId;

    @ManyToMany
    private List<ManyToManyMember> members = new ArrayList<ManyToManyMember>();
}
