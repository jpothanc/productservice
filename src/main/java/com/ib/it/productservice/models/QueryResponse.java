package com.ib.it.productservice.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@JsonPropertyOrder({"source", "records", "timeStamp", "data"})
public class QueryResponse<T> {
    private List<T> data;
    private String source;
    private String timeStamp;
    private int records;


    public static class Builder<T> {
        private final QueryResponse<T> instance;

        public Builder() {
            instance = new QueryResponse<>();
        }

        public Builder<T> setData(List<T> data) {
            instance.data = data;
            if (instance.data != null)
                instance.records = instance.data.size();
            return this;
        }

        public Builder<T> setSource(String source) {
            instance.source = source;
            return this;
        }

        public Builder<T> setTimeStamp(String timeStamp) {
            instance.timeStamp = timeStamp;
            return this;
        }


        public QueryResponse<T> build() {
            return instance;
        }
    }
}
