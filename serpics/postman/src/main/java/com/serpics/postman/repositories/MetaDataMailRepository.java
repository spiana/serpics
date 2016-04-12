package com.serpics.postman.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serpics.core.data.Repository;
import com.serpics.postman.model.MailState;
import com.serpics.postman.model.MetaDataMail;

public interface MetaDataMailRepository extends Repository<MetaDataMail, Long> {

	@Query("select m from MetaDataMail m where m.state in :states")
	public Page<MetaDataMail> findMailToSend(@Param("states")List<MailState> mailState,Pageable pageable);
}
