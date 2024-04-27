package com.turkcell.turkcellcrm.customerService.dataAccess;

import com.turkcell.turkcellcrm.customerService.entity.Customer;
import com.turkcell.turkcellcrm.customerService.entity.IndividualCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IndividualCustomerRepository extends JpaRepository<IndividualCustomer,Integer> {
    Optional<IndividualCustomer> findIndividualCustomerById(int id);
    Optional<IndividualCustomer> findIndividualCustomerByNationalityNumberEquals(String nationalityNumber);

}
