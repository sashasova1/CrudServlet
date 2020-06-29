/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3_5;

import java.io.*;
import java.util.*;
import java.sql.*;

/**
 *
 * @author sovas
 */
public class LibraryDAO {

    private Connection myConn;

    public LibraryDAO() throws Exception {

        Properties props = new Properties();
        props.load(new FileInputStream("library.properties"));

        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String dburl = props.getProperty("dburl");

        myConn = DriverManager.getConnection(dburl, user, password);
        System.out.println("DB connection successful to: " + dburl);
    }

    public List<Book> getAllBooks() throws Exception {
        List<Book> list = new ArrayList<>();

        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("select * from books");

            while (myRs.next()) {
                Book tempBook = convertRowToBook(myRs);
                list.add(tempBook);
            }
            return list;
        } finally {
            close(myStmt, myRs);
        }
    }

    public void addBook(Book theBook) throws Exception {
        PreparedStatement myStmt = null;
        try {
            myStmt = myConn.prepareStatement("insert into books"
                    + " (name, author, genre)"
                    + " values (?, ?, ?)");

            myStmt.setString(1, theBook.getName());
            myStmt.setString(2, theBook.getAuthor());
            myStmt.setString(3, theBook.getGenre());

            myStmt.executeUpdate();
        } finally {
            close(myStmt);
        }

    }

    public void deleteBook(int id) throws SQLException {
        PreparedStatement myStmt = null;
        try {
            myStmt = myConn.prepareStatement("delete from books where id=?");

            myStmt.setInt(1, id);

            myStmt.executeUpdate();
        } finally {
            close(myStmt);
        }
    }

    public void updateBook(Book theBook) throws SQLException {
        PreparedStatement myStmt = null;

        try {
            myStmt = myConn.prepareStatement("update books"
                    + " set name=?, author=?, genre=?"
                    + " where id=?");

            myStmt.setString(1, theBook.getName());
            myStmt.setString(2, theBook.getAuthor());
            myStmt.setString(3, theBook.getGenre());
            myStmt.setInt(4, theBook.getID());

            myStmt.executeUpdate();
        } finally {
            close(myStmt);
        }

    }

    private Book convertRowToBook(ResultSet myRs) throws SQLException {

        int id = myRs.getInt("id");
        String name = myRs.getString("name");
        String author = myRs.getString("author");
        String genre = myRs.getString("genre");

        Book tempBook = new Book(id, name, author, genre);
        return tempBook;
    }

    private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {

        if (myRs != null) {
            myRs.close();
        }

        if (myStmt != null) {
        }

        if (myConn != null) {
            myConn.close();
        }
    }

    private void close(Statement myStmt, ResultSet myRs) throws SQLException {
        close(null, myStmt, myRs);
    }

    private void close(Statement myStmt) throws SQLException {
        close(null, myStmt, null);
    }

}
