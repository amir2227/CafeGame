package com.cafe.game.shop.model;

import com.cafe.game.shop.enums.CustomerType;
import com.cafe.game.user.model.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private CustomerType type = CustomerType.GUEST;
    @Column
    private String agent;

    @OneToOne
    private User user;

    @ManyToOne
    private ShopTable shopTable;

    @CreationTimestamp
    private OffsetDateTime createdAt;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
