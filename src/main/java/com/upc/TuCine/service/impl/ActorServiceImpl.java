package com.upc.TuCine.service.impl;

import com.upc.TuCine.dto.ActorDto;
import com.upc.TuCine.model.Actor;
import com.upc.TuCine.repository.ActorRepository;
import com.upc.TuCine.service.ActorService;
import com.upc.TuCine.shared.exception.ResourceValidationException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ActorServiceImpl(){
        this.modelMapper = new ModelMapper();
    }

    private ActorDto EntityToDto(Actor actor){
        return modelMapper.map(actor, ActorDto.class);
    }

    private Actor DtoToEntity(ActorDto actorDto){
        return modelMapper.map(actorDto, Actor.class);
    }

    @Override
    public ActorDto createActor(ActorDto actorDto) {
        validateActor(actorDto);
        existActorByFirstName(actorDto.getFirstName(),actorDto.getLastName());
        Actor actor = DtoToEntity(actorDto);
        return EntityToDto(actorRepository.save(actor));
    }

    @Override
    public List<ActorDto> getAllActors() {
        List<Actor> actors = actorRepository.findAll();
        List<ActorDto> actorDtos = new ArrayList<>();
        for (Actor actor : actors) {
            actorDtos.add(EntityToDto(actor));
        }
        return actorDtos;
    }

    private void validateActor(ActorDto actor) {
        if (actor.getFirstName() == null || actor.getFirstName().isEmpty()) {
            throw new ResourceValidationException("El nombre es obligatorio");
        }
        if (actor.getLastName() == null || actor.getLastName().isEmpty()) {
            throw new ResourceValidationException("El apellido es obligatorio");
        }
        if (actor.getBirthday() == null) {
            throw new ResourceValidationException("La fecha de nacimiento es obligatoria");
        }
    }

    private void existActorByFirstName(String firstName,String lastName){
        if (actorRepository.existsByFirstNameAndLastName(firstName, lastName)) {
            throw new ResourceValidationException("Ya existe un actor con el nombre " + firstName + " " + lastName);
        }
    }


}
