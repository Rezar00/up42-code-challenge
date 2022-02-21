package com.up42.challenge.mapper;

import java.util.List;

public interface GenericMapper<ENT, DTO> {

    ENT modelToEntity(DTO model);

    DTO entityToModel(ENT entity);

    List<ENT> modelsToEntities(List<DTO> modelList);

    List<DTO> entitiesToModels(List<ENT> entityList);
}
