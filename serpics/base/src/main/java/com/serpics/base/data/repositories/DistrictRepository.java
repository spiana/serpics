package com.serpics.base.data.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.base.data.model.District;
import com.serpics.core.data.Repository;

public interface DistrictRepository extends Repository<District, Long>{

	@Query("select d from District d where d.isoCode = :isoCode")
	public District getDistrictByIsoCode(@Param("isoCode") String isoCode);
}
