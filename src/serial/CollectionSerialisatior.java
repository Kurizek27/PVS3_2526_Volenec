package serial;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CollectionSerialisatior {
    public static void main(String[] args) {
        String path = "data/listOfPoints.ser";
        List<Point> points = new ArrayList<Point>();
        Random rng = new Random();
        for (int i = 0; i < 100_000; i++) {
            points.add(new Point(rng.nextInt(), rng.nextInt()));
        }

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(points);
            oos.close();


            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            List<Point> deserialisedList = (List<Point>) ois.readObject();
            System.out.println(deserialisedList.size());
            System.out.println(points.size());
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
