package com.vaadin.addon.jpacontainer;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.util.converter.Converter;
import com.vaadin.data.util.converter.StringToNumberConverter;

public class SerpicsStringToNumberConverter extends StringToNumberConverter implements Converter<String, Number>{

    private static final Logger logger = LoggerFactory.getLogger(SerpicsStringToNumberConverter.class);


    @Override
    public Number convertToModel(final String value,
            final Class<? extends Number> targetType, final Locale locale)
                    throws ConversionException {
        if (!getModelType() .isAssignableFrom(targetType)) {
            final ConversionException e = new ConversionException("Converter only supports "
                    + getModelType().getName() + " (targetType was "
                    + targetType.getName() + ")");
            logger.error("conversion error", e);
            throw e;
        }

        return convertToNumber(value, targetType, locale);
    }


    @Override
    protected Number convertToNumber(String value,
            final Class<? extends Number> targetType, final Locale locale)
                    throws ConversionException {
        if (value == null) {
            return null;
        }

        // Remove leading and trailing white space
        value = value.trim();

        // Parse and detect errors. If the full string was not used, it is
        // an error.
        final ParsePosition parsePosition = new ParsePosition(0);
        final Number parsedValue = getFormat(locale).parse(value, parsePosition);
        if (parsePosition.getIndex() != value.length()) {
            throw new ConversionException("Could not convert '" + value
                    + "' to " + getModelType().getName());
        }

        if (parsedValue == null) {
            // Convert "" to null
            return null;
        }

        if (targetType.equals(BigInteger.class))
            return new BigInteger(parsedValue.toString());

        if (targetType.equals(Integer.class))
            return Integer.parseInt(parsedValue.toString());

        return parsedValue;
    }

    //	@Override
    //    public Number convertToModel(String value,
    //            Class<? extends Number> targetType, Locale locale)
    //            throws ConversionException {
    //		
    //		Number res = null;
    //		try {
    //			res = super.convertToModel(value, targetType, locale);
    //		} catch (ConversionException e){
    //			logger.error("conversion error", e);
    //			throw e;
    //		}
    //		
    //		return res;
    //    }



    @Override
    protected NumberFormat getFormat(final Locale locale)
    {
        final NumberFormat numberFormat = super.getFormat(locale);
        //        if (numberFormat instanceof DecimalFormat)
        //        {
        //            ((DecimalFormat)numberFormat).setParseBigDecimal(true);
        //        }


        return numberFormat;
    }


}
