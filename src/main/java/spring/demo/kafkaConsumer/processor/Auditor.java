package spring.demo.kafkaConsumer.processor;

import net.mastery.load.OrderCreated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.demo.entity.Audit;
import spring.demo.entity.EventType;
import spring.demo.repo.AuditRepo;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
public class Auditor {

    private final AuditRepo auditRepo;

    @Autowired
    public Auditor(AuditRepo auditRepo) {
        this.auditRepo = auditRepo;
        System.out.println("AuditRepo injected: " + (auditRepo != null));
    }

    public void convertAndSaveLoadOrdersAuditEntry(OrderCreated orderCreatedMessage){
        Audit audit = new Audit();

        audit.setId(UUID.randomUUID());
        audit.setLoadId(orderCreatedMessage.getLoadId());
        audit.setOrderId(orderCreatedMessage.getOrderId());
        audit.setCustomerId(orderCreatedMessage.getCustomerId());

        audit.setEventType(EventType.OrderCreated);
        audit.setOldValue(null);
        audit.setNewValue(orderCreatedMessage.getCode().toString());

        audit.setReasonCode(null);
        audit.setCreatedAt(LocalDateTime.ofInstant(orderCreatedMessage.getCreatedAt(), ZoneId.systemDefault()));
        audit.setUpdatedAt(null);
        audit.setTriggeredOn(LocalDateTime.ofInstant(orderCreatedMessage.getCreatedAt(), ZoneId.systemDefault()));
        audit.setTriggeredByUserId(orderCreatedMessage.getCreatedByUserId());

        try{
            System.out.println("before saving to db"+audit.toString());
            auditRepo.save(audit);
        }catch(Exception e){
            System.out.println("exception   occurred"+e.toString());
        }

        System.out.println("after saving to db");
    }
}
