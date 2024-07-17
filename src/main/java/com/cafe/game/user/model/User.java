package com.cafe.game.user.model;

import com.cafe.game.shop.model.Shop;
import com.cafe.game.user.enums.UserState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "_user")
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;
    @JsonIgnore
    @Column(length = 127,nullable = false)
    private String password;

    @Column(length = 15,unique = true, nullable = false)
    private String username;
    @Column(length = 15,unique = true)
    private String phone;

    @Column(length = 120,unique = true)
    private String email;
    @Column
    private String fullName;

    @Column
    private String image;
    @Enumerated(EnumType.ORDINAL)
    private UserState state = UserState.REGISTERED;
    @Column(nullable = false)
    private Boolean isActive = true;

    @CreationTimestamp
    private OffsetDateTime createdAt;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @OneToMany(mappedBy = "owner")
    private List<Shop> shops =  new ArrayList<>();

    @ManyToMany
    private Set<Role> roles = new HashSet<>();
    public User(String password, String phone, String fullName) {
        this.password = password;
        this.phone = phone;
        this.fullName = fullName;
        this.isActive = true;
        this.state = UserState.REGISTERED;
    }

    public User(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles.isEmpty()) return null;
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).toList();
    }
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }
}
