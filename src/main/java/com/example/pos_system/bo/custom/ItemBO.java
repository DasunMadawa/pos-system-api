package com.example.pos_system.bo.custom;

import com.example.pos_system.bo.SuperBO;
import com.example.pos_system.dto.CustomerDTO;
import com.example.pos_system.dto.ItemDTO;

import java.util.List;

public interface ItemBO extends SuperBO {
    public boolean addItem(ItemDTO itemDTO) throws Exception;
    public ItemDTO searchItem(String id) throws Exception;
    public boolean updateItem(ItemDTO itemDTO) throws Exception;
    public boolean deleteItem(String id) throws Exception;
    public List<ItemDTO> getAllItems() throws Exception;

}
