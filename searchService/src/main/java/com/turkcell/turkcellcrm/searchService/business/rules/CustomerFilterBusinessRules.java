package com.turkcell.turkcellcrm.searchService.business.rules;

import com.turkcell.turkcellcrm.common.events.customer.CustomerCreatedEvent;
import com.turkcell.turkcellcrm.common.events.customer.CustomerUpdatedEvent;
import com.turkcell.turkcellcrm.searchService.business.dto.request.GetAllCustomerRequest;
import com.turkcell.turkcellcrm.searchService.business.messages.CustomerFilterBusinessRulesMessages;
import com.turkcell.turkcellcrm.searchService.core.utilities.exceptions.types.BusinessException;
import com.turkcell.turkcellcrm.searchService.dataAccess.SearchCustomerRepository;
import com.turkcell.turkcellcrm.searchService.entities.Customer;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerFilterBusinessRules {

    private SearchCustomerRepository searchCustomerRepository;
    private MongoTemplate mongoTemplate;

    public List<Customer> filterCustomer(GetAllCustomerRequest getAllCustomerRequest) {

        Query query = new Query();
        query.addCriteria(Criteria.where("deletedDate").is(null));

        Map<String,String> dtoMap = new HashMap();
        dtoMap.put("middleName" ,getAllCustomerRequest.getMiddleName());
        dtoMap.put("firstName" ,getAllCustomerRequest.getFirstName());
        dtoMap.put("lastName" ,getAllCustomerRequest.getLastName());
        dtoMap.put("nationalityNumber",getAllCustomerRequest.getNationalityNumber());
        dtoMap.put("accountAccountNumber",getAllCustomerRequest.getAccountAccountNumber());
        dtoMap.put("mobilePhoneNumber",getAllCustomerRequest.getMobilePhoneNumber());

        for(Map.Entry<String,String> entry : dtoMap.entrySet()){
            String key= entry.getKey();
            String value = entry.getValue();
            if(!value.equals("string")){  //value != null
                System.out.println(key + " " + value);
                query.addCriteria(Criteria.where(key).regex(".*" + value + ".*","i"));
            }
        }

        if (getAllCustomerRequest.getCustomerId() != 0) {
            query.addCriteria(Criteria.where("customerId").is(getAllCustomerRequest.getCustomerId()));
        }

        return this.mongoTemplate.find(query, Customer.class);
    }

    public void IsCustomerIdExistById(CustomerCreatedEvent customerCreatedEvent){

        if (this.searchCustomerRepository.findCustomersByCustomerId(customerCreatedEvent.getCustomerId()).isPresent()){
            throw new BusinessException(CustomerFilterBusinessRulesMessages.CUSTOMER_IS_ALREADY_EXIST);
        }

    }
    public void IsCustomerIdExistById(CustomerUpdatedEvent customerUpdatedEvent){

        if (this.searchCustomerRepository.findCustomersByCustomerId(customerUpdatedEvent.getId()).isPresent()){
            throw new BusinessException(CustomerFilterBusinessRulesMessages.CUSTOMER_IS_ALREADY_EXIST);
        }

    }

    public Customer IsCustomerAlreadyDeleted(int customerId){

        Customer customer = this.searchCustomerRepository.findCustomersByCustomerId(customerId).orElseThrow(() -> new BusinessException(CustomerFilterBusinessRulesMessages.CUSTOMER_NOT_EXISTS));

        if(customer.getDeletedDate() != null){
            throw new BusinessException(CustomerFilterBusinessRulesMessages.CUSTOMER_IS_ALREADY_DELETED);
        }

        return customer;
    }
}
