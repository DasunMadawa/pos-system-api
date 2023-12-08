package com.example.pos_system.bo.custom.impl;

import com.example.pos_system.bo.custom.CustomerBO;
import com.example.pos_system.dao.DAOFactory;
import com.example.pos_system.dao.custom.CustomerDAO;
import com.example.pos_system.dto.CustomerDTO;
import com.example.pos_system.entity.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO)DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) throws Exception {
        return customerDAO.add(
                new Customer(
                        customerDTO.getcId() ,
                        customerDTO.getcName() ,
                        customerDTO.getcAddress() ,
                        customerDTO.getcSalary()
                )
        );
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws Exception {
        Customer customer = customerDAO.search(id);

        return new CustomerDTO(
                customer.getcId() ,
                customer.getcName() ,
                customer.getcAddress() ,
                customer.getcSalary()
        );

    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception {
        return customerDAO.update(
                new Customer(
                        customerDTO.getcId() ,
                        customerDTO.getcName() ,
                        customerDTO.getcAddress() ,
                        customerDTO.getcSalary()
                )
        );

    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        return customerDAO.delete(id);

    }

    @Override
    public List<CustomerDTO> getAllCustomers() throws Exception {
        List<Customer> customerList = customerDAO.getAll();

        List<CustomerDTO> customerDTOList = new ArrayList<>();

        for (Customer customer : customerList) {
            customerDTOList.add(
                    new CustomerDTO(
                            customer.getcId() ,
                            customer.getcName() ,
                            customer.getcAddress() ,
                            customer.getcSalary()
                    )
            );
        }

        return customerDTOList;

    }

}
