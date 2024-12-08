package com.restFullApi.restFullApi.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name="post_table")
public class PostDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="discribe")
    private String discribe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Users users;
}
