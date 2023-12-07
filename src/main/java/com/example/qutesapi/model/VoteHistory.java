package com.example.qutesapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Votes_history")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class VoteHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "timestamp")
    private LocalDate timestamp;

    @ManyToOne
    private Vote vote;
}
