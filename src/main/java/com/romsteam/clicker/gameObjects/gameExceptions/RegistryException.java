package com.romsteam.clicker.gameObjects.gameExceptions;

public class RegistryException extends Exception{
    public static String NO_ITEM_WITH_THIS_ID(Long id){
        return "Error trying to get item of id :"+id+" because no items are registered at this id.";
    }

    public RegistryException(String message) {
        super(message);
    }
}
