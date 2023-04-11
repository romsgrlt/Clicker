package com.romsteam.clicker.game.exceptions;

public class RegistryException extends Exception{
    public static String NO_ITEM_WITH_THIS_ID(Long id){
        return "Error trying to get item with id :"+id+" because no items are registered with this id.";
    }

    public RegistryException(String message) {
        super(message);
    }

    public static String NO_NLOCK_WITH_THIS_ID(Long id) {
        return "Error trying to get block with id :"+id+" because no blocks are registered with this id.";
    }
}
