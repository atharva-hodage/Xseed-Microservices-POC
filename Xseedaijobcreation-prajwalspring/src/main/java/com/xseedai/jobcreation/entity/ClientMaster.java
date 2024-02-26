package com.xseedai.jobcreation.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ClientMaster")
public class ClientMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long clientId;
    
	private String clientName;
	
	@ManyToOne()
	@JoinColumn(name = "vmsMasterId", nullable = false)
	@JsonIgnore
	private VmsMaster vmsMaster;
	
	@OneToMany(mappedBy = "clientMaster", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<JobCreation> jobCreation = new ArrayList<>();
	
	@Column(name = "createdBy", length = 36, nullable = false)
	private String createdBy;

	@Column(name = "createdOn", nullable = false)
	private LocalDateTime createdOn;

	@Column(name = "modifiedBy", length = 36)
	private String modifiedBy;

	@Column(name = "modifiedOn")
	private LocalDateTime modifiedOn;
}
