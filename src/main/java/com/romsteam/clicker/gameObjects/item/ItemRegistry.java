package com.romsteam.clicker.gameObjects.item;

import com.romsteam.clicker.gameObjects.Item;
import com.romsteam.clicker.gameObjects.gameExceptions.RegistryException;

import java.util.HashMap;

public class ItemRegistry{
    HashMap<Long, Item> itemRegistry = new HashMap<>();

    public Item getItem(Long id){
        return itemRegistry.get(id);
    }
    public void addNewItem(Long id, Item item){
        if(itemRegistry.containsKey(id))
            throw new RegistryException("Error trying to register item :"+item.getName()+" because Id :"+id+" is already given to item :"+itemRegistry.get(id).getName());
    }
}
