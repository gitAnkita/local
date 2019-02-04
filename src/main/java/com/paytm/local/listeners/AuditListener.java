package com.paytm.local.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.paytm.local.datasource.model.Audit;
import com.paytm.local.datasource.model.User;
import com.paytm.local.datasource.repository.AuditRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.persistence.PostPersist;
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
    public void setCreatedOn(User user) throws JsonProcessingException {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronizationAdapter() {
            @Override
            public void beforeCommit(boolean readOnly) {
                Audit auditLog = new Audit();
                auditLog.setAffectedEntityType("User");
                auditLog.setAffectedEntityId("1");
                auditLog.setActorId(user.getId());
                auditLog.setCreatedOn(new Date());
                try {
                    auditLog.setUpdatedEntity(mapper.writeValueAsString(user));
                }
                catch (Exception e){

                }

                log.info("{}", auditLog);

                auditRepository.save(auditLog);
                throw new NullPointerException();
            }
        });
    }

}