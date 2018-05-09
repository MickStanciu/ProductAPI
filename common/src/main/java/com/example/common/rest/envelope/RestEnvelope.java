package com.example.common.rest.envelope;

import com.example.common.rest.dto.ErrorDto;

import java.io.Serializable;
import java.util.List;

public abstract class RestEnvelope<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private T data;
    private List<ErrorDto> errors;

    RestEnvelope(T data, List<ErrorDto> errors) {
        this.data = data;
        this.errors = errors;
    }

    public RestEnvelope() {
        //required by jackson
    }

    public T getData() {
        return data;
    }

    public List<ErrorDto> getErrors() {
        return errors;
    }
}
