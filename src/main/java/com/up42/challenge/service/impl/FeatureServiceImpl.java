package com.up42.challenge.service.impl;

import com.up42.challenge.dto.FeatureDTO;
import com.up42.challenge.entity.Feature;
import com.up42.challenge.exception.ResourceNotFoundException;
import com.up42.challenge.mapper.FeatureMapper;
import com.up42.challenge.repository.FeatureRepository;
import com.up42.challenge.service.FeatureServiceApi;
import com.up42.challenge.util.Base64Util;
import com.up42.challenge.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeatureServiceImpl implements FeatureServiceApi {

    private final FeatureRepository repository;

    private final FeatureMapper mapper;

    @Autowired
    public FeatureServiceImpl(FeatureMapper mapper, FeatureRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    @Transactional
    public void saveAll(List<FeatureDTO> featureDTOS) {
        List<Feature> features = this.mapper.modelsToEntities(featureDTOS);
        this.repository.saveAll(features);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FeatureDTO> getAllFeatures(Pageable pageable) {
        Page<Feature> features = this.repository.findAll(pageable);
        return features.map(mapper::entityToModel);
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] getFeatureQuickBookById(String featureId) {
        Feature feature = this.repository.findByUuid(featureId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(MessageUtil.getMessageParameter("FEATURE.NOT.FOUND",
                                new Object[]{featureId})));

        return Base64Util.convertToImage(feature.getQuickLook());
    }

}
