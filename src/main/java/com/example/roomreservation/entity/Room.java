package com.example.roomreservation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "rooms")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "building_id")
    private long buildingId;

    @Column(name = "max_allocation")
    private int maxAllocation;

    @Formula("5 + max_allocation")
    private int cleanUpTime;

    @Column
    private int floor;

    @Column
    private boolean multimedia;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Set<Reservation> reservations;
}
