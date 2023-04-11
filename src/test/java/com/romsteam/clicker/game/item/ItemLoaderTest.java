package com.romsteam.clicker.game.item;

import com.romsteam.clicker.game.exceptions.RegistryException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

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