import java.util.*;

class Laptop {
    private int ram;
    private int storage;
    private String os;
    private String color;

    public Laptop(int ram, int storage, String os, String color) {
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "ram=" + ram +
                ", storage=" + storage +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

public class Main {
    public static void main(String[] args) {
        // Создаем множество ноутбуков
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop(16, 512, "Windows", "Черный"));
        laptops.add(new Laptop(8, 256, "macOS", "Серый"));
        laptops.add(new Laptop(32, 1024, "Linux", "Белый"));
        laptops.add(new Laptop(12, 512, "Windows", "Синий"));
        laptops.add(new Laptop(8, 256, "Windows", "Серебристый"));

        // Запускаем фильтрацию
        filterLaptops(laptops);
    }

    private static void filterLaptops(Set<Laptop> laptops) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> filterCriteria = new HashMap<>();

        while (true) {
            System.out.println("Введите цифру, соответствующую необходимому критерию:");
            System.out.println("1 - ОЗУ");
            System.out.println("2 - Объем ЖД");
            System.out.println("3 - Операционная система");
            System.out.println("4 - Цвет");
            System.out.println("5 - Завершить фильтрацию");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Считываем перевод строки

            if (choice == 5) {
                break;
            }

            String criteria = "";
            switch (choice) {
                case 1:
                    criteria = "ram";
                    break;
                case 2:
                    criteria = "storage";
                    break;
                case 3:
                    criteria = "os";
                    break;
                case 4:
                    criteria = "color";
                    break;
            }
            System.out.println("Введите минимальное значение для " + criteria + ":");
            int minValue = scanner.nextInt();
            scanner.nextLine(); // Считываем перевод строки

            filterCriteria.put(criteria, minValue);

            System.out.println("Добавить еще один критерий? (да/нет)");
            String addCriteria = scanner.nextLine();

            if (!addCriteria.equalsIgnoreCase("да")) {
                break;
            }
        }

        // Выводим отфильтрованные ноутбуки
        System.out.println("Отфильтрованные ноутбуки:");
        for (Laptop laptop : laptops) {
            if (filterCriteria.isEmpty()) {
                System.out.println(laptop);
            } else {
                boolean matchesAllCriteria = true;
                for (Map.Entry<String, Integer> entry : filterCriteria.entrySet()) {
                    String key = entry.getKey();
                    int value = entry.getValue();
                    switch (key) {
                        case "ram":
                            if (laptop.getRam() < value) {
                                matchesAllCriteria = false;
                            }
                            break;
                        case "storage":
                            if (laptop.getStorage() < value) {
                                matchesAllCriteria = false;
                            }
                            break;
                        case "os":
                            if (!laptop.getOs().equalsIgnoreCase(value + "")) {
                                matchesAllCriteria = false;
                            }
                            break;
                        case "color":
                            if (!laptop.getColor().equalsIgnoreCase(value + "")) {
                                matchesAllCriteria = false;
                            }
                            break;
                    }
                }
                if (matchesAllCriteria) {
                    System.out.println(laptop);
                }
            }
        }
    }
}
