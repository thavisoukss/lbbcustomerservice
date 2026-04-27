package com.lbb.customer.createaccount.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMonth {
    private String td_code;
    private String interest_type;
    private String description;
    private int month;

}
