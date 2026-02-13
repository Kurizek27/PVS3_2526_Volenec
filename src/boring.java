import java.io.*;

public class boring {
    public static void main(String[] args) {
        String path = "data/poetry";
        File f = new File(path);
        if (!f.isDirectory()) {
            return;
        }
        File[] files = f.listFiles();
            for (File file : files) {
                if (file.getName().endsWith(".txt")){
                    System.out.println(file.getName() + " " + file.length() + "B");
                }
            }
            for (File file : files) {
                if (file.getName().endsWith(".txt")){
                    try {
                        BufferedReader br = new BufferedReader(new FileReader(file));
                        String line;
                        while((line = br.readLine()) != null){
                            System.out.println(line);
                        }
                        br.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

    }
}
