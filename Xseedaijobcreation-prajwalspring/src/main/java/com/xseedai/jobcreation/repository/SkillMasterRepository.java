package com.xseedai.jobcreation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xseedai.jobcreation.entity.SkillsMaster;

public interface SkillMasterRepository extends JpaRepository<SkillsMaster, Long>{

	SkillsMaster findBySkills(String skillName);
	
	List<SkillsMaster> findBySkillsContainingIgnoreCase(String keyword);
	
}
