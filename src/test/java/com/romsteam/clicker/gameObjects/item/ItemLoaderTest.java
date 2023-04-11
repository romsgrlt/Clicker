package com.romsteam.clicker.gameObjects.item;

import com.romsteam.clicker.gameObjects.gameExceptions.RegistryException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ItemLoaderTest {
    private static String TEST_PATH = "src/test/json/item";
    private ItemLoader itemLoader = new ItemLoader(TEST_PATH);

    @Test
    void loadItems() throws IOException, RegistryException {
        ItemRegistry itemRegistry = new ItemRegistry();
        itemLoader.loadItems(itemRegistry);
        System.out.println(itemRegistry);
    }
}