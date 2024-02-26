package com.xseedai.jobcreation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xseedai.jobcreation.entity.ClientMaster;



public interface ClientMasterRepository extends JpaRepository<ClientMaster, Long>{
    List<ClientMaster> findByVmsMaster_VmsId(Long vmsId);

}
