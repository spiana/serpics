package com.serpics.scheduler.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.core.data.Repository;
import com.serpics.scheduler.model.JobLog;
import com.serpics.scheduler.model.SerpicsJobDetails;

public interface JobLogRepository extends Repository<JobLog, Long> {
	
	@Query("select j from JobLog j where j.jobRunned= :jobDetail")
	public List<JobLog> findLogFroJobDetails(@Param("jobDetail")SerpicsJobDetails jobdetail);
	
	
}
