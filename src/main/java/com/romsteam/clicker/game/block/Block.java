package com.romsteam.clicker.game.block;

import com.romsteam.clicker.game.item.Item;
import com.romsteam.clicker.game.item.ItemCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@Getter
@ToString
public abstract class Block {
    private final String name;
    private long id;
    private Long[] items;
    private Integer[] percent;
    private Integer[] amount;

    public abstract HashMap<Long, Integer> drops();
}
