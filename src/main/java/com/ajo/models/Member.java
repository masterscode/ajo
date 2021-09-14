package com.ajo.models;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Member extends User{
    private LocalDate dateJoined = LocalDate.now();

    @OneToMany(targetEntity = Contribution.class)
    private Set<Contribution> contributions;

    @OneToMany(targetEntity = Request.class)
    private Set<Request> requests;

}
