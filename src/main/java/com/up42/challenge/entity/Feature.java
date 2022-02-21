package com.up42.challenge.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "feature", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"uuid"},
                name = "UK_UUID"
        )})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Feature {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(unique = true)
    private String uuid;

    @Column
    private Long timestamp;

    @Column
    private Long beginViewingDate;

    @Column
    private Long endViewingDate;

    @Column
    private String missionName;

    @Column
    @Lob
    private String quickLook;
}
