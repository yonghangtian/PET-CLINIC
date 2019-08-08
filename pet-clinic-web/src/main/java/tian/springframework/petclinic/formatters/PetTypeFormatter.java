package tian.springframework.petclinic.formatters;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import tian.springframework.petclinic.model.PetType;
import tian.springframework.petclinic.services.PetTypeService;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

/**
 * created by tianyh on 8/8/19 8:05 PM
 */
@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String s, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = petTypeService.findAll();

        for (PetType type : findPetTypes) {
            if (type.getName().equals(s)) {
                return type;
            }
        }

        throw new ParseException("type not found" + s , 0);
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
