package id.co.bsi.hello_spring.dto.request;

import lombok.Data;

@Data
public class DashboardRequest {
    private String accountnum;
    private String token;
}
