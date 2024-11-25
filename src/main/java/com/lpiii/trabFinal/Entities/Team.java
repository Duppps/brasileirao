package com.lpiii.trabFinal.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Entity
@Getter
@Setter
public class Team {
    @Id
    @Column(name="id")
    private Long id;


}
