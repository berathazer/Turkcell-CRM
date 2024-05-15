package com.turkcell.crm.catalogService.api.controller;

import com.turkcell.crm.catalogService.business.abstracts.CatalogPropertyService;
import com.turkcell.crm.catalogService.business.dtos.request.catalog.CreateCatalogRequest;
import com.turkcell.crm.catalogService.business.dtos.request.catalog.UpdateCatalogRequest;
import com.turkcell.crm.catalogService.business.dtos.request.catalogProperties.CreateCatalogPropertyRequest;
import com.turkcell.crm.catalogService.business.dtos.request.catalogProperties.UpdateCatalogPropertyRequest;
import com.turkcell.crm.catalogService.business.dtos.response.catalog.CreatedCatalogResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalog.GetAllCatalogResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalog.GetByIdCatalogResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalog.UpdatedCatalogResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalogProperties.CreatedCatalogPropertyResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalogProperties.GetAllCatalogPropertyResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalogProperties.GetByIdCatalogPropertyResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalogProperties.UpdatedCatalogPropertyResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalogservice/api/v1/catalogs/catalogproperty")
@AllArgsConstructor
public class CatalogPropertiesController {
    private CatalogPropertyService catalogPropertyService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCatalogPropertyResponse add(@Valid @RequestBody CreateCatalogPropertyRequest createCatalogPropertyRequest){

        return this.catalogPropertyService.add(createCatalogPropertyRequest);
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<GetAllCatalogPropertyResponse> getAll(){
        return this.catalogPropertyService.getAll();
    }

    @GetMapping("/getById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GetByIdCatalogPropertyResponse getById(@PathVariable int id){
        return this.catalogPropertyService.getById(id);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCatalogPropertyResponse update(@Valid @RequestBody UpdateCatalogPropertyRequest updateCatalogPropertyRequest){

        return this.catalogPropertyService.update(updateCatalogPropertyRequest);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        this.catalogPropertyService.delete(id);
    }

}
