package com.biswa.JWTAuthentication.model;

import java.util.Date;



import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "created_at", "created_by", "updated_at", "updated_by" }, allowGetters = true)
public abstract class Auditable {
	
	
	 @CreatedDate
	 @Temporal(TemporalType.TIMESTAMP)
      protected Date created_at;
	 
	
	 
	 @LastModifiedDate
	 @Temporal(TemporalType.TIMESTAMP)
		
      protected Date updated_at;
	 
	
	 
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	

	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	@Override
	public String toString() {
		return "Auditable [created_at=" + created_at + ", updated_at=" + updated_at
				+ "]";
	}
	
	 
	 
	 
      
}
