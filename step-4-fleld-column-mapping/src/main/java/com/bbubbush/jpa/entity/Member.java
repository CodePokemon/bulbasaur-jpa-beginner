package com.bbubbush.jpa.entity;

import com.bbubbush.jpa.type.RoleType;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Member {
    @Id
    private Long id;

    @Column(
            name = "name"           // 컬럼의 이름을 직접 매핑
            , nullable = true       // Null 여부를 선택. 기본은 true
//            , columnDefinition = "varchar(10) default 'bbubbush'"   // 직접 컬럼 정보를 기술할 수 있다.
            , length = 10           // 문자열에 한해 길이를 제한할 수 있다. 기본값은 255
            , insertable = true     // 등록가능여부를 선택한다. 기본값은 true.
            , updatable = true      // 변경가능여부를 선택한다. 기본값은 true.
            , unique = false        // 컬럼 내 중복값 여부를 선택한다. 기본값은 false.
    )
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
