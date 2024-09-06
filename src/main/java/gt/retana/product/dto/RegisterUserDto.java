package gt.retana.product.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterUserDto {

    private String firstName;
    private String lastName;
    private String shippingAddress;
    private String email;
    private Date birthDate;
    private String password;

    @Override
    public String toString() {
        return "RegisterUserDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + firstName+ ' '+lastName + '\'' +
                '}';
    }
}