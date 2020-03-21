package io.project.virustracker.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class PHCasesByFacility {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long fid;
    private int age;
    private String gender;
    private String nationality;
    private String residence;
    private String travelHistory;
    private String symptoms;
    private String confirmed;
    private String facility;
    private String status;
    private String date;

}

