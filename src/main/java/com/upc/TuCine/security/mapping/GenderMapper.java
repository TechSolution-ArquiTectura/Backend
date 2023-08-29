package com.upc.TuCine.security.mapping;

import java.io.Serializable;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;

import com.upc.TuCine.security.domain.model.entity.Gender;
import com.upc.TuCine.security.domain.model.enumeration.Genders;
import com.upc.TuCine.security.resource.GenderDto;
import com.upc.TuCine.shared.mapping.EnhancedModelMapper;

import java.util.List;

public class GenderMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    Converter<Genders, String> genderToString = new AbstractConverter<>() {
        @Override
        protected String convert(Genders gender) {
            return gender == null ? null : gender.name();
        }
    };

    // Object Mapping
    public GenderDto toResource(Gender model) {
        mapper.addConverter(genderToString);
        return mapper.map(model, GenderDto.class);
    }

    public List<GenderDto> toResourceList(List<Gender> modelList) {
        mapper.addConverter(genderToString);
        return mapper.mapList(modelList, GenderDto.class);
    }

    public Gender toModel(GenderDto resource){
        return mapper.map(resource,Gender.class);
    }

    public Page<GenderDto> modelListToPage(List<Gender> modelList, Pageable pageable) {
        mapper.addConverter(genderToString);
        return new PageImpl<>(mapper.mapList(modelList, GenderDto.class), pageable, modelList.size());
    }
}
