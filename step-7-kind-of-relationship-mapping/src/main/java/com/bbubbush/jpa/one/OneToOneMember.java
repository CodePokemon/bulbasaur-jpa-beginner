package com.bbubbush.jpa.one;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OneToOneMember {
    @Id
    private Long memberId;
}
