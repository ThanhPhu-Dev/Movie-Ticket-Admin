package com.dinhthanhphu.movieticketadmin.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Table(name = "Bookings")
@Entity
@Data
public class BookingEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column
    private Date time;

    @Column
    private Long total;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "showtime_id")
    private UserEntity showtime;

    @OneToMany(mappedBy = "booking")
    private List<TicketEntity> ticket;

}