package com.dreamkite.demo.model.entity;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ImageEntity {
    private String id;

    private String name;

    private byte[] content;
}
