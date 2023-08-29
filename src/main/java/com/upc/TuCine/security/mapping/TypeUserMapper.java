package com.upc.TuCine.security.mapping;

import java.io.Serializable;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;

import com.upc.TuCine.security.domain.model.entity.TypeUser;
import com.upc.TuCine.security.domain.model.enumeration.TypeUsers;
import com.upc.TuCine.security.resource.TypeUserDto;
import com.upc.TuCine.shared.mapping.EnhancedModelMapper;

import java.util.List;

public class TypeUserMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    Converter<TypeUsers, String> typeUserToString = new AbstractConverter<>() {
        @Override
        protected String convert(TypeUsers typeUser) {
            return typeUser == null ? null : typeUser.name();
        }
    };

    // Object Mapping
    public TypeUserDto toResource(TypeUser model) {
        mapper.addConverter(typeUserToString);
        return mapper.map(model, TypeUserDto.class);
    }

    public List<TypeUserDto> toResourceList(List<TypeUser> modelList) {
        mapper.addConverter(typeUserToString);
        return mapper.mapList(modelList, TypeUserDto.class);
    }

    public TypeUser toModel(TypeUserDto resource){
        return mapper.map(resource,TypeUser.class);
    }

    public Page<TypeUserDto> modelListToPage(List<TypeUser> modelList, Pageable pageable) {

        mapper.addConverter(typeUserToString);
        return new PageImpl<>(mapper.mapList(modelList, TypeUserDto.class), pageable, modelList.size());
    }
}
