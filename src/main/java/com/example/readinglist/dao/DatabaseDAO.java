package com.example.readinglist.dao;

import com.example.readinglist.model.Book;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DatabaseDAO extends DatabaseFactory implements ReadingListDAO {

    static {
        establishConnection();
    }

    public void add(Book book) {
        var sql = "INSERT INTO BOOK (ID, TITLE, AUTHOR, GENRE, MARK, COMMENT) VALUES (?,?,?,?,?,?);";

        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, System.currentTimeMillis());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getGenre().name());
            preparedStatement.setInt(5, book.getMark());
            preparedStatement.setString(6, book.getComment());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error during adding: {}", e.getMessage());
        }
    }


    public List<Book> getAll() {
        List<Book> bookList = new ArrayList<>();
        var sql = "SELECT * FROM BOOK";

        try (var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                var book = Book.builder()
                        .id(resultSet.getLong("ID"))
                        .title(resultSet.getString("TITLE"))
                        .author(resultSet.getString("AUTHOR"))
                        .genre(Book.Genre.valueOf(resultSet.getString("GENRE")))
                        .mark(resultSet.getInt("MARK"))
                        .comment(resultSet.getString("COMMENT"))
                        .build();

                bookList.add(book);
            }
        } catch (SQLException e) {
            log.error("Error during getting all: {}", e.getMessage());
        }
        return bookList;
    }


    public Book getById(Long id) {
        Book book = null;
        var sql = "SELECT ID, TITLE, AUTHOR, GENRE, MARK, COMMENT FROM BOOK WHERE ID=?;";

        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                book = Book.builder()
                        .id(resultSet.getLong("ID"))
                        .title(resultSet.getString("TITLE"))
                        .author(resultSet.getString("AUTHOR"))
                        .genre(Book.Genre.valueOf(resultSet.getString("GENRE")))
                        .mark(resultSet.getInt("MARK"))
                        .comment(resultSet.getString("COMMENT"))
                        .build();
            }
        } catch (SQLException e) {
            log.error("Error during getting by id: {}", e.getMessage());
        }

        return book;
    }


    public void update(Book book) {
        String sql = "UPDATE BOOK SET  TITLE=?, AUTHOR=?, GENRE=?, MARK=?, COMMENT=? WHERE ID=?;";

        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getGenre().name());
            preparedStatement.setInt(4, book.getMark());
            preparedStatement.setString(5, book.getComment());
            preparedStatement.setLong(6, book.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error during update: {}", e.getMessage());
        }
    }


    public void remove(Long id) {
        var sql = "DELETE FROM BOOK WHERE ID=?";

        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error during remove: {}", e.getMessage());
        }
    }

}
