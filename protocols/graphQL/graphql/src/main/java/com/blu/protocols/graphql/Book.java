package com.blu.protocols.graphql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public record Book (String id, String name, int pageCount, String authorId) {
    // in-memory list
    private static List<Book> books = Arrays.asList(
        new Book("1", "High-performance in-memory computing",450, "1"),
        //new Book("2", "The Apache Ignite Book", 630, "1"),
        new Book("2", "The Apache Ignite book",450, "2")
    );
    public static Book getBookById(String id){
        System.out.println("Date Time:"+ java.time.LocalDate.now() + " : Called getBookById: " + id);
        return books.stream()
                .filter(book -> book.id().equals(id))
                .findFirst()
                .orElse(null
        );
    }
}
