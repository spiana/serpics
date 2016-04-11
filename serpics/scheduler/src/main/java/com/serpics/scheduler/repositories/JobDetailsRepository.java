package com.serpics.scheduler.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.core.data.Repository;
import com.serpics.scheduler.model.JobDetails;

public interface JobDetailsRepository extends Repository<JobDetails, Long> {

	
	@Query("select job from JobDetails job where job.uuid= :uuid ")
	public List<JobDetails> findJobByUUID(@Param("uuid")String uuid);
}
