package com.brianmorales.chatapplication.clutter;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Embeddable
public class FileEntity {
    private String url;
    private String type;
}
