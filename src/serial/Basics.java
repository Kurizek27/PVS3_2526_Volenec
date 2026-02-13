package serial;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Basics {
    public static void main(String[] args) {
        Coords coords = new Coords(1,2,3);
        System.out.println(coords);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/serial/coords.ser"));
            oos.writeObject(coords);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Coords implements Serializable {
    int x,y,z;
    private static final long serialVersionUID = 1L;

    public Coords(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "Coords[" + x + y +  z +"]";
    }
}