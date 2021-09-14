package com.ajo.models;

import com.ajo.models.enums.PaymentType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class Contribution extends BaseEntity{

    @ManyToOne
    private Member member;

    @ManyToOne
    private ContributionCycle contributionCycle;

    private double amountPaid;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private Date datePaid = this.getCreatedAt();
}
