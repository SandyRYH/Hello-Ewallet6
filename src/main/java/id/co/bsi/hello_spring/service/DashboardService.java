package id.co.bsi.hello_spring.service;

import id.co.bsi.hello_spring.dto.response.DashboardResponse;
import id.co.bsi.hello_spring.model.User;
import id.co.bsi.hello_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private UserRepository userRepository;

    public DashboardResponse getDashboard(String token, String accountnum) {
        DashboardResponse response = new DashboardResponse();
        var userOpt = userRepository.findByToken(token);
        if (userOpt.isEmpty()) {
            response.setStatus("fail");
            response.setMessage("Unauthorized access");
            return response;
        }

        User user = userOpt.get();
        if (!user.getAccountnum().equals(accountnum)) {
            response.setStatus("fail");
            response.setMessage("Account number mismatch");
            return response;
        }

        DashboardResponse.DashboardData data = new DashboardResponse.DashboardData();
        data.setAccountnum(user.getAccountnum());
        data.setFullname(user.getFullName());
        data.setBalance(user.getBalance());

        response.setStatus("success");
        response.setMessage("Dashboard successfully displayed");
        response.setData(data);
        return response;
    }
}
