package ru.skfl.skflshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.skfl.skflshop.entities.User;
import ru.skfl.skflshop.entities.UserOrder;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<UserOrder, Long> {

    List<UserOrder> findByUser(User user);

    @Query(value = "select * from user_order where user_id=?", nativeQuery = true)
    Optional<List<UserOrder>> getUserOrdersByUserId(Long user_id);
}
