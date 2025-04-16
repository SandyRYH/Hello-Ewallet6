package id.co.bsi.hello_spring.dto.request;

import lombok.Data;

@Data
public class DashboardRequest {
    private String account_number;
    private String full_name;
    private int balance;
}
