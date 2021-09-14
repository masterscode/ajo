package com.ajo.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ContributionCycle extends BaseEntity{
    private String name;

    private int slots;

    private Double monthlyAmount;

    @OneToMany(targetEntity = Request.class)
    Set<Request> requests;

    @OneToMany(targetEntity = Contribution.class)
    Set<Contribution> contributions;
}
