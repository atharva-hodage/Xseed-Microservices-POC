package com.xseedai.jobcreation.service.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xseedai.jobcreation.entity.City;
import com.xseedai.jobcreation.entity.Country;
import com.xseedai.jobcreation.entity.State;
import com.xseedai.jobcreation.repository.CityRepository;
import com.xseedai.jobcreation.repository.CountryRepository;
import com.xseedai.jobcreation.repository.StateRepository;
import com.xseedai.jobcreation.service.LocationService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private CityRepository cityRepository;
	
	
	private ModelMapper modelMapper;
//
	@Override
	public Country createCountry(Country country) {
	    // Check if the country already exists by name
	    Optional<Country> existingCountry = countryRepository.findByCountryName(country.getCountryName());
	    if (existingCountry.isPresent()) {
	        throw new IllegalArgumentException("Country with name '" + country.getCountryName() + "' already exists.");
	    }

	    // Set created and modified details
	    country.setCreatedBy("prajwal");
	    country.setCreatedOn(LocalDateTime.now());
	    country.setModifiedBy(country.getCreatedBy());
	    country.setModifiedOn(country.getCreatedOn());

	    // Validate country name
	    if (country.getCountryName() == null || country.getCountryName().isEmpty()) {
	        throw new IllegalArgumentException("Country name cannot be null or empty.");
	    }

	    // Save the country
	    return countryRepository.save(country);
	}

	@Override
	public State createState(State state, Long countryId) {
		    // Retrieve the country by its ID
		    Country country = countryRepository.findById(countryId)
		            .orElseThrow(() -> new IllegalArgumentException("Country not found with ID: " + countryId));

		    // Associate the state with the retrieved country
		    state.setCountry(country);

		    state.setCreatedBy("prajwal");
		    state.setCreatedOn(LocalDateTime.now());
		    state.setModifiedBy(state.getCreatedBy());
		    state.setModifiedOn(state.getCreatedOn());

		    // Validate state name
		    if (state.getStateName() == null || state.getStateName().isEmpty()) {
		        throw new IllegalArgumentException("State name cannot be null or empty.");
		    }

		    // Save the state
		    return stateRepository.save(state);
		}

	@Override
	public City createCity(City city, Long stateId) {
	    // Retrieve the state by its ID
	    State state = stateRepository.findById(stateId)
	            .orElseThrow(() -> new IllegalArgumentException("State not found with ID: " + stateId));

	    // Associate the city with the retrieved state
	    city.setState(state);

	    // Set created and modified details
	    city.setCreatedBy("prajwal");
	    city.setCreatedOn(LocalDateTime.now());
	    city.setModifiedBy(city.getCreatedBy());
	    city.setModifiedOn(city.getCreatedOn());

	    // Validate city name
	    if (city.getCityName() == null || city.getCityName().isEmpty()) {
	        throw new IllegalArgumentException("City name cannot be null or empty.");
	    }

	    // Save the city
	    return cityRepository.save(city);
	}

	@Override
	public List<Country> getAllCountries() {

		return countryRepository.findAll();
	}

	@Override
	public List<State> getStatesByCountryId(Long countryId) {

		 return stateRepository.findByCountryCountryId(countryId);
	}

	@Override
	public List<City> getCitiesByStateId(Long stateId) {

		return cityRepository.findByStateStateId(stateId);
	}

}
