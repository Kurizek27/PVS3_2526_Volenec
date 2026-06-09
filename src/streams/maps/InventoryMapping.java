package streams.maps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;

public class InventoryMapping {

    static InventoryItem parseLine(String[] token) {
        if (token.length == 2) return new InventoryItem(token[0], Integer.parseInt(token[1]));
        if (token.length == 3)
            return new InventoryItem(token[0], Integer.parseInt(token[1]), Integer.parseInt(token[2]));
        // špatný počet argumentů
        return null;
    }

    public static void main(String[] args) throws IOException {
        Map<String, InventoryItem> mapByName = Files.lines(Path.of("data/inventory.txt"))
                .map(line -> line.trim().split(";"))
                .map(InventoryMapping::parseLine)
                .collect(Collectors.toMap(
                        InventoryItem::getName,
                        item -> item
                ));


        Files.lines(Path.of("data/operations.txt"))
                .map(line -> line.trim().split(";"))
                .forEach(parts -> {
                            InventoryItem item = mapByName.get(parts[1]);
                            if(item == null) return;

                            item.processOrder(parts[0], Integer.parseInt(parts[2]));
                        }
                );

        System.out.println("Valid: " + InventoryItem.validOperations);
        System.out.println("Invalid: " + InventoryItem.invalidOperations);

        // Každý item -> počet
        mapByName.forEach((type, item) -> System.out.println(type + " -> " + item.getQty()));
    }



}

class InventoryItem {
    private String name;
    private int qty;
    private int maxQty;

    static int invalidOperations = 0;
    static int validOperations = 0;

    public InventoryItem(String name, int qty) {
        this.name = name;
        this.qty = qty;
        maxQty = Integer.MAX_VALUE;
    }

    public InventoryItem(String name, int qty, int maxQty) {
        this.name = name;
        this.qty = qty;
        this.maxQty = maxQty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getMaxQty() {
        return maxQty;
    }

    public void setMaxQty(int maxQty) {
        this.maxQty = maxQty;
    }

    public void processOrder(String type, int qty){
        // ADD, REMOVE
        //  Tim 5
        if(qty < 0) {
            System.out.println("quantity has to be greater then 0.");
            invalidOperations++;
            return;
        }

        if(type.equals("ADD") && (this.qty + qty) > maxQty){
            System.out.println("Over the max qty of an item.");
            invalidOperations++;
            return;
        }

        if(type.equals("REMOVE") && (this.qty - qty) < 0){
            System.out.println("Not enough items to complete operation.");
            invalidOperations++;
            return;
        }

        if(type.equals("ADD")) {
            this.qty += qty;
            validOperations++;
        }

        if(type.equals("REMOVE")) {
            this.qty -= qty;
            validOperations++;
        }
    }
}