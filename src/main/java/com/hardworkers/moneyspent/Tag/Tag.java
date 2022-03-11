package com.hardworkers.moneyspent.Tag;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Tag {

    @Id
    private Long id;
}
