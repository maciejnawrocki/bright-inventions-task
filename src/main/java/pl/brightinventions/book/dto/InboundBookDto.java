package pl.brightinventions.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InboundBookDto {
  private String name;
  private String author;
  private String isbn;
  private int pages;
  private int rating;
}
