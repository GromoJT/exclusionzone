package pl.gromotj.exclusionzone.service.imlp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.gromotj.exclusionzone.entity.ZoneUser;
import pl.gromotj.exclusionzone.exception.UserEmailNotFoundException;
import pl.gromotj.exclusionzone.repository.IZoneUserRepository;

import java.util.Optional;
@Service
public class ZoneUserDetailService implements UserDetailsService {
    @Autowired
    private IZoneUserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ZoneUser> user = repository.findByUserName(username);
        if(user.isPresent()){
            var userObj = user.get();
            return User.builder()
                    .username(userObj.getUserName())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj))
                    .disabled(!userObj.isVerified())
                    .build();
        }else{
            throw new UsernameNotFoundException(username);
        }
    }


//    public UserDetails loadUserByEmail(String email) throws UserEmailNotFoundException {
//        Optional<ZoneUser> user = repository.findByUserEmail(email);
//        if(user.isPresent()){
//            var userObj = user.get();
//            return User.builder()
//                    .username(userObj.getUserName())
//                    .password(userObj.getPassword())
//                    .roles(getRoles(userObj))
//                    .build();
//        }else{
//            throw new UsernameNotFoundException(username);
//        }
//    }

    private String[] getRoles(ZoneUser userObj) {
        if(userObj.getGlobalRole() == null){
            return new String[]{"VISITOR"};
        }
        return userObj.getGlobalRole().split(",");
    }
}
