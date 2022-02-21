package com.up42.challenge.mapper;

import com.up42.challenge.dto.FeatureDTO;
import com.up42.challenge.entity.Feature;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeatureMapper extends GenericMapper<Feature, FeatureDTO> {
}
