package com.example.qutesapi.model;

import com.example.qutesapi.listener.QuoteActionListener;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Quotes")
@EntityListeners(QuoteActionListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID", updatable = false)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Column(name = "date_updated")
    private LocalDate dateUpdated;

    @ManyToOne
    @JoinColumn(name = "authorId")
    private User author;

    @OneToMany(mappedBy = "quote")
    private List<Vote> votes;
}
