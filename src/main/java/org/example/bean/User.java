package org.example.bean;

import lombok.*;
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private long id;
    private String name;
    private String lastName;
    private byte age;
}
