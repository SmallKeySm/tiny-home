package com.wzj.entity;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 6498765824033300264L;

    private Date createdDate = new Date();

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
