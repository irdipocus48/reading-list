package com.example.readinglist;

import com.example.readinglist.dao.DatabaseFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ReadingListDAOTest extends DatabaseFactory {

    @Test
    public void someMethode_shouldReturnSmth_ifArgsValid() {

        // Given
        Long id1 = System.currentTimeMillis();
        Long id2 = System.currentTimeMillis() + 3453;
        var book1 = new Book(id1, "Alise in wanderland", "Jon Jason", Book.Genre.Detective, 4, "ewcfevc");
        var book2 = new Book(id2, "Biba and boba", "Ben Truman", Book.Genre.History, 3, "dfvdv");

        // When
        System.out.println(book1);
        System.out.println(book2);

        // Then
        Assert.assertEquals(id1, book1.getId());
        Assert.assertEquals(id2, book2.getId());
        assert id1.equals(book1.getId());
        assert id2.equals(book2.getId());
    }

}