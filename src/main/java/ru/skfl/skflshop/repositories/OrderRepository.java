package ru.skfl.skflshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skfl.skflshop.entities.UserOrder;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Long> {
}
