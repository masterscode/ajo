package com.ajo.models;

import com.ajo.models.enums.RequestStatus;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class Request extends BaseEntity{
    private Date dateOfRequest = this.getCreatedAt(); //LocalDate.now();//can't this be replaced with createdAt props????

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    private String message;
}
