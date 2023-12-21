package com.example.pos_system.bo.custom.impl;

import com.example.pos_system.bo.custom.ItemBO;
import com.example.pos_system.dao.DAOFactory;
import com.example.pos_system.dao.custom.ItemDAO;
import com.example.pos_system.dto.CustomerDTO;
import com.example.pos_system.dto.ItemDTO;
import com.example.pos_system.entity.Customer;
import com.example.pos_system.entity.Item;
import com.example.pos_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);


    @Override
    public boolean addItem(ItemDTO itemDTO) throws Exception {
        Transaction transaction = null;

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            itemDAO.add(toItem(itemDTO) , session);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception();
        }

    }

    @Override
    public ItemDTO searchItem(String id) throws Exception {
        Transaction transaction = null;

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            Item item = itemDAO.search(id, session);
            transaction.commit();
            return toItemDTO(item);
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception();
        }

    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws Exception {
        Transaction transaction = null;

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            itemDAO.update(toItem(itemDTO) , session);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception();
        }
    }

    @Override
    public boolean deleteItem(String id) throws Exception {
        Transaction transaction = null;

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            itemDAO.delete(id , session);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception();
        }
    }

    @Override
    public List<ItemDTO> getAllItems() throws Exception {
        Transaction transaction = null;

        try (Session session = FactoryConfiguration.getInstance().getSession()){
            transaction = session.beginTransaction();

            List<Item> itemList = itemDAO.getAll(session);

            transaction.commit();

            List<ItemDTO> itemDTOList = new ArrayList<>();

            for (Item item : itemList) {
                itemDTOList.add(toItemDTO(item));

            }

            return itemDTOList;
        } catch (Exception e) {
            transaction.rollback();
            throw new Exception();
        }


    }

    private ItemDTO toItemDTO(Item item){
        return new ItemDTO(
                item.getiCode() ,
                item.getiName() ,
                item.getiPrice() ,
                item.getiQty()
        );

    }

    private Item toItem(ItemDTO itemDTO){
        return new Item(
                itemDTO.getiCode() ,
                itemDTO.getiName() ,
                itemDTO.getiPrice() ,
                itemDTO.getiQty()
        );

    }

}
