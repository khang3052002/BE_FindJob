package backend.findjob;

import backend.findjob.helper.Helper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FindJobApplication {

	public static void main(String[] args) {

//		System.out.println(Helper.convertDay());
		SpringApplication.run(FindJobApplication.class, args);
	}

}
