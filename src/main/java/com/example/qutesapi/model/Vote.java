package com.example.qutesapi.model;

import com.example.qutesapi.model.enums.VoteType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Votes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quoteId")
    @JsonIgnore
    private Quote quote;

    @ManyToOne
    @JoinColumn(name = "voterId")
    private User voter;

    @Enumerated(EnumType.STRING)
    private VoteType voteType;

    @OneToMany(mappedBy = "vote")
    @JsonIgnore
    private List<VoteHistory> histories;
}
