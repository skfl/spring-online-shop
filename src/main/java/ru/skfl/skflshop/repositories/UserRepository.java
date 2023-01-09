package ru.skfl.skflshop.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skfl.skflshop.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    Optional<User> findByToken(String token);

    @Query(value = "SELECT id from usr where token=?",nativeQuery = true)
    Optional<Long> getUserIdByToken(String token);
}

