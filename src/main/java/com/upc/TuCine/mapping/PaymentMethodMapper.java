package com.upc.TuCine.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.upc.TuCine.dto.PaymentMethodDto;
import com.upc.TuCine.model.PaymentMethod;
import com.upc.TuCine.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;

public class PaymentMethodMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public PaymentMethodDto toResource(PaymentMethod model){
        return mapper.map(model, PaymentMethodDto.class);
    }

    public PaymentMethod toModel(PaymentMethodDto resource){
        return mapper.map(resource, PaymentMethod.class);
    }

    public List<PaymentMethodDto> modelListToResource(List<PaymentMethod> modelList){return mapper.mapList(modelList, PaymentMethodDto.class); }

    public Page<PaymentMethodDto> modelListToPage(List<PaymentMethod> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, PaymentMethodDto.class), pageable, modelList.size());
    }
}
