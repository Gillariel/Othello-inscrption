/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.HashMap;
import java.util.Map;

/**
 * @author User
 */
public class Game implements Comparable<Game>{
    private final long ID;
    private int priority;
    private Gamer J1;
    private Gamer J2;

    /**
    *@param
    */
    public Game(String id1, String id2, int priority) {
        ID = System.currentTimeMillis();
        J1 = new Gamer(id1);
        J2 = new Gamer(id2);
        this.priority = priority;
    }

    public Game(int ID, String id1, String id2, int priority) {
        this.ID = ID;
        J1 = new Gamer(id1);
        J2 = new Gamer(id2);
        this.priority = priority;
    }
    
     public static Game questionMarkGame(int priority) {
        return new Game("?", "?",priority);
    }
    
    public long getId() { return ID; }
    public Gamer getJ1() { return J1; }
    public Gamer getJ2() { return J2; }
    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }
    
    @Override
    public int compareTo(Game o) {
        return this.getPriority() - o.priority;
    }
}
