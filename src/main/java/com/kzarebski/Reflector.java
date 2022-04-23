package com.kzarebski;

import java.util.HashMap;

class Reflector {
    HashMap<Character, Character> reflector_dict;
    Character name;
    public Reflector(Character name, HashMap<Character, Character> ref_dict) {
        this.reflector_dict = ref_dict;
        this.name = name;
    }

    public Character reflector_conversion(Character letter) {
        return reflector_dict.get(letter);
    }

    public static Reflector Reflector_B() {
        Character name = 'B';
        HashMap<Character, Character> mapping = new HashMap<Character, Character>();
        mapping.put('A', 'Y');
        mapping.put('B', 'R');
        mapping.put('C', 'U');
        mapping.put('D', 'H');
        mapping.put('E', 'Q');
        mapping.put('F', 'S');
        mapping.put('G', 'L');
        mapping.put('H', 'D');
        mapping.put('K', 'N');
        mapping.put('L', 'G');
        mapping.put('M', 'O');
        mapping.put('N', 'K');
        mapping.put('O', 'M');
        mapping.put('P', 'I');
        mapping.put('Q', 'E');
        mapping.put('R', 'B');
        mapping.put('S', 'F');
        mapping.put('T', 'Z');
        mapping.put('U', 'C');
        mapping.put('V', 'W');
        mapping.put('W', 'V');
        mapping.put('X', 'J');
        mapping.put('Y', 'A');
        mapping.put('Z', 'T');
        mapping.put('I', 'P');
        mapping.put('J', 'X');
        return new Reflector(name, mapping);
    }

    public static Reflector Reflector_C() {
        Character name = 'C';
        HashMap<Character, Character> mapping = new HashMap<Character, Character>();
        mapping.put('A', 'Y');
        mapping.put('B', 'R');
        mapping.put('C', 'U');
        mapping.put('D', 'H');
        mapping.put('E', 'Q');
        mapping.put('F', 'S');
        mapping.put('G', 'L');
        mapping.put('H', 'D');
        mapping.put('K', 'N');
        mapping.put('L', 'G');
        mapping.put('M', 'O');
        mapping.put('N', 'K');
        mapping.put('O', 'M');
        mapping.put('P', 'I');
        mapping.put('Q', 'E');
        mapping.put('R', 'B');
        mapping.put('S', 'F');
        mapping.put('T', 'Z');
        mapping.put('U', 'C');
        mapping.put('V', 'W');
        mapping.put('W', 'V');
        mapping.put('X', 'J');
        mapping.put('Y', 'A');
        mapping.put('Z', 'T');
        mapping.put('I', 'P');
        mapping.put('J', 'X');
        return new Reflector(name, mapping);
    }

    public static Reflector Reflectors(Character type) throws Exception {
        if(type == 'B') return Reflector_B();
        else if(type == 'C') return Reflector_C();
        throw new Exception("Unrecognised reflector type '"+type+"'");
    }
}
