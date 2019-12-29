package com.zelazobeton.bookstore.services;

import com.zelazobeton.bookstore.model.BaseEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public abstract class AbstractServiceJPA<T extends BaseEntity, ID extends Long> {
    protected CrudRepository<T, ID> repository;

    Set<T> findAll(){
        Set<T> set = new HashSet<>();
        repository.findAll().iterator().forEachRemaining(set::add);
        return set;
    }

    T findById(ID id){
        Optional<T> object = repository.findById(id);
        if(object.isPresent()){
            return object.get();
        }
        return null;
    }

    T save(T object){
        if(object == null){
            throw new RuntimeException("Saving object: object is null");
        }
        return repository.save(object);
    }

    void deleteById(ID id){
        repository.deleteById(id);
    }

    void delete(T object){
        repository.delete(object);
    }
}
