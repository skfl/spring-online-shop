package ru.skfl.skflshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skfl.skflshop.entities.Weapon;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon, Long> {
}
