package com.pantry.entities.util;

import com.pantry.model.QuantityType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class QuantityTypeConverter implements AttributeConverter<QuantityType, java.lang.String> {

    @Override
    public java.lang.String convertToDatabaseColumn(QuantityType quantityType) {
        if (quantityType == null) {
            return null;
        }
        return quantityType.getValues().get(0);
    }

    @Override
    public QuantityType convertToEntityAttribute(java.lang.String s) {
        return QuantityType.forTypeString(s);
    }
}
