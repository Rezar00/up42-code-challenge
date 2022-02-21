package com.up42.challenge;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.up42.challenge.dto.FeatureDTO;
import com.up42.challenge.entity.Feature;
import com.up42.challenge.exception.BusinessException;
import com.up42.challenge.exception.ResourceNotFoundException;
import com.up42.challenge.mapper.FeatureMapper;
import com.up42.challenge.model.FeatureCollection;
import com.up42.challenge.model.FeatureModel;
import com.up42.challenge.repository.FeatureRepository;
import com.up42.challenge.service.FeatureServiceApi;
import com.up42.challenge.service.impl.FeatureServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class FeatureServiceTest {

    @Mock
    private FeatureRepository repository;

    @Mock
    private FeatureMapper mapper;

    private FeatureServiceApi service;

    private static final List<FeatureDTO> featureDTOS = new ArrayList<>();

    @DisplayName("read json data and create feature dto list")
    @BeforeEach
    void prepareFeaturesFromJson() throws IOException {
        service = new FeatureServiceImpl(mapper, repository);
        List<FeatureModel> featuresFromJson = getFeaturesFromJson();
        buildFeatureDTOs(featuresFromJson);
    }

    @Test
    @DisplayName("get all features from json")
    void getAllFeatures() {
        FeatureDTO feature1 = FeatureDTO.builder()
                .uuid("39c2f29e-c0f8-4a39-a98b-deed547d6aea")
                .timestamp(1554831167697L)
                .beginViewingDate(1554831167697L)
                .endViewingDate(1554831202043L)
                .missionName("Sentinel-1B")
                .build();
        FeatureDTO feature2 = FeatureDTO.builder()
                .uuid("cf5dbe37-ab95-4af1-97ad-2637aec4ddf0")
                .timestamp(1556904743783L)
                .beginViewingDate(1556904743783L)
                .endViewingDate(1556904768781L)
                .missionName("Sentinel-1B")
                .build();

        FeatureDTO feature3 = FeatureDTO.builder()
                .uuid("ca81d759-0b8c-4b3f-a00a-0908a3ddd655")
                .timestamp(1558155123786L)
                .beginViewingDate(1558155123786L)
                .endViewingDate(1558155148785L)
                .missionName("Sentinel-1A")
                .build();
        FeatureDTO feature4 = FeatureDTO.builder()
                .uuid("0b598c52-7bf2-4df0-9d09-94229cdfbc0b")
                .timestamp(1560661222337L)
                .beginViewingDate(1560661222337L)
                .endViewingDate(1560661247336L)
                .missionName("Sentinel-1A")
                .build();
        FeatureDTO feature5 = FeatureDTO.builder()
                .uuid("aeaa71d6-c549-4620-99ce-f8cae750b8d5")
                .timestamp(1560015145495L)
                .beginViewingDate(1560015145495L)
                .endViewingDate(1560015170493L)
                .missionName("Sentinel-1B")
                .build();
        FeatureDTO feature6 = FeatureDTO.builder()
                .uuid("12d0b505-2c70-4420-855c-936d05c55669")
                .timestamp(1555477219508L)
                .beginViewingDate(1555477219508L)
                .endViewingDate(1555477244506L)
                .missionName("Sentinel-1A")
                .build();
        FeatureDTO feature7 = FeatureDTO.builder()
                .uuid("7f23a853-76a8-4787-a2ba-fdfe93e74165")
                .timestamp(1561051946263L)
                .beginViewingDate(1561051946263L)
                .endViewingDate(1561051971261L)
                .missionName("Sentinel-1B")
                .build();
        FeatureDTO feature8 = FeatureDTO.builder()
                .uuid("b3ac34e1-12e6-4dee-9e21-b717f472fcfd")
                .timestamp(1557941519219L)
                .beginViewingDate(1557941519219L)
                .endViewingDate(1557941544218L)
                .missionName("Sentinel-1B")
                .build();
        FeatureDTO feature9 = FeatureDTO.builder()
                .uuid("6df9b09a-3a93-4064-bf9f-47e5809b0ffe")
                .timestamp(1557118373086L)
                .beginViewingDate(1557118373086L)
                .endViewingDate(1557118398085L)
                .missionName("Sentinel-1A")
                .build();
        FeatureDTO feature10 = FeatureDTO.builder()
                .uuid("2ed68fe5-f719-48c3-aa27-b0cc155f06cb")
                .timestamp(1560015170494L)
                .beginViewingDate(1560015170494L)
                .endViewingDate(1560015204843L)
                .missionName("Sentinel-1B")
                .build();
        FeatureDTO feature11 = FeatureDTO.builder()
                .uuid("f556abfe-5558-4d3a-9849-c0de4d2766fd")
                .timestamp(1561051971263L)
                .beginViewingDate(1561051971263L)
                .endViewingDate(1561052005606L)
                .missionName("Sentinel-1B")
                .build();
        FeatureDTO feature12 = FeatureDTO.builder()
                .uuid("63fded50-842f-4384-ac65-e83d584beb4c")
                .timestamp(1556904768782L)
                .beginViewingDate(1556904768782L)
                .endViewingDate(1556904803124L)
                .missionName("Sentinel-1B")
                .build();
        FeatureDTO feature13 = FeatureDTO.builder()
                .uuid("b0d3bf6a-ff54-49e0-a4cb-e57dcb68d3b5")
                .timestamp(1558155148786L)
                .beginViewingDate(1558155148786L)
                .endViewingDate(1558155173785L)
                .missionName("Sentinel-1A")
                .build();
        FeatureDTO feature14 = FeatureDTO.builder()
                .uuid("08a190bf-8c7e-4e94-a22c-7f3be11f642c")
                .timestamp(1555044772083L)
                .beginViewingDate(1555044772083L)
                .endViewingDate(1555044797082L)
                .missionName("Sentinel-1A")
                .build();
        List<FeatureDTO> expectedFeatures =
                Arrays.asList(feature1, feature2, feature3, feature4, feature5, feature6, feature7, feature8,
                        feature9, feature10, feature11, feature12, feature13, feature14
                );

        Set<String> uuidSetOfExpectedFeatures = expectedFeatures.stream()
                .map(FeatureDTO::getUuid).collect(Collectors.toSet());

        AtomicBoolean isNotValid = new AtomicBoolean(false);
        featureDTOS.forEach(featureDTO -> {
            if (!uuidSetOfExpectedFeatures.contains(featureDTO.getUuid())) {
                isNotValid.set(true);
            }
        });

        assertThat(isNotValid).isFalse();
    }

    @Test
    void shouldNotConvertInvalidBase64InFeature() {
        String featureId = "39c2f29e-c0f8-4a39-a98b-deed547d6aea";

        Feature featureEntity = Feature.builder()
                .uuid("39c2f29e-c0f8-4a39-a98b-deed547d6aea")
                .timestamp(1554831167697L)
                .beginViewingDate(1554831167697L)
                .endViewingDate(1554831202043L)
                .missionName("Sentinel-1B")
                .build();

        Mockito.when(repository.findByUuid(featureId)).thenReturn(Optional.of(featureEntity));

        Assertions.assertThrows(BusinessException.class,
                () -> service.getFeatureQuickBookById(featureId));
    }

    @Test
    void shouldFindQuicklookByFeatureId() {
        String featureId = "39c2f29e-c0f8-4a39-a98b-deed547d6aea";

        FeatureDTO featureFromJsonFile = featureDTOS.stream()
                .filter(featureDTO -> featureId.equals(featureDTO.getUuid()))
                .findAny().get();

        Feature featureMockEntity = Feature.builder()
                .uuid(featureFromJsonFile.getUuid())
                .timestamp(featureFromJsonFile.getTimestamp())
                .beginViewingDate(featureFromJsonFile.getBeginViewingDate())
                .endViewingDate(featureFromJsonFile.getEndViewingDate())
                .missionName(featureFromJsonFile.getMissionName())
                .quickLook(featureFromJsonFile.getQuickLook())
                .build();

        Mockito.when(repository.findByUuid(featureId)).thenReturn(Optional.of(featureMockEntity));

        byte[] actualQuickLook = service.getFeatureQuickBookById(featureId);
        assertThat(actualQuickLook).isNotNull();
    }

    @Test
    void shouldNotFindQuicklookByFeatureId() {
        String featureId = "invalid-feature-id";
        Mockito.when(repository.findByUuid(featureId)).thenThrow(ResourceNotFoundException.class);
        Assertions.assertThrows(ResourceNotFoundException.class,
                () -> service.getFeatureQuickBookById(featureId));
    }

    private static List<FeatureModel> getFeaturesFromJson() throws IOException {
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

    private static void buildFeatureDTOs(List<FeatureModel> featureModel) {
        featureModel.forEach(feature -> featureDTOS.add(
                FeatureDTO.builder()
                        .uuid(feature.getProperties().getId())
                        .timestamp(feature.getProperties().getTimestamp())
                        .beginViewingDate(feature.getProperties().getAcquisition().getBeginViewingDate())
                        .endViewingDate(feature.getProperties().getAcquisition().getEndViewingDate())
                        .missionName(feature.getProperties().getAcquisition().getMissionName())
                        .quickLook(feature.getProperties().getQuicklook())
                        .build()));
    }
}