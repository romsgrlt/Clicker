package com.romsteam.clicker.game.item;

import com.romsteam.clicker.game.item.ItemCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public abstract class Item {
    private final String name;
    private long id;
    private ItemCategory itemCategory;
    private int maxStack;

}
