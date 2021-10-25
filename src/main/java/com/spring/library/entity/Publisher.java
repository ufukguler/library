package com.spring.library.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table
public class Publisher  extends EntityBase{
    @Column(unique = true)
    private String name;

    @Column
    private String comment;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "publisher")
    @JsonBackReference
    private Set<Book> books;
}
