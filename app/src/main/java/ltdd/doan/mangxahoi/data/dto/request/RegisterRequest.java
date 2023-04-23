package ltdd.doan.mangxahoi.data.dto.request;

public class RegisterRequest {
    public String email ;
    public String username ;
    public String password ;

    public RegisterRequest(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }
}
