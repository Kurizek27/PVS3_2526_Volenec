package filing;

import java.io.File;

public class test {
    static void tree(File root){
        if (!root.exists()){
            return;
        }
        if (!root.isDirectory()){
            System.out.println(root.getPath());
        } else {
            System.out.println(root.getPath());
            File[] content = root.listFiles();
            for(File file : content){
                tree(file);
            }
        }
    }

    public static void main(String[] args) {
        File f = new File("data/tracks.txt");
        tree(new File("data"));

    }
}
