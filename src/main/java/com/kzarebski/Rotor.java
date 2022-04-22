package com.kzarebski;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

public class Rotor {
    char[] alpha = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
        'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
        'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
        'Y', 'Z'
    };
    char face = 'A';
    String name;
    Vector<Character> notches = new Vector<Character>();
    HashMap<Integer, Integer> wiring = new HashMap<Integer, Integer>();

    public Rotor(String name, Vector<Character> notches, HashMap<Integer, Integer> wiring) {
        this.name = name;
        this.notches = notches;
        this.wiring = wiring;
    }

    Integer alpha_index(char letter) {
        return Arrays.binarySearch(this.alpha, letter);
    }

    public char[] get_letters_dict() {
        return alpha;
    }

    public Vector<Character> get_notches() {
        return notches;
    }

    void rotate_rotor(Rotor other) {
        Integer pos = alpha_index(this.face);
        pos = (pos > 25) ? 0 : pos+1;

        if(other != null) {
            other.rotate_rotor(null);
        }

        this.face = alpha[pos];
    }

    Character get_face_letter() {
        return face;
    }

    Integer get_output_terminal(char letter) {
        Integer i = alpha_index(letter);
        return this.wiring.get(i);
    }

    Integer get_input_terminal(char letter) {
        Integer i = alpha_index(letter);
        for(int j = 0; j < this.wiring.size(); j++) {
            if(i == j) {
                return i;
            }
        }
        throw new InternalErrorException("Could not find Input terminal for letter '"+letter+"'");
    }

    Character get_rotor_conversion(Character letter) {
        Integer i = alpha_index(letter);

        return this.alpha[this.wiring.get(i)];
    }

    Character get_rotor_conversion_inv(Character letter) {
        Integer i = alpha_index(letter);

        for(int j = 0; j < this.wiring.size(); ++j)
        {
            if(this.wiring.get(j) == i)
            {
                return this.alpha[j];
            }
        }

        throw new InternalErrorException("Could not find the inverse of character '"+letter+"'");
    }

    public void rotate_inner_ring() {
        Integer x = wiring.get(0);
        for(int i=0; i < wiring.size(); i++) {
            if(i == 25) wiring.put(i, x);
            else {
                Integer y = wiring.get(i+1);
                wiring.put(i, y);
            }
        }
    }

    public static Rotor Rotor_1() {
        String label = "I";
        Vector<Character> notches = new Vector<Character>();
        notches.add('R');
        HashMap<Integer, Integer> wiring = new HashMap<Integer, Integer>();
        wiring.put(0, 4);
        wiring.put(1, 10);
        wiring.put(2, 12);
        wiring.put(3, 5);
        wiring.put(4, 11);
        wiring.put(5, 6);
        wiring.put(6, 3);
        wiring.put(7, 16);
        wiring.put(8, 21);
        wiring.put(9, 25);
        wiring.put(10, 13);
        wiring.put(11, 19);
        wiring.put(12, 14);
        wiring.put(13, 22);
        wiring.put(14, 24);
        wiring.put(15, 7);
        wiring.put(16, 23);
        wiring.put(17, 20);
        wiring.put(18, 18);
        wiring.put(19, 15);
        wiring.put(20, 0);
        wiring.put(21, 8);
        wiring.put(22, 1);
        wiring.put(23, 17);
        wiring.put(24, 2);
        wiring.put(25, 9);
        return new Rotor(label, notches, wiring);
    }

    public static Rotor Rotor_2() {
        String label = "II";
        Vector<Character> notches = new Vector<Character>();
        notches.add('F');
        HashMap<Integer, Integer> wiring = new HashMap<Integer, Integer>();
        wiring.put(0, 0);
        wiring.put(1, 9);
        wiring.put(2, 3);
		wiring.put(3, 10);
        wiring.put(4, 18);
        wiring.put(5, 8);
        wiring.put(6, 17);
        wiring.put(7, 20);
        wiring.put(8, 23);
        wiring.put(9, 1);
        wiring.put(10, 11);
        wiring.put(11, 7);
		wiring.put(12, 22);
        wiring.put(13, 19);
        wiring.put(14, 12);
        wiring.put(15, 2);
        wiring.put(16, 16);
        wiring.put(17, 6);
        wiring.put(18, 25);
        wiring.put(19, 13);
        wiring.put(20, 15);
        wiring.put(21, 24);
        wiring.put(22, 5);
        wiring.put(23, 21);
        wiring.put(24, 14);
        wiring.put(25, 4);
        return new Rotor(label, notches, wiring);
    }

    public static Rotor Rotor_3() {
        String label = "III";
        Vector<Character> notches = new Vector<Character>();
        notches.add('W');
        HashMap<Integer, Integer> wiring = new HashMap<Integer, Integer>();
        wiring.put(0, 1);
        wiring.put(1, 3);
        wiring.put(2, 5);
        wiring.put(3, 7);
        wiring.put(4, 9);
        wiring.put(5, 11);
        wiring.put(6, 2);
        wiring.put(7, 15);
        wiring.put(8, 17);
        wiring.put(9, 19);
        wiring.put(10, 23);
        wiring.put(11, 21);
        wiring.put(12, 25);
        wiring.put(13, 13);
        wiring.put(14, 24);
        wiring.put(15, 4);
        wiring.put(16, 8);
        wiring.put(17, 22);
        wiring.put(18, 6);
        wiring.put(19, 0);
        wiring.put(20, 10);
        wiring.put(21, 12);
        wiring.put(22, 20);
        wiring.put(23, 18);
        wiring.put(24, 16);
        wiring.put(25, 14);
        return new Rotor(label, notches, wiring);
    }

    public static Rotor Rotor_4() {
        String label = "IV";
        Vector<Character> notches = new Vector<Character>();
        notches.add('K');
        HashMap<Integer, Integer> wiring = new HashMap<Integer, Integer>();
        wiring.put(0, 4);
        wiring.put(1, 18);
        wiring.put(2, 14);
        wiring.put(3, 21);
        wiring.put(4, 15);
        wiring.put(5, 25);
        wiring.put(6, 9);
        wiring.put(7, 0);
        wiring.put(8, 24);
        wiring.put(9, 16);
        wiring.put(10, 20);
        wiring.put(11, 8);
        wiring.put(12, 17);
        wiring.put(13, 7);
        wiring.put(14, 23);
        wiring.put(15, 11);
        wiring.put(16, 13);
        wiring.put(17, 5);
        wiring.put(18, 19);
        wiring.put(19, 6);
        wiring.put(20, 10);
        wiring.put(21, 3);
        wiring.put(22, 2);
        wiring.put(23, 12);
        wiring.put(24, 22);
        wiring.put(25, 1);
        return new Rotor(label, notches, wiring);
    }

    public static Rotor Rotor_5() {
        String label = "V";
        Vector<Character> notches = new Vector<Character>();
        notches.add('A');
        HashMap<Integer, Integer> wiring = new HashMap<Integer, Integer>();
        wiring.put(0, 21);
        wiring.put(1, 25);
        wiring.put(2, 1);
        wiring.put(3, 17);
        wiring.put(4, 6);
        wiring.put(5, 8);
        wiring.put(6, 19);
        wiring.put(7, 24);
        wiring.put(8, 20);
        wiring.put(9, 15);
        wiring.put(10, 18);
        wiring.put(11, 3);
        wiring.put(12, 13);
        wiring.put(13, 7);
        wiring.put(14, 11);
        wiring.put(15, 23);
        wiring.put(16, 0);
        wiring.put(17, 22);
        wiring.put(18, 12);
        wiring.put(19, 9);
        wiring.put(20, 16);
        wiring.put(21, 14);
        wiring.put(22, 5);
        wiring.put(23, 4);
        wiring.put(24, 2);
        wiring.put(25, 10);
        return new Rotor(label, notches, wiring);
    }

    public static Rotor Rotor_6() {
        String label = "VI";
        Vector<Character> notches = new Vector<Character>();
        notches.add('A');
        notches.add('N');
        HashMap<Integer, Integer> wiring = new HashMap<Integer, Integer>();
        wiring.put(0, 9);
        wiring.put(1, 15);
        wiring.put(2, 6);
        wiring.put(3, 21);
        wiring.put(4, 14);
        wiring.put(5, 20);
        wiring.put(6, 12);
        wiring.put(7, 5);
        wiring.put(8, 24);
        wiring.put(9, 16);
        wiring.put(10, 1);
        wiring.put(11, 4);
        wiring.put(12, 13);
        wiring.put(13, 7);
        wiring.put(14, 25);
        wiring.put(15, 17);
        wiring.put(16, 3);
        wiring.put(17, 10);
        wiring.put(18, 0);
        wiring.put(19, 18);
        wiring.put(20, 23);
        wiring.put(21, 11);
        wiring.put(22, 8);
        wiring.put(23, 2);
        wiring.put(24, 19);
        wiring.put(25, 22);
        return new Rotor(label, notches, wiring);
    }

    public static Rotor Rotor_7() {
        String label = "VII";
        Vector<Character> notches = new Vector<Character>();
        notches.add('A');
        notches.add('N');
        HashMap<Integer, Integer> wiring = new HashMap<Integer, Integer>();
        wiring.put(0, 13);
        wiring.put(1, 25);
        wiring.put(2, 9);
        wiring.put(3, 7);
        wiring.put(4, 6);
        wiring.put(5, 17);
        wiring.put(6, 2);
        wiring.put(7, 23);
        wiring.put(8, 12);
        wiring.put(9, 24);
        wiring.put(10, 18);
        wiring.put(11, 22);
        wiring.put(12, 1);
        wiring.put(13, 14);
        wiring.put(14, 20);
        wiring.put(15, 5);
        wiring.put(16, 0);
        wiring.put(17, 8);
        wiring.put(18, 21);
        wiring.put(19, 11);
        wiring.put(20, 15);
        wiring.put(21, 4);
        wiring.put(22, 10);
        wiring.put(23, 16);
        wiring.put(24, 3);
        wiring.put(25, 19);
        return new Rotor(label, notches, wiring);
    }

    public static Rotor Rotor_8() {
        String label = "VIII";
        Vector<Character> notches = new Vector<Character>();
        notches.add('A');
        notches.add('N');
        HashMap<Integer, Integer> wiring = new HashMap<Integer, Integer>();
        wiring.put(0, 5);
        wiring.put(1, 10);
        wiring.put(2, 16);
        wiring.put(3, 7);
        wiring.put(4, 19);
        wiring.put(5, 11);
        wiring.put(6, 23);
        wiring.put(7, 14);
        wiring.put(8, 2);
        wiring.put(9, 1);
        wiring.put(10, 9);
        wiring.put(11, 18);
        wiring.put(12, 15);
        wiring.put(13, 3);
        wiring.put(14, 25);
        wiring.put(15, 17);
        wiring.put(16, 0);
        wiring.put(17, 12);
        wiring.put(18, 4);
        wiring.put(19, 22);
        wiring.put(20, 13);
        wiring.put(21, 8);
        wiring.put(22, 20);
        wiring.put(23, 24);
        wiring.put(24, 6);
        wiring.put(25, 21);
        return new Rotor(label, notches, wiring);
    }

    public static Rotor Rotors(Integer rotor_type) {
        if(rotor_type > 8 || rotor_type < 1) {
            throw new Exception("Could not find Rotor of type '"+rotor_type+"'");
        }
        switch(rotor_type) {
            case 2:
                return Rotor_2();
            case 3:
                return Rotor_3();
            case 4:
                return Rotor_4();
            case 5:
                return Rotor_5();
            case 6:
                return Rotor_6();
            case 7:
                return Rotor_7();
            case 8:
                return Rotor_8();
            default:
                return Rotor_1();
        }
    }
}