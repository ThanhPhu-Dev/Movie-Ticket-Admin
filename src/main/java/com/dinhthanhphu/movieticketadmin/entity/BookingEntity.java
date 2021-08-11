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
    private Long total;

    @OneToOne
    @JoinColumn(name = "showtime_id")
    private ShowtimeEntity showtime;

    @OneToMany(mappedBy = "booking", fetch = FetchType.LAZY)
    private List<TicketEntity> ticket;

}