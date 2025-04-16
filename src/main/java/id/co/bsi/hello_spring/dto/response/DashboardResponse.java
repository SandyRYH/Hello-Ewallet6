package id.co.bsi.hello_spring.dto.response;

import lombok.Data;

@Data
public class DashboardResponse {
    private String status;
    private Data data;
    private String message;
}
