package com.example.common.rest.envelope;

import com.example.common.rest.dto.ErrorDto;
import com.example.common.rest.dto.PaginationDto;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

import java.util.List;

public class PageEnvelope<T> extends RestEnvelope<T> {

    private final PaginationDto pagination;


    private PageEnvelope(PageEnvelopeBuilder<T> builder) {
        super(builder.data, builder.errors);
        this.pagination = builder.pagination;
    }

    public PaginationDto getPagination() {
        return pagination;
    }

    @JsonIgnoreType
    public static class PageEnvelopeBuilder<T> {
        private T data;
        private List<ErrorDto> errors;
        private PaginationDto pagination;

        public PageEnvelope.PageEnvelopeBuilder data(T data) {
            this.data = data;
            return this;
        }

        public PageEnvelope.PageEnvelopeBuilder errors(List<ErrorDto> errors) {
            this.errors = errors;
            return this;
        }

        public PageEnvelope.PageEnvelopeBuilder pagination(PaginationDto pagination) {
            this.pagination = pagination;
            return this;
        }

        public PageEnvelope build() {
            return new PageEnvelope<>(this);
        }
    }
}
