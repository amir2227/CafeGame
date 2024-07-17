package com.cafe.game.utils;

public enum Constants {
    REVOKE_LIST_PREFIX("RevokeList::");

    private String name;

    Constants(String name){
        this.name = name;
    }
    public String getStr(){
        return this.name;
    }
}
