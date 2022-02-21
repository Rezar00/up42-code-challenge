package com.up42.challenge.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Lob;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeatureDTO {

    private Long id;
    private String uuid;
    private Long timestamp;
    private Long beginViewingDate;
    private Long endViewingDate;
    private String missionName;
    @Lob
    @JsonIgnore
    private String quickLook;


}
