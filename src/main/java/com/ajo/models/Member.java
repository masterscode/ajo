package com.ajo.models;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Member extends User{
    private LocalDate dateJoined;

    @OneToOne
    User user;

    @OneToMany(targetEntity = Contribution.class)
    private Set<Contribution> contributions;

    @OneToMany(targetEntity = Request.class)
    private Set<Request> requests;

}
