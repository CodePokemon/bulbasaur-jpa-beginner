package com.bbubbush.jpa.many;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ManyToOneMember {
    @Id
    private Long memberId;
}
