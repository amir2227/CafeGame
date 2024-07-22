package com.cafe.game.game.model;

import com.cafe.game.game.enums.PuzzleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "puzzle")
public class Puzzle{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    @Column(length = 600)
    private String info;

    @Enumerated(EnumType.STRING)
    private PuzzleType type;

    @OneToMany(mappedBy = "puzzle", cascade = CascadeType.ALL)
    private List<Tip> tips;

    private String answer;
}
