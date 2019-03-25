package com.paytm.local.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.paytm.local.datasource.model.Audit;
import com.paytm.local.datasource.model.User;
import com.paytm.local.datasource.repository.AuditRepository;
import com.paytm.local.dto.Auditable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.Null;
import java.util.Date;

@Component
@Slf4j
public class AuditListener {

    private static AuditRepository auditRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public void init(AuditRepository repository) {
        auditRepository=repository;
    }

    @PostPersist
    public void prePersist(Auditable auditable) throws JsonProcessingException {
        log.info("prepersist {}",auditable);
                log.info("inside prepersist {}",auditable);
                saveAudit(auditable);

    }

    @PostUpdate
    public void postUpdate(Auditable auditable) throws JsonProcessingException {
        log.info("preupdate {}",auditable);
                log.info("inside preupdate {}",auditable);
                saveAudit(auditable);

    }

    private void saveAudit(Auditable auditable){
        Audit audit = new Audit();

        audit.setAffectedEntityId(auditable.getEntityId());
        audit.setAffectedEntityType(auditable.getClass().getName().substring(20));
        audit.setUpdatedBy("local");

        try {
            audit.setUpdatedEntity(mapper.writeValueAsString(auditable));
        } catch (JsonProcessingException e) {
            log.error("Exception {}",e.getMessage());
        }

        log.info("Auditrepo {}",auditRepository);
        try {
            audit = auditRepository.save(audit);
        }
        catch (Exception e1){
            log.error("exception {}",e1);
        }
    }

}