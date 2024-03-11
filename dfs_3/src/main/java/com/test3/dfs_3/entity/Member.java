package com.test3.dfs_3.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eid;
    private String firstName;
    private String lastName;
    private LocalDate dol;
    private String level;
    private String location;
    private int overallExp;
    private String status;

    @ElementCollection
    @MapKeyColumn(name="skill")
    @Column(name="experience")
    private Map<String, Integer> skills;

    public LocalDate getDol() {
        return this.dol;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getLocation() {
        return this.location;
    }

    public String getLevel() {
        return this.level;
    }

    public Map<String, Integer> getSkills() {
        return this.skills;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

