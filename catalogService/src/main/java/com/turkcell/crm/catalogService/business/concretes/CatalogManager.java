package com.turkcell.crm.catalogService.business.concretes;

import com.turkcell.crm.catalogService.business.abstracts.CatalogService;
import com.turkcell.crm.catalogService.business.dtos.request.catalog.CreateCatalogRequest;
import com.turkcell.crm.catalogService.business.dtos.request.catalog.UpdateCatalogRequest;
import com.turkcell.crm.catalogService.business.dtos.response.catalog.CreatedCatalogResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalog.GetAllCatalogResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalog.GetByIdCatalogResponse;
import com.turkcell.crm.catalogService.business.dtos.response.catalog.UpdatedCatalogResponse;
import com.turkcell.crm.catalogService.core.utilities.mapping.ModelMapperService;
import com.turkcell.crm.catalogService.dataAccess.CatalogRepository;
import com.turkcell.crm.catalogService.entity.Catalog;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CatalogManager implements CatalogService {

    private CatalogRepository catalogRepository;
    private ModelMapperService modelMapperService;

    @Override
    public CreatedCatalogResponse add(CreateCatalogRequest createCatalogRequest) {

        Catalog catalog = this.modelMapperService.forRequest().map(createCatalogRequest, Catalog.class);
        CreatedCatalogResponse createdCatalogResponse = this.modelMapperService.forResponse().map(this.catalogRepository.save(catalog), CreatedCatalogResponse.class);
        return createdCatalogResponse;

    }

    @Override
    public UpdatedCatalogResponse update(UpdateCatalogRequest updateCatalogRequest) {
        Catalog catalog = this.modelMapperService.forRequest().map(updateCatalogRequest,Catalog.class);
        return this.modelMapperService.forResponse().
                map(this.catalogRepository.save(catalog), UpdatedCatalogResponse.class);
    }

    @Override
    public GetByIdCatalogResponse getById(int id) {
        Optional<Catalog> catalog =this.catalogRepository.findById(id);
        return this.modelMapperService.forResponse().map(catalog.get(), GetByIdCatalogResponse.class);
    }

    @Override
    public List<GetAllCatalogResponse> getAll() {
        List<Catalog> catalogs = this.catalogRepository.findAll();
        List<GetAllCatalogResponse> getAllCatalogResponses = catalogs.stream().map(catalog -> this.modelMapperService.forResponse().
                map(catalog, GetAllCatalogResponse.class)).toList();
        return getAllCatalogResponses;
    }

    @Override
    public void delete(int id) {
        this.catalogRepository.deleteById(id);
    }


}
