package com.paytm.local.datasource.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "audit")
@Getter
@Setter
@DynamicInsert
public class Audit implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

    @Column(name = "affected_entity_id")
    private String affectedEntityId;

    @Column(name = "affected_entity_type")
    private String affectedEntityType;

    @Column(name = "updated_entity")
    private String updatedEntity;

    @Column(name = "actor_id")
    private Long actorId;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "created_on")
	@Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

}
