package dataSensors;

import java.io.*;
import java.util.ArrayList;

public class SensorTask {
    static ArrayList<Reading> validReadings = new ArrayList<>();
    static ArrayList<InvalidReading> invalidReadings = new ArrayList<>();

    static void reportReadings(String dirPath) throws IOException {
        File dir = new File(dirPath);
        String line;
        for (File currentfile : dir.listFiles() ) {
            if (!dir.exists()) {
                return;
            }
            try {
                if(currentfile.isDirectory()){
                    return;
                }
                BufferedReader br = new BufferedReader(new FileReader(currentfile));
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(" ");
                    Double data = Double.valueOf(parts[1]);
                    if (data>=-200.0&&data<=500.0){
                       // validReadings.add(parts[0], parts[1]);
                    } else {
                     //   invalidReadings.add(currentfile, parts[1]);
                    }
                }
                br.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        try {
            reportReadings("src/dataSensors/dataSensors");
        } catch (IOException e){
            System.out.println("Problem reading the directory:");
            System.out.println(e.getMessage());
        }
    }

}
class Reading{
    String sensorID;
    int measuredValue;

    public Reading(String sensorID, int measuredValue) {
        this.sensorID = sensorID;
        this.measuredValue = measuredValue;
    }

    @Override
    public String toString() {
        return sensorID + ": " + measuredValue;
    }
}
class InvalidReading{
    String fileName;
    int lineNumber;
    Reading reading;

    @Override
    public String toString() {
        return "InvalidReading{" +
                "fileName='" + fileName + '\'' +
                ", lineNumber=" + lineNumber +
                ", Value=" + reading +
                '}';
    }
}
