package org.ahmedukamel.eduai.dto.Library;

import lombok.Data;

import java.util.List;


@Data
    public class TransactionDTO {

        private Integer userId;
        private List<BookRecordDTO> books;
}
