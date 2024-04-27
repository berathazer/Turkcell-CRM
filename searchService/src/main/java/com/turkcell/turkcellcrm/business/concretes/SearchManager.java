package com.turkcell.turkcellcrm.business.concretes;

import com.turkcell.turkcellcrm.business.abstracts.SearchService;
import com.turkcell.turkcellcrm.entities.Customer;
import com.turkcell.turkcellcrm.repositories.SearchRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SearchManager implements SearchService {
    private SearchRepository searchRepository;

    @Override
    public void add(Customer customer) {
        this.searchRepository.save(customer);

    }
}
