package com.dinhthanhphu.movieticketadmin.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "Movies")
@Entity
@Setter
@Getter
public class MovieEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String name;

    @Column
    private Integer times;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column
    private Date openDate;

    @Column
    private String posterUrl;

    @Column
    private String posterPublicId;

    @Column
    private String trailerUrl;

    @Column
    private String nation;

    @Column
    private String trailerPublicid;

    @OneToMany(mappedBy = "movie")
    private List<ShowtimeEntity> showtime;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private List<ImageEntity> image;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "Actor_Movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<ActorEntity> actors;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "category_movie",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<CategoryEntity> categories;

    public void addBook(ActorEntity actor) {
        this.actors.add(actor);
        actor.getMovies().add(this);
    }

    public void removeBook(ActorEntity actor) {
        this.actors.remove(actor);
        actor.getMovies().remove(this);
    }
}