package com.up42.challenge.controller;

import com.up42.challenge.dto.FeatureDTO;
import com.up42.challenge.service.FeatureServiceApi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/v1/features")
public class FeatureController {

    private final FeatureServiceApi featureServiceApi;

    public FeatureController(FeatureServiceApi featureServiceApi) {
        this.featureServiceApi = featureServiceApi;
    }

    @GetMapping
    public ResponseEntity<Page<FeatureDTO>> getFeatures(Pageable pageable) {
        return ResponseEntity.ok(this.featureServiceApi.getAllFeatures(pageable));
    }

    @GetMapping(value = "{featureId}/quicklook")
    public void getQuicklookByFeatureId(@PathVariable String featureId, HttpServletResponse response) throws IOException {
        byte[] image = this.featureServiceApi.getFeatureQuickBookById(featureId);
        response.setContentType("image/png");
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(image);
        outputStream.close();
    }
}
