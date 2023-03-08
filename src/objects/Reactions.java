/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;



public enum Reactions {
    NON(0, "Like", "/img/ic_like_outline.png","#606266"),
    LIKE(1, "Like", "/img/ic_like.png","#056BE1"),
    LOVE(2, "Love", "/img/ic_love_.png","#E12C4A"),
    CARE(3, "Care", "/img/ic_care.png","#EAA823"),
    HAHA(4, "Haha", "/img/ic_haha.png","#EAA823"),
    WOW(5, "Wow", "/img/ic_wow.png","#EAA823"),
    SAD(6, "Sad", "/img/ic_sad.png","#EAA823"),
    ANGRY(7, "Angry", "/img/ic_angry.png","DD6B0E");

    private final int id;
    private final String name;
    private final String imgSrc;
    private String color;

    Reactions(int id, String name, String imgSrc, String color) {
        this.id = id;
        this.name = name;
        this.imgSrc = imgSrc;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
}

