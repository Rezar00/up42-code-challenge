package com.up42.challenge.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertiesModel {
    private String id;
    private Long timestamp;
    private AcquisitionModel acquisition;
    private String quicklook;
}
