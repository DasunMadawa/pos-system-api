package com.example.pos_system.bo.custom.impl;

import com.example.pos_system.bo.custom.CustomerBO;
import com.example.pos_system.dao.DAOFactory;
import com.example.pos_system.dao.custom.CustomerDAO;
import com.example.pos_system.dto.CustomerDTO;
import com.example.pos_system.entity.Customer;
import com.example.pos_system.util.FactoryConfiguration;
import com.mysql.cj.xdevapi.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean addCustomer(CustomerDTO customerDTO) throws Exception {
        Transaction transaction = null;

        try (Session session = FactoryConfiguration.getInstance().getSession()){
            transaction = session.beginTransaction();

            customerDAO.add(toCustomer(customerDTO), session);

            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception();
        }
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws Exception {
        Transaction transaction = null;

        try (Session session = FactoryConfiguration.getInstance().getSession()){
            transaction = session.beginTransaction();

            Customer customer = customerDAO.search(id, session);

            transaction.commit();
            return toCustomerDTO(customer);
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception();
        }

    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception {
        Transaction transaction = null;

        try (Session session = FactoryConfiguration.getInstance().getSession()){
            transaction = session.beginTransaction();

            customerDAO.update(toCustomer(customerDTO), session);

            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception();
        }

    }

    @Override
    public boolean deleteCustomer(String id) throws Exception {
        Transaction transaction = null;

        try (Session session = FactoryConfiguration.getInstance().getSession()){
            transaction = session.beginTransaction();

            customerDAO.delete(id, session);

            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception();
        }

    }

    @Override
    public List<CustomerDTO> getAllCustomers() throws Exception {
        Transaction transaction = null;

        try (Session session = FactoryConfiguration.getInstance().getSession()){
            transaction = session.beginTransaction();

            List<Customer> customerList = customerDAO.getAll(session);

            transaction.commit();

            List<CustomerDTO> customerDTOList = new ArrayList<>();

            for (Customer customer : customerList) {
                customerDTOList.add(toCustomerDTO(customer));

            }

            return customerDTOList;
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception();
        }

    }

    private CustomerDTO toCustomerDTO(Customer customer) {
        return new CustomerDTO(
                customer.getcId(),
                customer.getcName(),
                customer.getcAddress(),
                customer.getcSalary()
        );
    }

    private Customer toCustomer(CustomerDTO customerDTO) {
        return new Customer(
                customerDTO.getcId(),
                customerDTO.getcName(),
                customerDTO.getcAddress(),
                customerDTO.getcSalary()
        );
    }

}
