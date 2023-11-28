package com.socialnetwork.lab78.repository;


import com.socialnetwork.lab78.domain.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryRepository<ID, E extends Entity<ID>> implements Repository<ID, E> {
    protected Map<ID,E> entities;

    public InMemoryRepository() {
        //this.validator = validator;
        entities=new HashMap<ID,E>();
    }
    /*
    public InMemoryRepository(Validator validator) {
        this.validator = validator;
        entities=new HashMap<ID,E>();
    }
*/
    @Override
    public Optional<E> findOne(ID id){
        if (id==null)
            throw new IllegalArgumentException("id must be not null");
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public Iterable<E> findAll() {
        return entities.values();
    }

    @Override
    public Optional<E> save(E entity) {
        if (entity==null)
            throw new IllegalArgumentException("entity must be not null");
        //validator.validate(entity);
        if(entities.get(entity.getId()) != null) {
            return Optional.ofNullable(entity);
        }
        else {
            Optional.ofNullable(entities.put(entity.getId(),entity));
            entities.put(entity.getId(),entity);
        }
        return Optional.empty();
    }

    @Override
    public Optional<E> delete(ID id) {
        return  Optional.ofNullable(entities.remove(id));
    }

    @Override
    public Optional<E> update(E entity) {

        if(entity == null)
            throw new IllegalArgumentException("entity must be not null!");
        //validator.validate(entity);

        entities.put(entity.getId(),entity);

        if(entities.get(entity.getId()) != null) {
            entities.put(entity.getId(),entity);
            return Optional.empty();
        }
        return Optional.ofNullable(entity);

    }
}
