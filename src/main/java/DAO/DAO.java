package DAO;

import Entity.Author;
import Entity.Book;
import Entity.Publisher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.xml.bind.attachment.AttachmentUnmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

public class DAO {
    static Configuration cfg = new Configuration().configure();
    static SessionFactory factory = cfg.buildSessionFactory();
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));





    public static void insertAuthor() throws IOException {



        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        System.out.print("Enter author name: ");
        String authorName = reader.readLine();

        Author author = new Author();
        author.setAuthorName(authorName);
        s.save(author);
        tx.commit();
        s.close();
    }



    public static void showAuthors(){
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        List<Author> authors = s.createQuery("FROM Author", Author.class).list();

        for (Author author : authors) {
            System.out.println("Author ID: " + author.getAuthorId());
            System.out.println("Author Name: " + author.getAuthorName());
            System.out.println("--------------------------------------");
        }
    }

    public static void editAuthors() throws IOException {
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter author ID to update: ");
            int authorId = Integer.parseInt(reader.readLine());

            Author author = s.get(Author.class, authorId);

            if (author != null) {
                System.out.print("Enter new author name: ");
                String authorName = reader.readLine();

                author.setAuthorName(authorName);
                s.update(author);
                tx.commit();
                System.out.println("Author with ID " + authorId + " updated successfully!");
            } else {
                System.out.println("Author with ID " + authorId + " not found!");
            }
    }

    public static void deleteAuthor() throws IOException {
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter author ID to delete: ");
        int authorId = Integer.parseInt(reader.readLine());

        Author author = s.get(Author.class, authorId);

        if (author != null) {
            s.delete(author);

            tx.commit();
            System.out.println("Author with ID " + authorId + " deleted successfully!");
        } else {
            System.out.println("Author with ID " + authorId + " not found!");
        }
    }

    public static void insertBook() throws IOException {
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();

        System.out.print("Enter book name: ");
        String bookName = reader.readLine();

        // Get author ID from user input
        System.out.print("Enter author ID: ");
        int authorId = Integer.parseInt(reader.readLine());

        Author author = s.get(Author.class, authorId);

        if (author == null) {
            System.out.println("Author with ID " + authorId + " not found!");
            return;
        }

        Book book = new Book(bookName, author);
        s.save(book);

        tx.commit();
    }
    public static void showBook(){
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        List<Book> books = s.createQuery("FROM Book", Book.class).list();

        for (Book book : books) {
            System.out.println("Book ID: " + book.getBookId());
            System.out.println("Book Name: " + book.getBookName());
            System.out.println("Author Name: " + book.getAuthor().getAuthorName());
            System.out.println("--------------------------------------");
        }
    }

    public static void editBook() throws IOException {
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();

        System.out.print("Enter the ID of the book you want to update: ");
        int bookId = Integer.parseInt(reader.readLine());

        System.out.print("Enter the new name for the book: ");
        String newBookName = reader.readLine();

        Book book = s.get(Book.class, bookId);
        book.setBookName(newBookName);
        s.update(book);
        tx.commit();

    }

    public static void deleteBook() throws IOException {
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter book ID to delete: ");
        int bookId = Integer.parseInt(reader.readLine());

        Book book = s.get(Book.class, bookId);

        if (book != null) {
            s.delete(book);

            tx.commit();
            System.out.println("Book with ID " + bookId + " deleted successfully!");
        } else {
            System.out.println("Book with ID " + bookId + " not found!");
        }
    }

    public static void insertPublisher() throws IOException {
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();


        System.out.print("Enter publisher name: ");
        String pubName = reader.readLine();

        System.out.print("Enter publication date (yyyy-mm-dd): ");
        LocalDate pubDate = LocalDate.parse(reader.readLine());

        System.out.print("Enter price: ");
        int price = Integer.parseInt(reader.readLine());

        System.out.print("Enter author ID: ");
        int authorId = Integer.parseInt(reader.readLine());

        System.out.print("Enter book ID: ");
        int bookId = Integer.parseInt(reader.readLine());

        Publisher publisher = new Publisher();
        publisher.setPubName(pubName);
        publisher.setPubDate(pubDate);
        publisher.setPrice(price);

        Author author = s.get(Author.class, authorId);
        Book book = s.get(Book.class, bookId);

        publisher.setAuthor(author);
        publisher.setBook(book);
        s.save(publisher);
        tx.commit();
        s.close();

    }

    public static void showPublisher(){
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        List<Publisher> publishers = s.createQuery("FROM Publisher", Publisher.class).list();

        for (Publisher publisher : publishers) {
            System.out.println("--------------------------------------");
            System.out.println("Publisher ID: " + publisher.getPubId());
            System.out.println("Publisher Name: " + publisher.getPubName());
            System.out.println("Book Name: " + publisher.getBook().getBookName());
            System.out.println("Author Name: " + publisher.getAuthor().getAuthorName());
            System.out.println("Publication Date :"+publisher.getPubDate());
            System.out.println("Publication Price :" + publisher.getPrice());
            System.out.println("--------------------------------------");
        }
    }

    public static void queryOne(){
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        LocalDate userDate = LocalDate.parse("2021-01-01");


        List<Author> authors = s.createQuery("SELECT DISTINCT p.author FROM Publisher p WHERE p.pubDate < :userDate")
                .setParameter("userDate", userDate)
                .getResultList();

        for (Author author:authors) {
            System.out.println("--------------------------------------");
            System.out.println("Author name ::"+ author.getAuthorName());
            System.out.println("--------------------------------------");
        }

        tx.commit();
        s.close();
    }

    public static void query2(){
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();

        String name="Nirali";
        int price = 500;

        List<Book> book = s.createQuery("SELECT p.book FROM Publisher p WHERE p.pubName = :userName AND p.price >=: userPrice")
                .setParameter("userName", name).setParameter("userPrice",price)
                .getResultList();

        for (Book books: book) {
            System.out.println("--------------------------------------");
            System.out.println("Book Name ::" + books.getBookName());
            System.out.println("--------------------------------------");
        }

    }

    public static void queryThree(){
        Session s = factory.openSession();
        Transaction tx = s.beginTransaction();
        //LocalDate userDate = LocalDate.parse("2021-01-01");


        List<Book> books = s.createQuery("SELECT b FROM Author a JOIN a.books b WHERE LOWER(a.authorName) LIKE 'a%i'")
                .getResultList();

        for (Book book:books) {
            System.out.println("--------------------------------------");
            System.out.println("Book name ::"+ book.getBookName());
            System.out.println("--------------------------------------");
        }

        tx.commit();
        s.close();
    }
}
