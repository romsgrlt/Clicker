package com.romsteam.clicker.game.block;

import com.romsteam.clicker.game.exceptions.RegistryException;
import lombok.ToString;

import java.util.HashMap;
@ToString
public class BlockRegistry {
    HashMap<Long, Block> blockRegistry = new HashMap<>();

    public Block getItem(Long id) throws RegistryException {
        Block block = blockRegistry.get(id);
        if(block==null)
            throw new RegistryException(RegistryException.NO_NLOCK_WITH_THIS_ID(id));
        return block;
    }
    public void addNewItem(Block block) throws RegistryException {
        if(blockRegistry.containsKey(block.getId()))
            throw new RegistryException("Error trying to register block :"+block.getName()+" because Id :"+block.getId()+" is already given to block :"+blockRegistry.get(block.getId()).getName());
        blockRegistry.put(block.getId(),block);
    }
}
