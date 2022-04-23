package com.kzarebski;

import java.util.HashMap;
import java.util.Map.Entry;

public class Plugboard {
    HashMap<Character, Character> plug_board_dict = new HashMap<Character, Character>();

    public Plugboard() {
        plug_board_dict.put('A', 'Z');
        plug_board_dict.put('B', 'P');
        plug_board_dict.put('C', 'M');
        plug_board_dict.put('D', 'S');
        plug_board_dict.put('E', 'Y');
        plug_board_dict.put('F', 'U');
        plug_board_dict.put('G', 'N');
        plug_board_dict.put('H', 'V');
        plug_board_dict.put('I', 'Q');
        plug_board_dict.put('J', 'X');
        plug_board_dict.put('K', 'T');
        plug_board_dict.put('L', 'R');
        plug_board_dict.put('M', 'C');
        plug_board_dict.put('N', 'G');
        plug_board_dict.put('O', 'W');
        plug_board_dict.put('P', 'B');
        plug_board_dict.put('Q', 'I');
        plug_board_dict.put('R', 'L');
        plug_board_dict.put('S', 'D');
        plug_board_dict.put('T', 'K');
        plug_board_dict.put('U', 'F');
        plug_board_dict.put('V', 'H');
        plug_board_dict.put('W', 'O');
        plug_board_dict.put('X', 'J');
        plug_board_dict.put('Y', 'E');
        plug_board_dict.put('Z', 'A');
    }

    public Character plugboard_conversion(Character letter) {
        return plug_board_dict.get(letter);
    }

    public Character plugboard_conversion_inv(Character letter) throws Exception {
        for(Entry<Character, Character> entry : plug_board_dict.entrySet()) {
            if(entry.getValue() == letter) return entry.getKey();
        }
        throw new InternalErrorException("Could not find inverse conversion of character '"+letter+"'");
    }

    public void swap_letter_wiring(Character letter_1, Character letter_2) throws Exception {
        Character init_1 = plugboard_conversion(letter_1);
        Character init_2 = plugboard_conversion_inv(letter_2);
        plug_board_dict.put(letter_1, letter_2);
        plug_board_dict.put(letter_2, letter_1);
        plug_board_dict.put(init_2, init_1);
        plug_board_dict.put(init_1, init_2);
    }
}

