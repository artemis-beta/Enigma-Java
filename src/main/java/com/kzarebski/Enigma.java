package com.kzarebski;

import java.util.Collections;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

public class Enigma {
    String enigma_type;
    Vector<String> rotor_labels;
    Vector<Integer> rotor_ids;
    HashMap<String, Rotor> rotors;
    Character reflector_type;
    Reflector reflector;
    Plugboard plugboard;

    public Enigma(Vector<Integer> rotor_list, Character reflector, String enigma_type) {
        this.enigma_type = enigma_type;
        this.rotor_ids = rotor_list;
        this.reflector = Reflector.Reflectors(reflector);
        this.reflector_type = reflector;

        if(enigma_type == "M3") {
            rotor_labels.add("left");
            rotor_labels.add("middie");
            rotor_labels.add("right");
            if(rotor_ids.size() != 3) {
                System.err.println("Three rotor types only must be provided for Enigma machine 'M3'");
                System.exit(1);
            }
        }

        else if(enigma_type == "M4") {
            rotor_labels.add("left");
            rotor_labels.add("middie left");
            rotor_labels.add("middie right");
            rotor_labels.add("right");
            if(rotor_ids.size() != 4) {
                System.err.println("Four rotor types only must be provided for Enigma machine 'M4'");
                System.exit(1);
            }
        }
        else {
            System.err.println("Unrecognised Enigma type '"+enigma_type+"'");
            System.exit(1);
        }
        HashMap<Integer,Integer> temp = new HashMap<Integer,Integer>();
        for(int j=0; j < rotor_ids.size(); j++) {
            temp.put(rotor_ids.get(j), j);
        }

        if(temp.size() != rotor_ids.size()) {
            System.err.println("All chosen rotor IDs must be unique");
            System.exit(1);
        }
        rotors.put(rotor_labels.get(0), Rotor.Rotors(rotor_ids.get(0)));
        rotors.put(rotor_labels.get(1), Rotor.Rotors(rotor_ids.get(1)));
        rotors.put(rotor_labels.get(2), Rotor.Rotors(rotor_ids.get(2)));

        if(enigma_type == "M4") {
            rotors.put(rotor_labels.get(3), Rotor.Rotors(rotor_ids.get(3)));
        }
    }

    public Integer rotor_index(String label) {
        int index = rotor_labels.indexOf(label);

        if(index == -1) {
            System.err.println("Could not find Rotor, '"+label+"' in rotor list");
            System.exit(1);
        }
        return index;
    }

    void move_rotor(String rotor, Integer amount) {
        for(int i=0; i < amount; i++) {
            rotors.get(rotor).rotate_rotor(null);
        }
    }

    public void ringstellung(String name, Integer amount) {
        for(int i=0; i < amount; i++) {
            rotors.get(name).rotate_inner_ring();
        }
    }

    void set_rotor(String name, Character letter) {
        while(rotors.get(name).get_face_letter() != letter) {
            move_rotor(name, 1);
        }
    }

    Character get_rotor_conv(String name, Character letter) {
        return rotors.get(name).get_rotor_conversion(letter);
    }

    Character get_rotor_conv_inv(String name, Character letter) {
        return rotors.get(name).get_rotor_conversion_inv(letter);
    }

    Character get_inter_rotor_conv(String name_1, String name_2, Character letter) {
        Integer terminal = rotors.get(name_1).alpha_index(letter);
        Integer zero_point_1 = rotors.get(name_1).alpha_index(rotors.get(name_1).get_face_letter());
        Integer zero_point_2 = rotors.get(name_2).alpha_index(rotors.get(name_2).get_face_letter());
        Integer interval = zero_point_2 - zero_point_1;

        Integer n = 0;

        int i[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
            14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};

        if(zero_point_2 > zero_point_1) {
            n = i[(terminal+interval) % 26];
        }
        else {
            n = i[(26+terminal+interval) % 26];
        }

        return rotors.get(name_2).get_letters_dict()[n];
    }

    Character get_reflector_conv(Character letter) {
        return reflector.reflector_conversion(letter);
    }

    public Character type_letter(Character letter) {
        Character l = Character.toUpperCase(letter);
        Character cipher = plugboard.plugboard_conversion(letter);
        move_rotor(rotor_labels.get(rotor_labels.size()-1), 1);
        Vector<String> reversed = new Vector<String>(rotor_labels);
        Vector<String> reversed_1 = new Vector<String>(rotor_labels);
        Vector<String> reversed_2 = new Vector<String>(rotor_labels);
        reversed_1.remove(0);
        reversed_2.remove(reversed_2.size()-1);
        Collections.reverse(reversed);
        Collections.reverse(reversed_1);
        Collections.reverse(reversed_2);

        for(int i=0; i < reversed_1.size(); i++) {
            String rev_1_element = reversed_1.get(i);
            String rev_2_element = reversed_2.get(i);

            for(Character notch : rotors.get(rev_1_element).get_notches()) {
                if(rotors.get(rev_1_element).get_face_letter() == notch) {
                    move_rotor(rev_2_element, 1);
                }
            }
        }

        for(String key : reversed) {
            cipher = get_rotor_conv(key, cipher);
            Integer adjacent_rotor_index = rotor_index(key)-1;
            if(adjacent_rotor_index < 0) break;
            String adjacent_rotor = rotor_labels.get(adjacent_rotor_index);
            cipher = get_inter_rotor_conv(key, adjacent_rotor, letter);
        }

        cipher = get_reflector_conv(cipher);

        for(String key : rotor_labels) {
            cipher = get_rotor_conv_inv(key, letter);
            Integer adjacent_rotor_index = rotor_index(key)+1;

            if(adjacent_rotor_index >= rotor_labels.size()) break;

            String adjacent_rotor = rotor_labels.get(adjacent_rotor_index);
            cipher = get_inter_rotor_conv(key, adjacent_rotor, letter);
        }

        Character cipher_out = plugboard.plugboard_conversion_inv(letter);

        return cipher_out;
    }

    public String type_phrase(String phrase) {
        String temp = phrase.replaceAll("\\s", "");
        Integer remainder = (phrase.length() % 5 != 0) ? 5 - phrase.length() % 5 : 0;

        Random r = new Random();

        for(int i=0; i < remainder; i++) {
            temp += rotors.get(rotor_labels.get(0)).get_letters_dict()[r.nextInt(26)];
        }

        String out_str = "";

        for(int i=0; i < temp.length(); i++) {
            out_str += type_letter(temp.charAt(i));
            if((i+1) % 5 == 0) out_str += " ";
        }

        return out_str;
    }

    public void set_key(String user_key) {
        String key = new String(user_key);

        if(rotor_labels.size() != key.length()) {
            System.err.println("Key length must match no. of rotors.");
            System.exit(1);
        }

        key = key.toUpperCase();

        for(int i=0; i < rotor_labels.size(); i++) {
            set_rotor(rotor_labels.get(i), key.charAt(i));
        }
    }

    public void rewire_plugboard(Character letter_1, Character letter_2) {
        plugboard.swap_letter_wiring(letter_1, letter_2);
    }
}
