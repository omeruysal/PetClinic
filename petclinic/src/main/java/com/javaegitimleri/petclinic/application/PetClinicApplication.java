package com.javaegitimleri.petclinic.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.javaegitimleri.petclinic.dao.OwnerRepository;
import com.javaegitimleri.petclinic.service.PetClinicService;
import com.javaegitimleri.petclinic.service.PetClinicServiceImpl;
import com.javaegitimleri.petclinic.web.PetClinicController;


@ServletComponentScan  // bu anatasyon sayesınde sprıngboot classpathte webserlet ozellıgıne sahıp sınıfları otomatık tespıt eder
@SpringBootApplication
@ComponentScan(basePackageClasses = {PetClinicController.class, 
		PetClinicService.class,PetClinicServiceImpl.class, OwnerRepository.class})
public class PetClinicApplication {

	public static void main(String[] args) {

		SpringApplication.run(PetClinicApplication.class,args);
		System.out.println("                                                                       _______   _____   _______");
		System.out.println("                                                                      |__   __| |  ___| |   _   |");
		System.out.println("                                                                         | |    | |_    |  | |  |");
		System.out.println("                                                                         | |    |  _|   |  | |  |");
		System.out.println("                                                                         | |    | |___  |  |_|  |");
		System.out.println("                                                                         |_|    |_____| |_______|");
		
		System.out.println("                                                                             Welcome Home ");
		
	}

}
