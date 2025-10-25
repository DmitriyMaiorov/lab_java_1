import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

// Класс для управления списком книг
public class Library {
    private final ArrayList<Book> libraryBooks = new ArrayList<>();

    // Добавление книги
    public void addBook(Book newBook) {
        libraryBooks.add(newBook);
        System.out.println("Книга успешно добавлена!");
    }

    // Показ всех книг из списка
    public void showBooks() {
        if (libraryBooks.isEmpty()) {
            System.out.println("Библиотека пуста.");
            return;
        }

        System.out.printf("%-5s %-30s %-20s %-5s%n", "№", "Название", "Автор", "Год");
        System.out.println("--------------------------------------------------------------");
        for (int i = 0; i < libraryBooks.size(); i++) {
            Book currentBook = libraryBooks.get(i);
            System.out.printf("%-5d %-30s %-20s %-5d%n", i + 1,
                    currentBook.getName(), currentBook.getWriter(), currentBook.getPublishYear());
        }
    }

    // Поиск книги по названию
    public void findBookByName(String name) {
        boolean found = false;
        for (Book b : libraryBooks) {
            if (b.getName().equalsIgnoreCase(name)) {
                System.out.println("Найдена книга: " + b);
                found = true;
            }
        }
        if (!found) System.out.println("Книга с названием \"" + name + "\" не найдена.");
    }

    // Поиск по автору
    public void findBookByWriter(String writer) {
        boolean found = false;
        for (Book b : libraryBooks) {
            if (b.getWriter().equalsIgnoreCase(writer)) {
                System.out.println("Найдена книга: " + b);
                found = true;
            }
        }
        if (!found) System.out.println("Книги автора \"" + writer + "\" не найдены.");
    }

    // Поиск по году
    public void findBookByYear(int year) {
        boolean found = false;
        for (Book b : libraryBooks) {
            if (b.getPublishYear() == year) {
                System.out.println("Найдена книга: " + b);
                found = true;
            }
        }
        if (!found) System.out.println("Книги за " + year + " год не найдены.");
    }

    // Сохранение списка книг в файл
    public void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(String.format("%-5s %-30s %-20s %-5s%n", "№", "Название", "Автор", "Год"));
            writer.write("--------------------------------------------------------------\n");

            for (int i = 0; i < libraryBooks.size(); i++) {
                Book b = libraryBooks.get(i);
                writer.write(String.format("%-5d %-30s %-20s %-5d%n",
                        i + 1, b.getName(), b.getWriter(), b.getPublishYear()));
            }

            System.out.println("Книги сохранены в файл: " + fileName);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении: " + e.getMessage());
        }
    }

    // Загрузка списка книг из файла
    public void loadFromFile(String fileName) {
        libraryBooks.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine();
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.trim().split("\\s{2,}");
                if (parts.length >= 4) {
                    String name = parts[1].trim();
                    String writer = parts[2].trim();
                    int year = Integer.parseInt(parts[3].trim());
                    libraryBooks.add(new Book(name, writer, year));
                }
            }

            System.out.println("Книги успешно загружены из файла: " + fileName);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден. Создайте новый список.");
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    // Редактирование информации о книге
    public void editBook(String name, Scanner scanner) {
        for (Book b : libraryBooks) {
            if (b.getName().equalsIgnoreCase(name)) {
                System.out.println("Найдена книга: " + b);

                System.out.print("Новое название (Enter — без изменений): ");
                String newName = scanner.nextLine();
                if (!newName.isEmpty()) b.setName(newName);

                System.out.print("Новый автор (Enter — без изменений): ");
                String newWriter = scanner.nextLine();
                if (!newWriter.isEmpty()) b.setWriter(newWriter);

                Integer newYear = getOptionalYear(scanner, "Новый год (Enter — без изменений): ");
                if (newYear != null) {
                    b.setPublishYear(newYear);
                }

                System.out.println("Информация о книге обновлена!");
                return;
            }
        }
        System.out.println("Книга не найдена.");
    }

    // Удаление книги
    public void removeBook(String name) {
        for (int i = 0; i < libraryBooks.size(); i++) {
            if (libraryBooks.get(i).getName().equalsIgnoreCase(name)) {
                libraryBooks.remove(i);
                System.out.println("Книга \"" + name + "\" удалена.");
                return;
            }
        }
        System.out.println("Книга не найдена.");
    }

    // Проверка, что пользователь ввёл число
    public static int getValidYear(Scanner scanner) {
        while (true) {
            try {
                int year = Integer.parseInt(scanner.nextLine().trim());
                if (year >= 0) return year;
                System.out.print("Введите положительный год: ");
            } catch (NumberFormatException e) {
                System.out.print("Ошибка! Введите число: ");
            }
        }
    }

    // Проверка года с возможностью пропуска (для опции изменения данных о книге)
    public static Integer getOptionalYear(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) return null;

            try {
                int year = Integer.parseInt(input);
                if (year > 0) return year;
                System.out.println("Год должен быть положительным числом.");
            } catch (NumberFormatException e) {
                System.out.println("Ошибка! Введите число или просто нажмите Enter для пропуска.");
            }
        }
    }
}
