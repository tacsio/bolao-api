package io.tacsio.apipagamentos.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.Optional;

@Entity
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentType type;

    @NotBlank
    private String description;

    @Enumerated(EnumType.STRING)
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)//TODO: move to DTO
    private Brand brand;

    @Deprecated
    protected PaymentMethod() {
    }

    public PaymentMethod(@NotNull PaymentType type, @NotBlank String description, Optional<Brand> brand) {
        this.type = type;
        this.description = description;

        if (type.equals(PaymentType.CARD)) {
            Assert.isTrue(brand.isPresent(), "For Card Type brand is mandatory.");
            this.brand = brand.get();
        }
    }

    public Long getId() {
        return id;
    }

    public PaymentType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public boolean isOnline() {
        return type.online;
    }

    public Optional<Brand> getBrand() {
        return Optional.ofNullable(brand);
    }

    public enum Brand {
        VISA, MASTERCARD, ELO, HYPERCARD
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentMethod that)) return false;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
               "id=" + id +
               ", type=" + type +
               ", description='" + description + '\'' +
               ", brand=" + brand +
               '}';
    }
}


