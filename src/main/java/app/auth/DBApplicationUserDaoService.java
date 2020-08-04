package app.auth;

import app.entity.Employee;
import app.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository("db")
public class DBApplicationUserDaoService implements ApplicationUserDao {

    private final EmployeeRepo employeeRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DBApplicationUserDaoService(EmployeeRepo employeeRepo, PasswordEncoder passwordEncoder) {
        this.employeeRepo = employeeRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }


    private List<ApplicationUser> getApplicationUsers() {
        List<String> roles = employeeRepo.findAll().stream().map(Employee::getRoles).collect(Collectors.toList());

        HashSet<GrantedAuthority> authorities = new HashSet<>(roles.size());
        for (String role : roles){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        }
        List<ApplicationUser> applicationUsers =employeeRepo.findAll()
                .stream().map(e-> new ApplicationUser(e.getUsername(), passwordEncoder.encode(e.getPassword()), authorities,true,true,true,true )).collect(Collectors.toList());

          return applicationUsers;
    }

}
