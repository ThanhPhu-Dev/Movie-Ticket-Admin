package com.dinhthanhphu.movieticketadmin.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Table(name = "Tickets")
@Entity
@Data
public class TicketEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "seatId")
    private String seatId;

    @Column(name = "price")
    private Long price;

    @ManyToOne()
    @JoinColumn(name = "booking_id")
    private BookingEntity booking;

}