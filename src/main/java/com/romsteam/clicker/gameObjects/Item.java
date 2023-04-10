package com.romsteam.clicker.gameObjects;

import com.romsteam.clicker.gameObjects.item.ItemCategory;
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
