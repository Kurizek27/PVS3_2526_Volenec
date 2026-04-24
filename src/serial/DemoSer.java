package serial;

import java.io.*;

public class DemoSer {
    public static void main(String[] args) {
        String serializedPoints = "data/points.ser";
        Point a = new Point(1, 2);
        Point b = new Point(6, 4);
        Point c = new Point(9, 8);

        System.out.println(a);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serializedPoints));

            oos.writeObject(a);
            oos.writeObject(b);
            oos.writeObject(c);

            oos.close();



            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serializedPoints));

            Point aCopy = (Point) ois.readObject();
            Point bCopy = (Point) ois.readObject();
            Point cCopy = (Point) ois.readObject();

            System.out.println(aCopy);
            System.out.println(bCopy);
            System.out.println(cCopy);

            ois.close();

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
        }
    }
}

class Point implements Serializable {
    @Serial
    private static final long serialVersionUid = 1;
    int x;
    transient int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
