package com.restFullApi.restFullApi.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity(name="user_detail")
public class Users {
    @Size(min=2,message = "should be at least 2 character")
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Past(message = "date of birth should be less current year")
    private LocalDate dob;

    @OneToMany(mappedBy = "users")
    @JsonIgnore
    private List<PostDetail> post;
    public Users(String name, Integer id, LocalDate dob) {
        this.name = name;
        this.id = id;
        this.dob = dob;
    }
    protected Users(){

    }
    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", dob=" + dob +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getDob() {
        return dob;
    }

}
