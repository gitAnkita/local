package com.paytm.local.datasource.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.paytm.local.dto.Auditable;
import com.paytm.local.listeners.AuditListener;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "user")
@DynamicInsert
@DynamicUpdate
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@EntityListeners(AuditListener.class)
public class User implements Serializable, Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    private String status;

    @Column(name = "default_vpa")
    private String defaultVpa;

    @Column(name = "created_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column(name = "updated_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    @Override
    public String getEntityId() {
        return this.id.toString();
    }
}
