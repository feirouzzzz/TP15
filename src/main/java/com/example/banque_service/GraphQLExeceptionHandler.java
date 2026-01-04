package com.example.banque_service;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class GraphQLExeceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable exception, DataFetchingEnvironment environment) {
        return new GraphQLError() {

            @Override
            public String getMessage() {
                return exception.getMessage();
            }

            @Override
            public List<SourceLocation> getLocations() {
                return Collections.emptyList();
            }

            @Override
            public ErrorClassification getErrorType() {
                return null;
            }
        };
    }
}
