package spring.demo.kafkaConsumer.listener;

import net.mastery.load.OrderCreated;
import net.mastery.load.OrderEvent;
import org.apache.avro.generic.GenericRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import spring.demo.kafkaConsumer.processor.Auditor;
import spring.demo.repo.AuditRepo;

import java.time.Instant;
import java.util.UUID;

@Service
public class LoadOrderConsumer {

    @Autowired
    AuditRepo auditRepo;

//    @KafkaListener(topics = "load.orders", groupId = "load-order-consumer-group")
//    public void consume(@Payload OrderEvent orderEvent) {
//        // Process the OrderEvent
//        System.out.println("Received event: " + orderEvent);
//    }

    @KafkaListener(topics = "load.orders", groupId = "load-order-consumer-group")
    public void consume(@Payload GenericRecord genericRecord) {
        System.out.println("Received raw record: " + genericRecord);

        OrderEvent orderEvent = convertToOrderEvent(genericRecord);
        System.out.println("Converted to OrderEvent: " + orderEvent);

        Auditor auditor = new Auditor(auditRepo);
        auditor.convertAndSaveLoadOrdersAuditEntry(orderEvent.getOrderCreated());
    }

    private OrderEvent convertToOrderEvent(GenericRecord genericRecord) {

        // Extract the nested 'order_created' field as a GenericRecord
        GenericRecord orderCreatedRecord = (GenericRecord) genericRecord.get("order_created");

        // Map fields from the nested GenericRecord to the OrderCreated builder
        OrderCreated orderCreated = OrderCreated.newBuilder()
                .setOrderId(UUID.fromString(orderCreatedRecord.get("order_id").toString()))
                .setLoadId(orderCreatedRecord.get("load_id") != null ? UUID.fromString(orderCreatedRecord.get("load_id").toString()) : null)
                .setLoadCode(orderCreatedRecord.get("load_code") != null ? orderCreatedRecord.get("load_code").toString() : null)
                .setCode(orderCreatedRecord.get("code") != null ? orderCreatedRecord.get("code").toString() : null)
                .setCreatedBy(orderCreatedRecord.get("created_by") != null ? orderCreatedRecord.get("created_by").toString() : null)
                .setCustomerId(orderCreatedRecord.get("customer_id") != null ? UUID.fromString(orderCreatedRecord.get("customer_id").toString()) : null)
                .setCreatedByUserId(orderCreatedRecord.get("created_by_user_id") != null ? UUID.fromString(orderCreatedRecord.get("created_by_user_id").toString()) : null)
                .setUpdatedByUserId(orderCreatedRecord.get("updated_by_user_id") != null ? UUID.fromString(orderCreatedRecord.get("updated_by_user_id").toString()) : null)
                .setCreatedAt(orderCreatedRecord.get("created_at") != null ? Instant.ofEpochMilli((Long) orderCreatedRecord.get("created_at")) : null)
                .setUpdatedAt(orderCreatedRecord.get("updated_at") != null ? Instant.ofEpochMilli((Long) orderCreatedRecord.get("updated_at")) : null)
                .build();


        // Map fields manually or use Avro tools
        return OrderEvent.newBuilder()
                .setId(genericRecord.get("id").toString())
                .setEventType(genericRecord.get("event_type").toString())
                .setOrderCreated(orderCreated)
                .build();
    }
}
