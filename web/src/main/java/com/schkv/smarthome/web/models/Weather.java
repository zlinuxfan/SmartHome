package com.schkv.smarthome.web.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "`weather`")
public class Weather {

    @Id
    private Integer id;

}
