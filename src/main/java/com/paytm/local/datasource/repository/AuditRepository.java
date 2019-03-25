package com.paytm.local.datasource.repository;

import com.paytm.local.datasource.model.Audit;
import com.paytm.local.datasource.model.User;
import org.springframework.data.repository.CrudRepository;

public interface AuditRepository extends CrudRepository<Audit,Long> {

    Audit save(Audit audit);
}
