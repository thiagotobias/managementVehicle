package com.tobias.managementVehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tobias.managementVehicle.util.CSVReader;

@SpringBootApplication
public class ManagementVehicleApplication implements CommandLineRunner{
	
	@Autowired
	private CSVReader csvReader;

	public static void main(String[] args) {
		SpringApplication.run(ManagementVehicleApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		csvReader.readCSVFile();
	}

}
