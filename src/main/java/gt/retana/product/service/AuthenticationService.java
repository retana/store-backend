package gt.retana.product.service;

import gt.retana.product.dto.LoginUserDto;
import gt.retana.product.dto.RegisterUserDto;
import gt.retana.product.dto.UpdatePasswordDto;
import gt.retana.product.model.User;
import gt.retana.product.model.repository.UserRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public AuthenticationService(
        UserRepository userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder,
        JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public User resetPassword(UpdatePasswordDto updatePasswordDto){
        User user =    userRepository.findByEmail(updatePasswordDto.getEmail()).orElseThrow();
        user.setPassword(passwordEncoder.encode(updatePasswordDto.getNewPassword()));
        return userRepository.save(user);
    }

    public User signup(RegisterUserDto input) {
         User user = new User();
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        if(!validate(input.getBirthDate())){
            throw new ConstraintViolationException("The person must be at least 18 years old.", null);
        }
        user.setBirthDate(input.getBirthDate());
        user.setShippingAddres(input.getShippingAddress());
        return userRepository.save(user);
    }

    public  boolean validate(Date birthDate) {
        Calendar fechaNac = Calendar.getInstance();
        fechaNac.setTime(birthDate);

        Calendar hoy = Calendar.getInstance();

        int edad = hoy.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);

        if (hoy.get(Calendar.MONTH) < fechaNac.get(Calendar.MONTH) ||
                (hoy.get(Calendar.MONTH) == fechaNac.get(Calendar.MONTH) &&
                        hoy.get(Calendar.DAY_OF_MONTH) < fechaNac.get(Calendar.DAY_OF_MONTH))) {
            edad--;
        }

        return edad >= 18;
    }
    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                input.getEmail(),
                input.getPassword()
            )
        );

        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        return jwtService.isTokenValid(token,userDetails);
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}