package com.romsteam.clicker.gameObjects.item;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.romsteam.clicker.gameObjects.gameExceptions.RegistryException;

public class ItemLoader {
    public static String FOLDER_PATH = "src/main/resources/items";
    private final String filePath;

    public ItemLoader(String filePath) {
        this.filePath = filePath;
    }
    public ItemLoader() {
        this.filePath = FOLDER_PATH;
    }

    public  void loadItems(ItemRegistry itemRegistry)
            throws IOException,
            RegistryException {

        File folder = new File(filePath);
        File[] files = folder.listFiles();

        for(File file : files){

            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jsonParser = jsonFactory.createParser(file);

            String itemName ="";
            Long itemId = -1L;
            String itemCategory = "";


            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String name = jsonParser.getCurrentName();
                if (name == null) {
                    continue;
                }
                switch (name) {
                    case "itemId":
                        jsonParser.nextToken();
                        itemId = jsonParser.getLongValue();
                        break;
                    case "itemCategory":
                        jsonParser.nextToken();
                        itemCategory = jsonParser.getValueAsString();
                        break;
                    case "itemName":
                        jsonParser.nextToken();
                        itemName = jsonParser.getValueAsString();
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
            switch(itemCategory){
                case "RESSOURCE":
                    itemRegistry.addNewItem(ItemFactory.createRessource(itemId,itemName));
                    break;
            }
        }
    }
}
