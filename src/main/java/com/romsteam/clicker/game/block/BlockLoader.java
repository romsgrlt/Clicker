package com.romsteam.clicker.game.block;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.romsteam.clicker.game.exceptions.RegistryException;
import com.romsteam.clicker.game.item.Item;
import com.romsteam.clicker.game.item.ItemFactory;
import com.romsteam.clicker.game.item.ItemRegistry;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class BlockLoader { public static String FOLDER_PATH = "src/main/resources/blocks";
    private final String filePath;

    public BlockLoader(String filePath) {
        this.filePath = filePath;
    }
    public BlockLoader() {
        this.filePath = FOLDER_PATH;
    }

    public  void loadBlocks(BlockRegistry blockRegistry)
            throws IOException,
            RegistryException {

        File folder = new File(filePath);
        File[] files = folder.listFiles();

        for(File file : files){

            JsonFactory jsonFactory = new JsonFactory();
            JsonParser jsonParser = jsonFactory.createParser(file);

            String blockName ="";
            Long blockId = -1L;
            String blockCategory = "";


            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String name = jsonParser.getCurrentName();
                if (name == null) {
                    continue;
                }
                switch (name) {
                    case "blockId":
                        jsonParser.nextToken();
                        blockId = jsonParser.getLongValue();
                        break;
                    case "blockName":
                        jsonParser.nextToken();
                        blockName = jsonParser.getValueAsString();
                        break;
                    case "blockCategory":
                        jsonParser.nextToken();
                        blockCategory = jsonParser.getValueAsString();
                        break;
                    default :
                        jsonParser.skipChildren();
                        break;
                }
            }
            switch(blockCategory){
                case "RESSOURCE":
                    List<Long> items = new ArrayList<>();
                    List<Integer> percent= new ArrayList<>();
                    List<Integer> amount= new ArrayList<>();
                    loadRessourceDrop(file, items, percent , amount);
                    blockRegistry.addNewItem(BlockFactory.createRessourceBlock(blockId,blockName, items, percent , amount));
                    break;
            }
        }
    }

    private void loadRessourceDrop(File file, List<Long> items, List<Integer> percent, List<Integer> amount) throws IOException {
        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jsonParser = jsonFactory.createParser(file);

        while(jsonParser.nextToken()!=JsonToken.END_OBJECT){
            String name = jsonParser.getCurrentName();
            if(name == "drops"){
                while(jsonParser.nextToken()!= JsonToken.END_ARRAY) {
                    while(jsonParser.nextToken()!= JsonToken.END_OBJECT) {
                                name = jsonParser.getCurrentName();
                                if (name == null) {
                                    continue;
                                }
                                switch (name) {
                                    case "itemId":
                                        jsonParser.nextToken();
                                        items.add(jsonParser.getLongValue());
                                        break;
                                    case "probability":
                                        percent.add(jsonParser.nextIntValue(0));
                                        break;
                                    case "minAmount", "maxAmount":
                                        amount.add(jsonParser.nextIntValue(0));
                                        break;
                                    default :
                                        jsonParser.skipChildren();
                                        break;
                                }
                            }
                    }
                }
            }
        }
    }

