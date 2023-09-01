package com.upc.TuCine.service;

import com.upc.TuCine.dto.ActorDto;

import java.util.List;

public interface ActorService {
    ActorDto createActor(ActorDto actorDto);

    List<ActorDto> getAllActors();

}
