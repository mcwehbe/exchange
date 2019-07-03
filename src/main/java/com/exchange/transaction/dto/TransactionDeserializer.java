package com.exchange.transaction.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class TransactionDeserializer extends JsonDeserializer<Transaction> {
    @Override
    public Transaction deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode jsonNode = objectCodec.readTree(jsonParser);

        return new Transaction(
                jsonNode.get("exchangeRate").get("currencyPair").asText(),
                jsonNode.get("exchangeRate").get("rate").asDouble(),
                jsonNode.get("orderType").get("name").asText(),
                jsonNode.get("amount").asDouble()
        );
    }
}
