package tian.springframework.petclinic.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import tian.springframework.petclinic.model.Price;
import tian.springframework.petclinic.repositories.PriceRepository;
import tian.springframework.petclinic.services.PriceService;

import java.util.HashSet;
import java.util.Set;

/**
 * @author tian
 */

@Service
@Profile("springdatajpa")
public class PriceSDJpaService implements PriceService {

    private final PriceRepository priceRepository;

    public PriceSDJpaService(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Set<Price> findAll() {
        Set<Price> prices = new HashSet<>();
        priceRepository.findAll().forEach(prices::add);

        return prices;
    }

    @Override
    public Price findById(Long id) {
        return priceRepository.findById(id).orElse(null);
    }

    @Override
    public Price save(Price object) {
        return priceRepository.save(object);
    }

    @Override
    public void delete(Price object) {
        priceRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        priceRepository.deleteById(aLong);
    }
}
