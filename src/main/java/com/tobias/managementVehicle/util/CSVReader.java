package com.tobias.managementVehicle.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tobias.managementVehicle.dto.NewVehicleDTO;
import com.tobias.managementVehicle.service.VehicleService;

@Service
public class CSVReader {
	
	private Integer maiorIdCarDTO = 0;
	private final String CSV_FILE_PATH = "src/main/resources/carros.csv";
	private final String CSV_DELIMITER = ",";
	private final String CSV_QUOTE = "\"";
	
	@Autowired
	private VehicleService vehicleService; 

    public List<NewVehicleDTO> readCSVFile() {
    	List<NewVehicleDTO> cars = new ArrayList<>();
        
    	try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_PATH))) {
        	br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
            	NewVehicleDTO car = parseCarFromCSVLine(line);
            	if(car != null) {
            		cars.add(car);
            		vehicleService.create(car);
            	}
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV: " + e.getMessage());
        }
        
        return cars;
    }

    private NewVehicleDTO parseCarFromCSVLine(String line) {
    	try {
    		String[] tokens = line.split(CSV_DELIMITER);
                       
            Integer id = Integer.parseInt(removeQuotes(tokens[0]));
            
            String marca = removeQuotes(tokens[1]);
            String modelo = removeQuotes(tokens[2]);
            String cor = removeQuotes(tokens[3]);

            if (id > maiorIdCarDTO) {
                maiorIdCarDTO = id;
            }
            return new NewVehicleDTO(marca, modelo, cor);
		} catch (Exception e) {
			e.getStackTrace();
		} 
        return null;

    }
    
    private String removeQuotes(String value) {
        if (value.startsWith(CSV_QUOTE) && value.endsWith(CSV_QUOTE)) {
            return value.substring(1, value.length() - 1);
        }
        return value;
    }
    
    public Integer getMaiorIdCarDTO() {
        return maiorIdCarDTO;
    }
}
