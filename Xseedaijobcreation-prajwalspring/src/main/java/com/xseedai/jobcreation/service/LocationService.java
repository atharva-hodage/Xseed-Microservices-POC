package com.xseedai.jobcreation.service;

import java.util.List;

import com.xseedai.jobcreation.entity.City;
import com.xseedai.jobcreation.entity.Country;
import com.xseedai.jobcreation.entity.State;

public interface LocationService {

	Country createCountry(Country country);

	State createState(State state,Long countryId);

	City createCity(City city,Long stateId);

	List<Country> getAllCountries();

	List<State> getStatesByCountryId(Long countryId);

	List<City> getCitiesByStateId(Long stateId);

}
