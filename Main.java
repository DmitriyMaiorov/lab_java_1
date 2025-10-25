import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library myLibrary = new Library();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== МЕНЮ БИБЛИОТЕКИ ===");
            System.out.println("1. Добавить книгу");
            System.out.println("2. Показать все книги");
            System.out.println("3. Найти книгу");
            System.out.println("4. Редактировать книгу");
            System.out.println("5. Удалить книгу");
            System.out.println("6. Сохранить в файл");
            System.out.println("7. Загрузить из файла");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            String option = input.nextLine();

            switch (option) {
                case "1" -> {
                    System.out.print("Введите название: ");
                    String name = input.nextLine();
                    System.out.print("Введите автора: ");
                    String writer = input.nextLine();
                    System.out.print("Введите год: ");
                    int year = Library.getValidYear(input);
                    myLibrary.addBook(new Book(name, writer, year));
                }
                case "2" -> myLibrary.showBooks();
                case "3" -> {
                    System.out.println("\nПоиск книги по критерию:");
                    System.out.println("1. Название");
                    System.out.println("2. Автор");
                    System.out.println("3. Год");
                    System.out.print("Ваш выбор: ");
                    String search = input.nextLine();

                    switch (search) {
                        case "1" -> {
                            System.out.print("Введите название: ");
                            myLibrary.findBookByName(input.nextLine());
                        }
                        case "2" -> {
                            System.out.print("Введите автора: ");
                            myLibrary.findBookByWriter(input.nextLine());
                        }
                        case "3" -> {
                            System.out.print("Введите год: ");
                            int y = Library.getValidYear(input);
                            myLibrary.findBookByYear(y);
                        }
                        default -> System.out.println("Неверный выбор.");
                    }
                }
                case "4" -> {
                    System.out.print("Введите название книги для редактирования: ");
                    myLibrary.editBook(input.nextLine(), input);
                }
                case "5" -> {
                    System.out.print("Введите название книги для удаления: ");
                    myLibrary.removeBook(input.nextLine());
                }
                case "6" -> {
                    System.out.print("Введите имя файла: ");
                    myLibrary.saveToFile(input.nextLine());
                }
                case "7" -> {
                    System.out.print("Введите имя файла: ");
                    myLibrary.loadFromFile(input.nextLine());
                }
                case "0" -> {
                    System.out.println("Выход...");
                    input.close();
                    return;
                }
                default -> System.out.println("Некорректный ввод, попробуйте снова.");
            }
        }
    }
}
