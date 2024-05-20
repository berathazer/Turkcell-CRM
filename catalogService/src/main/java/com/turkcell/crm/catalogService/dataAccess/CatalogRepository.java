package com.turkcell.crm.catalogService.dataAccess;

import com.turkcell.crm.catalogService.entity.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog,Integer> {

}
