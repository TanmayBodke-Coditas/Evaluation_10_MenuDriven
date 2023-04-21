package Entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pubId;
    private String pubName;
    private LocalDate pubDate;
    private int price;


    @ManyToOne
    private Author author;

    @OneToOne
    private Book book;


    public int getPubId() {
        return pubId;
    }

    public void setPubId(int pubId) {
        this.pubId = pubId;
    }

    public String getPubName() {
        return pubName;
    }

    public void setPubName(String pubName) {
        this.pubName = pubName;
    }

    public LocalDate getPubDate() {
        return pubDate;
    }

    public void setPubDate(LocalDate pubDate) {
        this.pubDate = pubDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Publisher(String pubName, LocalDate pubDate, int price, Author author, Book book) {
        this.pubName = pubName;
        this.pubDate = pubDate;
        this.price = price;
        this.author = author;
        this.book = book;
    }

    public Publisher() {
    }
}
