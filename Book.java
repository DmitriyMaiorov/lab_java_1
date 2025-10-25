// Класс, описывающий книгу
public class Book {
    private String name;
    private String writer;
    private int publishYear;

    // Конструктор для создания книги
    public Book(String name, String writer, int publishYear) {
        this.name = name;
        this.writer = writer;
        this.publishYear = publishYear;
    }

    // Геттеры и сеттеры (для доступа и изменения полей)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    // Вывод информации о книге(~табличный вид)
    @Override
    public String toString() {
        return String.format("Название: %-30s Автор: %-20s Год: %d", name, writer, publishYear);
    }
}
