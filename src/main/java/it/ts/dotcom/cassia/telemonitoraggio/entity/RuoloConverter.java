package it.ts.dotcom.cassia.telemonitoraggio.entity;

import it.ts.dotcom.cassia.telemonitoraggio.utils.RuoloEnum;

import javax.persistence.Converter;
import javax.persistence.AttributeConverter;

@Converter(autoApply = true)
public class RuoloConverter implements AttributeConverter<RuoloEnum, String> {

    @Override
    public String convertToDatabaseColumn(RuoloEnum ruoloEnum) {
        return ruoloEnum.name();
    }

    @Override
    public RuoloEnum convertToEntityAttribute(String string) {
        return RuoloEnum.valueOf(string) ;
    }
}
