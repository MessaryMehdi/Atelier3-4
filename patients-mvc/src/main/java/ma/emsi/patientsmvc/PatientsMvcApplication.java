package ma.emsi.patientsmvc;

import ma.emsi.patientsmvc.entities.Patient;
import ma.emsi.patientsmvc.repositories.PatientRepository;
import ma.emsi.patientsmvc.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


   @Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return  args ->{
            patientRepository.save(
                    new Patient(null, "Hassan",true,new Date(),false,true,122));
            patientRepository.save(
                    new Patient(null, "Mehdi",true,new Date(),true,false,333));
            patientRepository.save(
                    new Patient(null, "Fatima",false,new Date(),true,true,130));
            patientRepository.save(
                    new Patient(null, "Ali",true,new Date(),false,false,160));

            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });
        };
    }
   // @Bean
   CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
           securityService.saveNewUser("Farouk","1234","1234");
            securityService.saveNewUser("Abdou","1234","1234");
            securityService.saveNewUser("Manal","1234","1234");


            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");


            securityService.addRoleToUser("Farouk","USER");
            securityService.addRoleToUser("Abdou","USER");
            securityService.addRoleToUser("Manal","ADMIN");
            securityService.addRoleToUser("Manal","USER");
        };
    }

}
