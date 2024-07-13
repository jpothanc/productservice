package com.ib.it.productservice.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class QueryResponse<T> {
    private List<T> data;

    public static class Builder<T> {
        private final QueryResponse<T> instance;

        public Builder() {
            instance = new QueryResponse<>();
        }

        public Builder<T> setData(List<T> data) {
            instance.data = data;
            return this;
        }

        public QueryResponse<T> build() {
            return instance;
        }
    }
}
