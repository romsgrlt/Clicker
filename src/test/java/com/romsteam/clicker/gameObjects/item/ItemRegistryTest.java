package com.romsteam.clicker.gameObjects.item;

import com.romsteam.clicker.gameObjects.Item;
import com.romsteam.clicker.gameObjects.gameExceptions.RegistryException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ItemRegistryTest {
    @Mock
    HashMap<Long,Item>itemRegistryHashMock ;
    @InjectMocks
    ItemRegistry itemRegistry = new ItemRegistry();
    @Test
    void getItem() throws RegistryException {

        assertThrows(RegistryException.class,
               ()->{itemRegistry.getItem(1L);},
               RegistryException.NO_ITEM_WITH_THIS_ID(1L));

        Item item = createItem();
        itemRegistry.addNewItem(item);

        assertEquals(itemRegistry.getItem(1L),item);
    }



    private Item createItem() {
        return ItemFactory.createRessource(1L,"Wood");
    }

    @Test
    void addNewItem() {
    }
}