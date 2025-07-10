package com.belajar.belajarjpah2.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageModel {
    private String message;
    private boolean status;
    private Object data;
    // Tambahan untuk pagination (ini optional, bisa null dan tidak akan muncul bila tidak di set. sehingga compatible dengan yg existing)
    private Integer totalItems;
    private Integer totalPages;
    private Integer currentPage;
    private Integer numberOfElement;
}
