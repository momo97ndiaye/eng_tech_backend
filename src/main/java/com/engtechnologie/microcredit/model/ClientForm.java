package com.engtechnologie.microcredit.model;

import com.engtechnologie.microcredit.enumeration.Gender;
import com.engtechnologie.microcredit.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String demandId;
    private String clientId;
    private String name;
    private String surname;
    private Gender gender;
    private String email;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthday;
    private String phoneNumber;
    private String job;
    private String jobDuration;
    private String activitySector;
    private String activityLocation;
    private Long demandedSum;
    private Long allocatedSum;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime demandDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime acceptationDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime sessionStart;
    private Status status;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime sessionEnd;
    private Long interestRate;
}
