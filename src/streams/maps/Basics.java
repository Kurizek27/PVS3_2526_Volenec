package streams.maps;

import java.util.HashMap;

public class Basics {
    public static void main(String[] args) {
            HashMap<String, String> capitals = new HashMap<>();
        capitals.put("Czech Republic", "Prague");
        capitals.put("Germany", "Berlin");
        capitals.put("Austria", "Vienna");

        System.out.println(capitals.get("Germany"));

        System.out.println(capitals);

        //projet vse
        for (String country : capitals.keySet()){
            System.out.println(capitals.get(country));
        }

        capitals.remove("Germany");

        System.out.println(capitals);
        //uz tam jednou je
        capitals.put("Austria", "Sydney");
        System.out.println(capitals);

        //zpetne napravim - a slusne:
        capitals.replace("Austria", "Vienna");


    }
}
