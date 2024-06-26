package jpabook.jpashop.domain.item;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B") //기본으로 넣어도 들어감
@Getter
@Setter
public class Book extends Item{
    private String author;
    private String isbn;
}
