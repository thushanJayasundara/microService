package org.airretailer.entity;

import lombok.*;
import org.airretailer.constant.enums.CabinType;
import org.airretailer.constant.enums.CommonStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Flight {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE, generator = "flight_seq")
    @GenericGenerator(
        name = "flight_seq",
        strategy = "org.airretailer.util.StringPrefixedSequenceIdGenerator")
    private String id;

    @Column(name = "flight_number")
    private String flightNumber;

    @Column(name = "airline")
    private String airline;

    @Column(name = "seats")
    private Long seats;

    @Enumerated(EnumType.STRING)
    private CabinType cabinType;

    @Enumerated
    @Column(name = "status",nullable = false)
    private CommonStatus commonStatus;

}
