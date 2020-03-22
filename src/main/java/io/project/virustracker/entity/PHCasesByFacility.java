package io.project.virustracker.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class PHCasesByFacility extends BaseEntity<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

