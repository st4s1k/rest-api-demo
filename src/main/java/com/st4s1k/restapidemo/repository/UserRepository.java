package com.st4s1k.restapidemo.repository;

import com.st4s1k.restapidemo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
    List<User> findByName(String name);
    List<User> findAll();
}
