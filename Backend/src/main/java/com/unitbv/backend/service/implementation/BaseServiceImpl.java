package com.unitbv.backend.service.implementation;

import com.unitbv.backend.repository.BaseRepository;
import com.unitbv.backend.service.interfaces.BaseService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class BaseServiceImpl<T, E> implements BaseService<T> {

    @Autowired
    private BaseRepository<E, Integer> repository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Iterable<T> getAll() {
        List<E> entityList = repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return modelMapper.map(entityList, new TypeToken<List<T>>() {
        }.getType());
    }

    @Override
    public Optional<T> getById(int id) {
        Optional<E> entity = repository.findById(id);
        return modelMapper.map(entity, new TypeToken<Optional<T>>() {
        }.getType());
    }

    @Override
    public T save(T dtoObject) {
        E entity = repository.save(modelMapper.map(dtoObject, new TypeToken<E>() {
        }.getType()));
        return modelMapper.map(entity, new TypeToken<T>() {
        }.getType());
    }

    @Override
    public void delete(T dtoObject) {
        repository.delete(modelMapper.map(dtoObject, new TypeToken<E>() {
        }.getType()));
    }

    @Override
    public Iterable<T> filter(Predicate<? super T> condition, Comparator<? super T> comparator) {
        List<T> dtoList = modelMapper.map(repository.findAll(), new TypeToken<List<T>>() {
        }.getType());
        return dtoList.stream().filter(condition).sorted(comparator).collect(Collectors.toList());
    }
}
