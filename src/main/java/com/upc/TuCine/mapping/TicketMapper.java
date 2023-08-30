package com.upc.TuCine.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.upc.TuCine.dto.TicketDto;
import com.upc.TuCine.model.Ticket;
import com.upc.TuCine.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;

public class TicketMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    //Object Mapping
    public TicketDto toResource(Ticket model){
        return mapper.map(model,TicketDto.class);
    }

    public Ticket toModel(TicketDto resource){
        return mapper.map(resource,Ticket.class);
    }

    public List<TicketDto> modelListToResource(List<Ticket> modelList){return mapper.mapList(modelList, TicketDto.class); }

    public Page<TicketDto> modelListToPage(List<Ticket> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, TicketDto.class), pageable, modelList.size());
    }
}
