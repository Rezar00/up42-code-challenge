package com.up42.challenge;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.up42.challenge.dto.FeatureDTO;
import com.up42.challenge.service.FeatureServiceApi;
import com.up42.challenge.model.FeatureCollection;
import com.up42.challenge.model.FeatureModel;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


@Component
@ConditionalOnExpression(value = "${app.data-initialization}")
public class DataInitialization implements ApplicationListener<ContextRefreshedEvent> {

    private final FeatureServiceApi featureServiceApi;

    public DataInitialization(FeatureServiceApi featureServiceApi) {

        this.featureServiceApi = featureServiceApi;
    }

    @SneakyThrows
    @Override
    @Transactional
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        List<FeatureModel> featureModelsFromJson = this.getFeaturesFromJson();
        List<FeatureDTO> featureDTOS = this.buildFeatureDTOs(featureModelsFromJson);
        this.saveFeatures(featureDTOS);
    }

    private List<FeatureModel> getFeaturesFromJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Resource resource = new ClassPathResource("source-data.json");
        InputStream is = resource.getInputStream();
        List<FeatureModel> features = new ArrayList<>();
        try (JsonParser jsonParser = mapper.getFactory().createParser(is)) {

            // Check the first token
            if (jsonParser.nextToken() != JsonToken.START_ARRAY) {
                throw new IllegalStateException("Expected content to be an array");
            }
            // Iterate over the tokens until the end of the array
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                // Read a contact instance using ObjectMapper and do something with it
                FeatureCollection featureCollection = mapper.readValue(jsonParser, FeatureCollection.class);
                features.add(featureCollection.getFeatures().get(0));
            }
        }
        return features;
    }

    private List<FeatureDTO> buildFeatureDTOs(List<FeatureModel> featureModel) {
        List<FeatureDTO> featureDTOS = new ArrayList<>();
        featureModel.forEach(feature -> featureDTOS.add(
                FeatureDTO.builder()
                        .uuid(feature.getProperties().getId())
                        .timestamp(feature.getProperties().getTimestamp())
                        .beginViewingDate(feature.getProperties().getAcquisition().getBeginViewingDate())
                        .endViewingDate(feature.getProperties().getAcquisition().getEndViewingDate())
                        .missionName(feature.getProperties().getAcquisition().getMissionName())
                        .quickLook(feature.getProperties().getQuicklook())
                        .build()));

        return featureDTOS;
    }

    private void saveFeatures(List<FeatureDTO> featureDTOS) {
        this.featureServiceApi.saveAll(featureDTOS);
    }
}
