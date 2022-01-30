package com.unitbv.backend.repository;

import com.unitbv.backend.model.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDO, Integer> {

    @Query("Select u from _user u order by u.id ASC ")
    List<UserDO> findAllOrderByIdASC();

    @Query("Select u from _user u where u.email = ?1")
    Optional<UserDO> findByEmail(String email);
}
