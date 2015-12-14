/*
 * Copyright 2015 Jin Kwon &lt;onacit at gmail.com&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.github.jinahya.mysql.world.ws.rs;


import com.github.jinahya.mysql.world.ejb.CountryLanguageService;
import com.github.jinahya.mysql.world.persistence.CountryLanguage;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.validation.constraints.Min;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Path("/countryLanguages")
public class CountryLanguagesResource {


    @PostConstruct
    private void constructed() {

        final PathSegment rootPath = uriInfo.getPathSegments().get(0);
        final MultivaluedMap<String, String> matrixParameters
            = rootPath.getMatrixParameters();
        countryCode = matrixParameters.getFirst("countryCode");
    }


    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response read(
        @QueryParam(WsRsConstants.PARAM_FIRST_RESULT)
        @DefaultValue(WsRsConstants.DEFAULT_FIRST_RESULT) @Min(0)
        final Integer firstResult,
        @QueryParam(WsRsConstants.PARAM_MAX_RESULTS)
        @DefaultValue(WsRsConstants.DEFAULT_MAX_RESULTS) @Min(0)
        final Integer maxResults) {

        final List<CountryLanguage> list = countryLanguageService.list(
            countryCode, firstResult, maxResults);

        final GenericEntity<List<CountryLanguage>> entity
            = new GenericEntity<List<CountryLanguage>>(list) {
            };

        return Response.ok(entity).build();
    }


    @Context
    private UriInfo uriInfo;


    @EJB
    private CountryLanguageService countryLanguageService;


    private transient String countryCode;


}

