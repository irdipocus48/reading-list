package com.example.readinglist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Book {

    private Long id;

    private String title;

    private String author;

    private Genre genre;

    private Integer mark;

    private String comment;


    public boolean isValid() {
        return this.title != null && this.author != null &&
                this.genre != null && this.mark != null && this.comment != null;
    }

    public enum Genre {
        Detective,
        Drama,
        Romance,
        Science,
        History,
        Poetry,
        Other
    }
}
