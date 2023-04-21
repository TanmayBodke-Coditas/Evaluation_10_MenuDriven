import DAO.DAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Scanner;

public class Driver {

    //author
    //many books
    //publication - 1 book 1 publication
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int choice;

        do {
            System.out.println("1. Insert Author");
            System.out.println("2. Show Author");
            System.out.println("3. Edit Author");
            System.out.println("4. Delete Author");
            System.out.println("5. Insert Book");
            System.out.println("6. Show Book");
            System.out.println("7. Edit Book");
            System.out.println("8. Delete Book");
            System.out.println("9. Add Publication");
            System.out.println("10. Show Publication");
            System.out.println("11. Query 1");
            System.out.println("12. Query 2");
            System.out.println("13. Query 3");
//            System.out.println("3. Edit Author");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(reader.readLine());

            switch (choice) {
                case 1:
                    DAO.insertAuthor();
                    break;
                case 2:
                    DAO.showAuthors();
                    break;
                case 3:
                    DAO.editAuthors();
                    break;
                case 4:
                    DAO.deleteAuthor();
                    break;
                case 5:
                    DAO.insertBook();
                    break;
                case 6:
                    DAO.showBook();
                    break;
                case 7:
                    DAO.editBook();
                    break;
                case 8:
                    DAO.deleteBook();
                    break;
                case 9:
                    DAO.insertPublisher();
                    break;
                case 10:
                    DAO.showPublisher();
                    break;
                case 11:
                    DAO.queryOne();
                    break;
                case 12:
                    DAO.query2();
                    break;
                case 13:
                    DAO.queryThree();
                    break;
                case 0:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();

        } while (choice != 0);



    }

    }

