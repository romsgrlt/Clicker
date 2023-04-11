package com.romsteam.clicker.game.block;

import java.util.HashMap;
import java.util.List;

public class BlockFactory {
    public static Block createRessourceBlock(Long blockId, String blockName, List<Long> items, List<Integer> percent, List<Integer> amount) {
        return new Block(blockName,blockId,items.toArray(new Long[1]),percent.toArray(new Integer[1]),amount.toArray(new Integer[1])){
            @Override
            public HashMap<Long,Integer> drops() {
                int i = 0;
                HashMap<Long,Integer> drop = new HashMap<>();
                for(; i < getItems().length;++i){;
                    int prob = getPercent()[i];
                    if(Math.random()*100<prob){
                        int minAmount = getAmount()[i*2];
                        int maxAmount = getAmount()[i*2+1];
                        int amount =(int)(Math.random()*(maxAmount-minAmount)+minAmount);
                        drop.put(items.get(i),amount);
                    }
                }
                return drop;
            }
        };
    }
}
