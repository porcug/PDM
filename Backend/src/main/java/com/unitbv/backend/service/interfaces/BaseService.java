package com.unitbv.backend.service.interfaces;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public interface BaseService<T> {

    Iterable<T> getAll();

    Optional<T> getById(int id);

    T save(T dtoObject);

    void delete(T dtoObject);

    Iterable<T> filter(Predicate<? super T> condition, Comparator<? super T> comparator);
}
