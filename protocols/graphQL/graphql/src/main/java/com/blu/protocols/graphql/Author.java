package com.blu.protocols.graphql;

import java.util.Arrays;
import java.util.List;

public record Author(String id, String firstName, String lastName) {
    private static List<Author> authors = Arrays.asList(
            new Author("1", "Shamim", "Bhuiyan"),
            new Author("2", "Mishel", "Titkov")
    );
    public static Author getAuthorById(String id){
        return authors.stream()
                .filter(author -> author.id.equals(id))
                .findFirst()
                .orElse(null);
    }
}
