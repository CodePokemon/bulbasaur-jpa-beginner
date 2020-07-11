package com.bbubbush.jpa.one;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class OneToManyMember {
    @Id
    private Long memberId;
}
