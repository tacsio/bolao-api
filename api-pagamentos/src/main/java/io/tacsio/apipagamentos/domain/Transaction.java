package io.tacsio.apipagamentos.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(uniqueConstraints =
@UniqueConstraint(columnNames = {"USER_ID", "RESTAURANT_ID", "PAYMENT_METHOD_ID", "ORDER_ID"})
)
public class Transaction {
    @Id
    private UUID id;

    @PositiveOrZero
    private BigDecimal value;

    @NotNull
    @Column(name = "ORDER_ID")
    private Long orderId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private PaymentMethod paymentMethod;

    private String extraInfo;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    void generateTransactionId() {
        id = UUID.randomUUID();
    }

    @PreUpdate
    void updateTimestamp() {
        updatedAt = LocalDateTime.now();
    }

    @Deprecated
    protected Transaction() {
    }

    public Transaction(@NotNull Long orderId, @PositiveOrZero BigDecimal value, User user, Restaurant restaurant, PaymentMethod paymentMethod, String extraInfo) {
        this.orderId = orderId;
        this.value = value;
        this.user = user;
        this.restaurant = restaurant;
        this.paymentMethod = paymentMethod;
        this.extraInfo = extraInfo;
        this.createdAt = LocalDateTime.now();

        if (!paymentMethod.getType().online) {
            this.status = TransactionStatus.AWAITING_PAYMENT_CONFIRMATION;
        }
    }

    public void conclude() {
        if (Objects.equals(status, TransactionStatus.AWAITING_PAYMENT_CONFIRMATION)) {
            this.status = TransactionStatus.CONCLUDED;
        }
    }

    public UUID getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}

