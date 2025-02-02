package spring.demo.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.IdGeneratorType;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "load_audit")
public class Audit {

    @Id
    @UuidGenerator
    @Column(name = "id", columnDefinition = "CHAR(36)")
//    @org.hibernate.annotations.Type("uuid-char")
    private UUID id;

    @Column(name = "load_id", nullable = false, columnDefinition = "CHAR(36)")
    private UUID loadId;

    @Column(name = "route_id", columnDefinition = "CHAR(36)")
    private UUID routeId;

    @Column(name = "order_id", columnDefinition = "CHAR(36)")
    private UUID orderId;

    @Column(name = "customer_id", columnDefinition = "CHAR(36)")
    private UUID customerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private EventType eventType;

    @Override
    public String toString() {
        return "Audit{" +
                "id=" + id +
                ", loadId=" + loadId +
                ", routeId=" + routeId +
                ", orderId=" + orderId +
                ", customerId=" + customerId +
                ", eventType=" + eventType +
                ", oldValue='" + oldValue + '\'' +
                ", newValue='" + newValue + '\'' +
                ", reasonCode='" + reasonCode + '\'' +
                ", triggeredOn=" + triggeredOn +
                ", triggeredByUserId=" + triggeredByUserId +
                ", eventData='" + eventData + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Column(name = "old_value")
    private String oldValue;

    @Column(name = "new_value")
    private String newValue;

    @Column(name = "reason_code")
    private String reasonCode;

    @Column(name = "triggered_on", nullable = false)
    private LocalDateTime triggeredOn;

    @Column(name = "triggered_by_user_id", nullable = false, columnDefinition = "CHAR(36)")
    private UUID triggeredByUserId;

    @Column(name = "event_data")
    private String eventData;

    @Column(name = "created_at", nullable = true)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UUID getLoadId() {
        return loadId;
    }

    public void setLoadId(UUID loadId) {
        this.loadId = loadId;
    }

    public UUID getRouteId() {
        return routeId;
    }

    public void setRouteId(UUID routeId) {
        this.routeId = routeId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }


    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public void setReasonCode(String reasonCode) {
        this.reasonCode = reasonCode;
    }

    public LocalDateTime getTriggeredOn() {
        return triggeredOn;
    }

    public void setTriggeredOn(LocalDateTime triggeredOn) {
        this.triggeredOn = triggeredOn;
    }

    public UUID getTriggeredByUserId() {
        return triggeredByUserId;
    }

    public void setTriggeredByUserId(UUID triggeredByUserId) {
        this.triggeredByUserId = triggeredByUserId;
    }

    public String getEventData() {
        return eventData;
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;
    }
}
