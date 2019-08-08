package com.grokonez.jwtauthentication.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grokonez.jwtauthentication.message.request.LoginForm;
import com.grokonez.jwtauthentication.message.request.SignUpForm;
import com.grokonez.jwtauthentication.message.response.JwtResponse;
import com.grokonez.jwtauthentication.message.response.ResponseMessage;
import com.grokonez.jwtauthentication.model.Role;
import com.grokonez.jwtauthentication.model.RoleName;
import com.grokonez.jwtauthentication.model.User;
import com.grokonez.jwtauthentication.repository.RoleRepository;
import com.grokonez.jwtauthentication.repository.UserRepository;
import com.grokonez.jwtauthentication.security.EmailCfg;
import com.grokonez.jwtauthentication.security.jwt.JwtProvider;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestAPIs {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
    private EmailCfg emailCfg;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteuser(@PathVariable long id) {
		userRepository.deleteById(id);		
		return new ResponseEntity<>(new ResponseMessage("User deleted successfully!"), HttpStatus.OK);
		}


	@GetMapping("/AllUsers")
	public List<User> getAllUsers() {
		
		return 	userRepository.findAll();
	}
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(adminRole);

				break;
			
			default:
				Role userRole = roleRepository.findByName(RoleName.ROLE_EMPLOYER)
						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
				roles.add(userRole);
			}
		});

		user.setRoles(roles);
		userRepository.save(user);

		return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	}
	
	public String generate(int length)
	{
		    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		    String pass = "";
		    for(int x=0;x<length;x++)
		    {
		       int i = (int)Math.floor(Math.random() * 62); 
		       pass += chars.charAt(i);
		    }
		    System.out.println(pass);
		    return pass;
	}
	public String dateformat(String datetest) throws ParseException {
		String date_s = datetest;

        // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"  
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dt.parse(date_s);

        // *** same for the format String below
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");

		return dt1.format(date);
		
	}
	
	@GetMapping("/test/{id}/{datedebut}/{datefin}")
   public boolean test(@PathVariable long id,@PathVariable String datedebut,@PathVariable String datefin) throws ParseException {
	
			Date a =new Date(dateformat(datedebut));  // assume these are set to something
			Date b = new Date(dateformat(datefin)); 
			Date d = new Date("entry_DATE");
			return	(a.compareTo(d) * d.compareTo(b) > 0); 	
   } 
	
	@PostMapping("mail")
	public ResponseEntity getStudent(@RequestBody String username) {
		User user = userRepository.findByUsername(username) .orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username  : " +username));
	String	x =generate(16);
	user.setPassword(encoder.encode(x));
	
	  JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
      mailSender.setHost(this.emailCfg.getHost());
      mailSender.setPort(this.emailCfg.getPort());
      mailSender.setUsername(this.emailCfg.getUsername());
      mailSender.setPassword(this.emailCfg.getPassword());

      // Create an email instance
      SimpleMailMessage mailMessage = new SimpleMailMessage();
      mailMessage.setFrom("aflikhouloud18@gmail.com");
      mailMessage.setTo(user.getEmail());
      mailMessage.setSubject("Mot de passe oublier");
      mailMessage.setText("Votre nouveau mot de passe :"+x);

      // Send mail
      mailSender.send(mailMessage);
	userRepository.save(user);
		
		
		return new ResponseEntity<>(new ResponseMessage("Client registered successfully!"), HttpStatus.OK);
		}
	
	
	
	
}