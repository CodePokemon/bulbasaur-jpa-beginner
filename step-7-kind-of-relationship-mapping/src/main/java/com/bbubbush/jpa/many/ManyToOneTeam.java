package com.bbubbush.jpa.many;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ManyToOneTeam {
    @Id
    private Long teamId;

    @OneToMany
    private List<ManyToOneMember> members = new ArrayList<ManyToOneMember>();
}
