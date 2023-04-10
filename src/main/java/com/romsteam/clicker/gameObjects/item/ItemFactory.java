package com.romsteam.clicker.gameObjects.item;

import com.romsteam.clicker.gameObjects.Item;

public class ItemFactory {
    public static Item createRessource(Long itemId, String itemName){
        Item item = new Item(itemName,itemId,ItemCategory.RESSOURCE,100){
        };
        return item;
    }
}
