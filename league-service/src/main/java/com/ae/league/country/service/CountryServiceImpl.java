package com.ae.league.country.service;

import com.ae.league.country.repository.CountryRepository;
import com.ae.league.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository repository;

    @Autowired
    public CountryServiceImpl(CountryRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Country> getAllCountries() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Country getCountryById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Country getCountryByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Country getCountryByCode(String code) {
        Country country = repository.findByCode(code);
        return country;
    }

    @Override
    @Transactional
    public Country createCountry(Country country) {
        if (getCountryByName(country.getName()) != null) {
            return getCountryByName(country.getName());
        }
        return repository.save(country);
    }

    @Override
    @Transactional
    public Country updateCountry(Country country) {
        if (getCountryByName(country.getName()) != null) {
            Country updateCountry = Country.builder()
                    .name(country.getName())
                    .build();
            return repository.save(updateCountry);
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteCountry(Long id) {
        repository.deleteById(id);
    }
}
