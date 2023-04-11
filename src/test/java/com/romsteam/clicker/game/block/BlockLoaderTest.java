package com.romsteam.clicker.game.block;

import com.romsteam.clicker.game.exceptions.RegistryException;
import com.romsteam.clicker.game.item.ItemLoader;
import com.romsteam.clicker.game.item.ItemRegistry;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class BlockLoaderTest {
    private static String TEST_PATH = "src/test/json/block";
    private BlockLoader blockLoader = new BlockLoader();

    @Test
    void loadItems() throws IOException, RegistryException {
        BlockRegistry blockRegistry = new BlockRegistry();
        blockLoader.loadBlocks(blockRegistry);
        for(int i = 0;i<10;++i)System.out.println(blockRegistry.getItem(1L).drops());
        System.out.println(blockRegistry);
    }
}