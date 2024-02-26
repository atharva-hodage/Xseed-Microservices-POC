package com.xseedai.jobcreation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xseedai.jobcreation.entity.City;
import com.xseedai.jobcreation.entity.Country;
import com.xseedai.jobcreation.entity.State;
import com.xseedai.jobcreation.service.LocationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/jobcreation")
@AllArgsConstructor

public class LocationController {
	
	@Autowired
	 private  LocationService locationService;
	
	@PostMapping("/addcountry")
    public ResponseEntity<Country> Country(@RequestBody Country country) {
        Country createdCountry = locationService.createCountry(country);
        return new ResponseEntity<>(createdCountry, HttpStatus.CREATED);
    }

	@PostMapping("/addstate")
	@JsonIgnore
	public ResponseEntity<State> createState(@RequestBody State state, @RequestParam("countryId") Long countryId) {
	    State createdState = locationService.createState(state, countryId);
	    return new ResponseEntity<>(createdState, HttpStatus.CREATED);
	}

	@PostMapping("/addcity")
	public ResponseEntity<City> createCity(@RequestBody City city, @RequestParam("stateId") Long stateId) {
	    City createdCity = locationService.createCity(city, stateId);
	    return new ResponseEntity<>(createdCity, HttpStatus.CREATED);
	}

    @GetMapping("/getcountry")
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = locationService.getAllCountries();
        return ResponseEntity.ok().body(countries);
    }

    @GetMapping("/getstate")
    public ResponseEntity<List<State>> getStatesByCountryId(@RequestParam("countryId") Long countryId) {
        List<State> states = locationService.getStatesByCountryId(countryId);
        return ResponseEntity.ok().body(states);
    }

    @GetMapping("/getcity")
    public ResponseEntity<List<City>> getCitiesByStateId(@RequestParam("stateId") Long stateId) {
        List<City> cities = locationService.getCitiesByStateId(stateId);
        return ResponseEntity.ok().body(cities);
    }
}
