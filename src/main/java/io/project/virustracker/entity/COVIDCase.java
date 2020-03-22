package io.project.virustracker.entity;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class COVIDCase extends BaseEntity<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long ID;

    @Column(name = "province")
    private String province;

    @Column(name = "country")
    private String country;

    @Column(name = "last_update")
    private String lastUpdate;

    @Column(name = "confirmed")
    private long confirmed;

    @Column(name = "deaths")
    private long deaths;

    @Column(name = "recovered")
    private long recovered;

    @Column(name = "published_date")
    private String publishedDate;

}
