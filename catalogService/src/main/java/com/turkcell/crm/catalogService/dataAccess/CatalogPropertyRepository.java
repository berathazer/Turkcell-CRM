package com.turkcell.crm.catalogService.dataAccess;

import com.turkcell.crm.catalogService.entity.CatalogProperty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CatalogPropertyRepository extends JpaRepository<CatalogProperty,Integer> {

    List<CatalogProperty> findByDeletedDateIsNull();

}
