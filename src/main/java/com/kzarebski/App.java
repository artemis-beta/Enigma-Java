package com.kzarebski;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public final class App {
    public String getVersion()
    {
        String path = "/version.prop";
        InputStream stream = getClass().getResourceAsStream(path);
        if (stream == null)
            return "UNKNOWN";
        Properties props = new Properties();
        try {
            props.load(stream);
            stream.close();
            return (String) props.get("version");
        } catch (IOException e) {
            return "UNKNOWN";
        }
    }
    private App() {
        String intro = "===========================================\n\n"+
        "  WELCOME TO THE ENIGMA ENCODER\n"+
        StringUtils.repeat(" ", 18-getVersion().length()/2)+getVersion();
        intro += "\n        Kristian Zarebski\n\n" +
        "===========================================\n" +
        "Type 'quit' to exit.\n\n";
        System.out.println(intro);
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}
