package io.project.virustracker.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@Data
@Entity
public class PHCovidCase extends BaseEntity<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String day;
    private long confirmed;
    private long puis;
    private long pums;
    private long recovered;
    private long deaths;
    private long tests;

}


