package homework;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {

    List<Product> list = new ArrayList<>();
    list.add(new Product(1010, "book", 50.00f, true,
        LocalDateTime.of(2022, 10, 20, 12, 0)));
    list.add(new Product(1011, "book", 60.00f, true,
        LocalDateTime.of(2022, 10, 20, 12, 5)));
    list.add(new Product(1012, "magazine", 65.00f, true,
        LocalDateTime.of(2022, 10, 20, 12, 10)));
    list.add(new Product(1013, "journal", 65.00f, true,
        LocalDateTime.of(2022, 10, 20, 12, 15)));
    list.add(new Product(1014, "book", 300.00f, false,
        LocalDateTime.of(2022, 10, 20, 12, 20)));
    list.add(new Product(1015, "journal", 70.00f, true,
        LocalDateTime.of(2022, 10, 20, 12, 25)));
    list.add(new Product(1016, "magazine", 350.00f, false,
        LocalDateTime.of(2022, 10, 20, 12, 30)));

    // Метод получения всех продуктов в виде списка, категория которых эквивалентна “Book” и цена более чем 250.
    System.out.println("TASK 1");
    float priceValue = 250.00f;
    String categorySearch = "book";
    List<Product> collectionTask1 = list.stream()
        .filter(a -> a.getType().equals(categorySearch))
        .filter(a -> a.getPrice() >= priceValue)
        .collect(Collectors.toList());
    System.out.println(collectionTask1);
    System.out.println("");


    /* Метод получения всех продуктов в виде списка, категория которых эквивалентна “Book”
    и с возможностью применения скидки. Финальный список должен содержать продукты с уже
    примененной скидкой 10%.*/
    System.out.println("TASK 2");
    int discountValue = 10;
    String categoryDiscount = "book";
    List<Product> collectionTask2 = list.stream()
        .filter(a -> a.getType().equals(categoryDiscount))
        .filter(Product::isDiscount)
        .peek(a -> a.discount(discountValue))
        .collect(Collectors.toList());
    System.out.println(collectionTask2);
    System.out.println("");

    /* Метод получения самого дешевого продукта из категории “Book”.
    В случае, если ни один продукт не найден (ситуация, когда нет продукта с искомой категорией),
    выбросить исключение с сообщением “Продукт [категория: имя_категории] не найден”. */
    System.out.println("TASK 3");
    try {
      String category = "journal";
      if (list.stream().noneMatch(a -> a.getType().equals(category))) {
        throw new NotFoundException("Product of looked category [" + category + "] is not found");
      }
      Optional<Product> collectionTask31 = list.stream()
          .filter(a -> a.getType().equals(category))
          .min(Comparator.comparing(Product::getPrice));
      System.out.println(collectionTask31);
    } catch (NotFoundException e) {
      System.out.println(e.getMessage());
    }
    System.out.println("");

    //метод получения трех последних добавленных продуктов
    System.out.println("TASK 4");
    int level = 3;
    List<Product> collectionTask4 = list.stream()
        .sorted(Comparator.comparing(Product::getDateAdding).reversed())
        .limit(level)
        .toList();
    System.out.println(collectionTask4);
    System.out.println("");

    //метод калькуляции общей стоимости продуктов, которые отвечаю следующим критериям
    System.out.println("TASK 5");
    String categorySum = "book";
    float categoryPrice = 75.00f;
    int categoryYear = 2021;
    Optional<Float> collectionTask5 = Optional.of(list.stream()
        .filter(a -> a.getDateAdding().getYear() > categoryYear)
        .filter(a -> a.getType().equals(categorySum))
        .map(Product::getPrice)
        .filter(price -> price >= categoryPrice)
        .reduce(Float::sum).orElse(0f));
    System.out.println(collectionTask5);
    System.out.println("");

    /* Метод группирования объектов по типу продукта. Таким образом результатом выполнения
    метода будет тип данных “Словарь” хранящий пару ключ-значение: {тип: список_продуктов}.*/
    System.out.println("TASK 6");
    Map<String, List<Product>> collectionTask6 = new HashMap<>();
    list.stream()
        .map(Product::getType)
        .distinct()
        .forEach(key -> collectionTask6.put(key, list.stream()
            .filter(a -> a.getType().equals(key))
            .collect(Collectors.toList())));
    System.out.println(collectionTask6);
  }
}
