package com.romsteam.clicker.game.item;

public class ItemFactory {
    public static Item createRessource(Long itemId, String itemName){
        Item item = new Item(itemName,itemId,ItemCategory.RESSOURCE,100){
        };
        return item;
    }
}
