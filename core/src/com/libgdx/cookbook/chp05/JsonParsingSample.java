package com.libgdx.cookbook.chp05;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.libgdx.cookbook.help.BaseScreen;

/**
 * Json�ļ�����
 * 
 */
public class JsonParsingSample extends BaseScreen {

    @Override
    public void show() {
        
        System.out.println("======================");
        System.out.println("Reading character.json");
        System.out.println("======================");
        
        Json json = new Json();
        json.setElementType(Character.class, "items", Item.class);  // ָ��Character�е�item��������
        Character character = json.fromJson(Character.class, Gdx.files.internal("data/character.json"));    // ��Json�ļ��д���һ��Charactor����
        System.out.println(character);
//        System.out.println("dexterity: " + character.dexterity);  // ���ʵ�������,��Ҫ������Ӧ��class
        
        System.out.println();
        
        System.out.println("=====================");
        System.out.println("Serializing character");
        System.out.println("=====================");
        
        System.out.println(json.prettyPrint(json.toJson(character)));
        
        Gdx.app.exit();
    }

    
    public static class Item {
        private String name;
        private int number;
        
        @Override
        public String toString() {
            return name + "(" + number + ")";
        }
    }
    
    public static class Character {
        private String name = "";
        private int experience = 0;
        private  int strength = 1;
        private  int dexterity = 1;
        private  int intelligence = 1;
        
        public Array<Item> items = new Array<Item>();
        
        @Override
        public String toString() {
            String string = new String();
            string += "Name: " + name + "\n";
            string += "Experience: " + experience + "\n";
            string += "Strength: " + strength + "\n";
            string += "Dexterity: " + dexterity + "\n";
            string += "Intelligence: " + intelligence + "\n";
            string += "Items: ";
            
            for (Item item : items) {
                string += item.toString() + " ";
            }
            
            return string;
        }
    }
}
