package com.spring.library.entity;
/*
  User: Ufuk
  Date: 13.09.2020 21:15
*/

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class EntityBase implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private Date createDate;

    @Column
    private Date updateDate;

    @Column
    private boolean active;

    @Column
    private String operationType;

    @PrePersist
    public void onPrePersist() {
        this.setOperationType("SAVE");
        this.setCreateDate(new Date());
        this.setUpdateDate(new Date());
        this.setActive(true);
    }

    @PreUpdate
    public void onPreUpdate() {
        this.setOperationType("UPDATE");
        this.setUpdateDate(new Date());
    }

    @PreRemove
    public void onPreRemove() {
        this.setOperationType("DELETE");
        this.setUpdateDate(new Date());
        this.setActive(false);
    }
}