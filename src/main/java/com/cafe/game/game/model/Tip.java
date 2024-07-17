package com.cafe.game.game.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tip")
public class Tip {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(length = 300)
    private String text;

    @Column(length = 60)
    private String img;

    @Column
    private Boolean state; // Lock or unlock

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "puzzleid")
    private Puzzle puzzle;

}
