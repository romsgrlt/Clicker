package com.romsteam.clicker.game.item;

import com.romsteam.clicker.game.exceptions.RegistryException;
import lombok.ToString;

import java.util.HashMap;
@ToString
public class ItemRegistry{
    HashMap<Long, Item> itemRegistry = new HashMap<>();

    public Item getItem(Long id) throws RegistryException {
        Item item = itemRegistry.get(id);
        if(item==null)
            throw new RegistryException(RegistryException.NO_ITEM_WITH_THIS_ID(id));
        return item;
    }
    public void addNewItem(Item item) throws RegistryException {
        if(itemRegistry.containsKey(item.getId()))
            throw new RegistryException("Error trying to register item :"+item.getName()+" because Id :"+item.getId()+" is already given to item :"+itemRegistry.get(item.getId()).getName());
        itemRegistry.put(item.getId(),item);
    }


}
