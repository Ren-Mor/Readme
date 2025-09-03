package Capstone.capstone_project.repositories;

import Capstone.capstone_project.entities.Address;
import Capstone.capstone_project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findByUtente(User utente);
}
