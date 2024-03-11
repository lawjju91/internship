package com.test3.dfs_3.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Entity
@Data
public class Demand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;
    private String mainName;
    private String level;
    private String city;
    private String status;
    private int duration;
    private LocalDate startDate;

    @ElementCollection
    @MapKeyColumn(name="skill")
    @Column(name="level")
    private Map<String, Integer> skills;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLevel() {
        return this.level;
    }

    public Map<String, Integer> getSkills() {
        return this.skills;
    }
}
