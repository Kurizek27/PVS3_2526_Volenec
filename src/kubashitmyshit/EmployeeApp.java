package kubashitmyshit;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

// ==========================================
// ROZHRANÍ (Interfaces)
// ==========================================
// Rozhraní definuje "co" má objekt umět, ale ne "jak" (to řeší až implementace).
// Lze implementovat více rozhraní najednou, na rozdíl od dědičnosti tříd.
interface Workable {
    void doWork();
}

interface Identifiable {
    int getId();
}

// ==========================================
// GENERIKA (Generics)
// ==========================================
// Generika umožňují napsat třídu nebo metodu pro obecný typ (T).
// "T extends Identifiable & Serializable" znamená, že přijmeme jakýkoliv typ,
// který implementuje obě tato rozhraní. Zvyšuje to typovou bezpečnost.
class DataStore<T extends Identifiable & Serializable> implements Serializable {
    // Mapy: Ideální pro rychlé vyhledávání pomocí klíče (Zde ID -> Objekt)
    private Map<Integer, T> map = new HashMap<>();

    public void add(T item) {
        map.put(item.getId(), item);
    }

    public T get(int id) {
        return map.get(id);
    }

    public List<T> getAll() {
        return new ArrayList<>(map.values());
    }
}

// ==========================================
// OOP: ZÁKLADNÍ TŘÍDA (Zapouzdření, Abstraktní třída)
// ==========================================
// abstract = nelze vytvořit přímo instanci Person, musíme použít potomky.
// implements Comparable<Person> = umožňuje snadné řazení pomocí Collections.sort()
abstract class Person implements Comparable<Person>, Serializable, Identifiable {
    // Zapouzdření (Encapsulation): Proměnné jsou private/protected,
    // manipulace s nimi probíhá přes public get/set metody.
    protected int id; // protected = viditelné pro potomky
    private String name;
    private LocalDate hireDate; // Práce s datem (od Javy 8)

    // Konstruktory a přetěžování (Overloading)
    // 1. Konstruktor
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
        this.hireDate = LocalDate.now(); // defaultní hodnota
    }

    // 2. Přetížený konstruktor (využívá 'this' k volání prvního konstruktoru)
    public Person(int id, String name, LocalDate hireDate) {
        this(id, name); // Volání jiného konstruktoru ve stejné třídě
        this.hireDate = hireDate;
    }

    // Gettery a Settery
    @Override
    public int getId() { return id; }
    public String getName() { return name; }
    public LocalDate getHireDate() { return hireDate; }

    // equals() a hashCode() - Nutné pro správné fungování v kolekcích jako Set nebo Map
    // Porovnáváme objekty podle logické shody (zde ID), ne podle místa v paměti.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // toString() - textová reprezentace objektu (použije se např. při System.out.println)
    @Override
    public String toString() {
        return String.format("[%d] %s (Nástup: %s)", id, name, hireDate);
    }

    // CompareTo - implementace rozhraní Comparable. Určuje výchozí řazení (podle ID).
    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.id, other.id);
    }
}

// ==========================================
// DĚDIČNOST (Inheritance)
// ==========================================
// 'extends Person' znamená, že Employee dědí všechny vlastnosti Person.
// 'implements Workable' znamená, že navíc splňuje kontrakt rozhraní Workable.
class Employee extends Person implements Workable {
    private double salary;

    public Employee(int id, String name, LocalDate hireDate, double salary) {
        super(id, name, hireDate); // 'super' volá konstruktor předka (Person)
        this.salary = salary;
    }

    public double getSalary() { return salary; }

    // Přepisování metod (Overriding) - měníme chování zděděné metody
    @Override
    public void doWork() {
        System.out.println(getName() + " pracuje na běžných úkolech.");
    }

    @Override
    public String toString() {
        // Použití super.toString() pro získání výpisu předka a připojení specifických dat
        return super.toString() + " | Plat: " + salary;
    }
}

// ==========================================
// HLAVNÍ APLIKACE A SOUBORY
// ==========================================
public class EmployeeApp {
    // 'final' = konstanta, nelze ji po inicializaci změnit
    // 'static' = patří třídě, ne instanci, volá se bez vytváření objektu
    private static final String CSV_FILE = "employees.csv";
    private static final String SER_FILE = "data.ser";

    // Kolekce a Generika
    private static DataStore<Employee> store = new DataStore<>();

    public static void main(String[] args) {
        createDummyCsvIfNotExists(); // Příprava dat
        loadFromCsv(); // Práce se souborem - čtení

        Scanner scanner = new Scanner(System.in); // userInput
        boolean running = true;

        System.out.println("--- Správa zaměstnanců ---");

        while (running) {
            System.out.println("\n1. Zobrazit všechny (seřazené)");
            System.out.println("2. Zobrazit unikatní roky nástupu (Set)");
            System.out.println("3. Zpracovat frontu úkolů (Queue)");
            System.out.println("4. Filtrovat s platem > 50000 (Stream)");
            System.out.println("5. Info o souborech (File API)");
            System.out.println("6. Uložit stav aplikace (Serializace)");
            System.out.println("0. Konec");
            System.out.print("Vyber možnost: ");

            int choice = -1;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Neplatný vstup, zadej číslo.");
                continue;
            }

            switch (choice) {
                case 1:
                    List<Employee> list = store.getAll();
                    // ZÁKLADY: sort (už existující)
                    Collections.sort(list); // Použije compareTo() definované v Person

                    // Anonymní vnitřní třída (starý způsob, zřídka se používá místo lambdy)
                    // Ukázka čistě pro splnění požadavku.
                    list.sort(new Comparator<Employee>() {
                        @Override
                        public int compare(Employee e1, Employee e2) {
                            return e1.getName().compareTo(e2.getName());
                        }
                    });

                    // Lambda: Moderní a čistý způsob (seřadí primárně podle jména)
                    list.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));

                    for (Employee e : list) {
                        System.out.println(e);
                    }
                    break;

                case 2:
                    // KOLEKCE: Set (pro unikáty). Neumožňuje duplicity.
                    Set<Integer> uniqueYears = new HashSet<>();
                    for (Employee e : store.getAll()) {
                        uniqueYears.add(e.getHireDate().getYear());
                    }
                    System.out.println("Roky, kdy někdo nastoupil (bez duplicit): " + uniqueYears);
                    break;

                case 3:
                    // KOLEKCE: Queue (Fronta FIFO - First In, First Out)
                    // Queue sama o sobě je interface, používáme existující implementaci LinkedList
                    Queue<Employee> taskQueue = new LinkedList<>(store.getAll());
                    while (!taskQueue.isEmpty()) {
                        Employee e = taskQueue.poll(); // odebere a vrátí prvek
                        e.doWork(); // Polymorfismus a volání rozhraní
                    }
                    break;

                case 4:
                    // KOLEKCE: Stream kolekcí
                    // Umožňuje elegantní řetězení operací: filtrování, mapování, sběr dat
                    List<Employee> richEmployees = store.getAll().stream()
                            .filter(e -> e.getSalary() > 50000) // Lambda pro filtr
                            .collect(Collectors.toList());

                    System.out.println("Zaměstnanci s platem > 50k:");
                    richEmployees.forEach(System.out::println);
                    break;

                case 5:
                    // SOUBORY: třída File - info a práce s adresáři
                    File currentDir = new File(".");
                    System.out.println("Současný adresář: " + currentDir.getAbsolutePath());
                    File csv = new File(CSV_FILE);
                    if (csv.exists()) {
                        System.out.println("Soubor " + CSV_FILE + " má velikost: " + csv.length() + " bajtů.");
                    }

                    // Základní operace s polem - listFiles vrací pole souborů
                    File[] files = currentDir.listFiles();
                    if (files != null) {
                        System.out.println("Počet souborů ve složce: " + files.length);
                    }
                    break;

                case 6:
                    serializeData();
                    break;

                case 0:
                    running = false;
                    System.out.println("Konec.");
                    break;

                default:
                    System.out.println("Neznámá volba.");
            }
        }
        scanner.close();
    }

    // ==========================================
    // ČTENÍ SOUBORU - PO ŘÁDCÍCH
    // ==========================================
    private static void loadFromCsv() {
        // Zkoušíme číst v bloku try-with-resources (automaticky zavírá soubor)
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while ((line = br.readLine()) != null) {
                // Základní operace s polem: rozdělení stringu do pole
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    LocalDate date = LocalDate.parse(parts[2], formatter);
                    double salary = Double.parseDouble(parts[3]);

                    Employee emp = new Employee(id, name, date, salary);
                    store.add(emp);
                }
            }
            System.out.println("Data z CSV načtena.");
        } catch (IOException e) {
            System.out.println("Chyba čtení CSV: " + e.getMessage());
        }
    }

    // ==========================================
    // PSANÍ DO SOUBORU (Příprava Dummy dat)
    // ==========================================
    private static void createDummyCsvIfNotExists() {
        File file = new File(CSV_FILE);
        if (!file.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                bw.write("1,Karel Novak,2020-05-12,45000\n");
                bw.write("2,Jana Stara,2019-11-01,65000\n");
                bw.write("3,Petr Maly,2022-01-15,35000\n");
                bw.write("4,Eva Velka,2020-05-12,55000\n"); // Stejný rok pro demonstraci Setu
            } catch (IOException e) {
                System.out.println("Nelze vytvořit CSV: " + e.getMessage());
            }
        }
    }

    // ==========================================
    // SERIALIZACE
    // ==========================================
    private static void serializeData() {
        // ObjectOutputStream převede objekt do bajtů a FileOutputStream je zapíše do souboru
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SER_FILE))) {
            oos.writeObject(store); // Třída DataStore i Employee musí implementovat Serializable!
            System.out.println("Stav aplikace úspěšně serializován do: " + SER_FILE);
        } catch (IOException e) {
            System.out.println("Chyba při serializaci: " + e.getMessage());
        }
    }
}

/*
Základy a OOP: Atributy id, name jsou chráněné před přímým zásahem (Zapouzdření/Encapsulation). Třída Person implementuje equals, hashCode (aby správně fungovaly Mapy a Sety) a toString pro hezký výpis. Konstanta CSV_FILE je definována klíčovým slovem final – jakmile se zkompiluje, její adresa se už nemění.

Dědičnost a Super: Třída Employee "nabaluje" nové vlastnosti na třídu Person. K volání logiky předka používá super(id, name, hireDate) a v metodě toString() ukazuje přepisování (@Override), kde vezme výsledek z předka super.toString() a přidá vlastní text.

Rozhraní, Lambda a Anonymní třídy: Pod case 1 najdeš ukázku Comparator. Místo zbytečného vytváření celého nového souboru s třídou je použit moderní Lambda výraz (e1, e2) -> .... V kódu je i ukázka (komentovaná) anonymní vnitřní třídy new Comparator() {...} – funguje to stejně, ale je to zápisově delší a dnes se upřednostňuje Lambda.

Generika: Naše kontejnerová třída DataStore<T> přijímá typ T. Tím kompilátoru říkáme: "Nevíme předem, co to bude, ale musí to implementovat Identifiable a Serializable". Kdykoliv pak vytváříme instanci, doplníme ostrou třídu, v našem případě new DataStore<Employee>().

Streamy: V case 4 store.getAll().stream().filter(...) probíhá transformace dat. Místo abychom psali for cyklus, zakládali nový prázdný List a používali if, vyřeší se celá logika v rámci jedné deklarativní řady příkazů. Je to bezpečnější a mnohem čitelnější.
 */