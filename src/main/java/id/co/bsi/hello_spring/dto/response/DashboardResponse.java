package id.co.bsi.hello_spring.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DashboardResponse {
    private String status;
    private String message;
    private DashboardData data;

    @Data
    public static class DashboardData {
        @JsonProperty("account_number")
        private String accountnum;

        @JsonProperty("full_name")
        private String fullname;

        private int balance;
    }
}
