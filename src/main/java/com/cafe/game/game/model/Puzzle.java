package com.cafe.game.game.model;

import com.cafe.game.game.enums.PuzzleType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "puzzle")
public class Puzzle extends Challenge{
    @Enumerated
    private PuzzleType type;

    @OneToMany(mappedBy = "challenge")
    private List<Tip> tips;

    private String answer;
}
