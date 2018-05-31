package com.example.web.converter;

import com.example.common.converter.TimeConversion;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@FacesConverter("localDateTimeConverter")
public class LocalDateTimeConverter implements Converter {

    private final String FORMAT_VALUE = "HH:mm";
    private final String FORMAT_ATTRIBUTE = "format";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (null == s || s.isEmpty()) {
            return null;
        }

        return TimeConversion.fromString(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (null == o) {
            return "";
        }

        if (!(o instanceof LocalDateTime)) {
            throw new ConverterException("Must be applied on an LocalDateTime");
        }

        Map<String, Object> attributes = uiComponent.getAttributes();
        String formatString = (String) attributes.get(FORMAT_ATTRIBUTE);
        if (null == formatString) {
            formatString = FORMAT_VALUE;
        }

        LocalDateTime localDateTime = (LocalDateTime) o;

        return localDateTime.format(DateTimeFormatter.ofPattern(formatString).withZone(ZoneOffset.UTC));
    }
}