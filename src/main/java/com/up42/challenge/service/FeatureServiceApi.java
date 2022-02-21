package com.up42.challenge.service;

import com.up42.challenge.dto.FeatureDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FeatureServiceApi {

    void saveAll(List<FeatureDTO> featureDTOS);

    Page<FeatureDTO> getAllFeatures(Pageable pageable);

    byte[] getFeatureQuickBookById(String featureId);
}
