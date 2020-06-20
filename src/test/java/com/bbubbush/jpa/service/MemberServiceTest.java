package com.bbubbush.jpa.service;

import com.bbubbush.jpa.entity.Member;
import org.junit.Test;

import static org.junit.Assert.*;

public class MemberServiceTest {
    private MemberService service = new MemberService();

    @Test
    public void memberFind() {
        // given
        Long findId = 1L;
        String expectedName = "HelloJPA";

        // when
        Member member = service.findMember(findId);

        // then
        assertEquals(expectedName, member.getName());
    }

    @Test
    public void memberUpdateName() {
        // given
        Long findId = 1L;
        String afterName = "hALLOjpa";
        String expectedName = "hALLOjpa";

        // when
        service.updateName(findId, afterName);
        Member afterMember = service.findMember(findId);

        // then
        assertEquals(expectedName, afterMember.getName());
    }

    @Test
    public void memberInsert() {
        // given
        Long createId = 1L;
        String createName = "HelloJPA";
        String expectedName = "HelloJPA";
        Member member = new Member(createId, createName);

        // when
        service.insertMember(member);
        Member insertMember = service.findMember(createId);

        // then
        assertEquals(expectedName, insertMember.getName());
    }

    @Test
    public void memberRemove() {
        // given
        Long removeId = 1L;

        // when
        service.removeMember(removeId);
        Member member = service.findMember(removeId);

        // then
        assertNull(member);
    }

}