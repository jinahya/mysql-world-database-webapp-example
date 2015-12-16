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


import com.github.jinahya.mysql.world.ejb.CityService;
import com.github.jinahya.mysql.world.persistence.City;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.Min;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
@Api(value = "cities")
@Path("/cities")
public class CitiesResource {


    @PostConstruct
    private void constructed() {

        final PathSegment rootPath = uriInfo.getPathSegments().get(0);
        final MultivaluedMap<String, String> matrixParameters
            = rootPath.getMatrixParameters();
        countryCode = matrixParameters.getFirst("countryCode");
    }


    @ApiOperation(value = "Reads cities",
                  response = City.class,
                  responseContainer = "List")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response read(
        @QueryParam(WsRsConstants.PARAM_FIRST_RESULT)
        @DefaultValue(WsRsConstants.DEFAULT_FIRST_RESULT) @Min(0)
        final Integer firstResult,
        @QueryParam(WsRsConstants.PARAM_MAX_RESULTS)
        @DefaultValue(WsRsConstants.DEFAULT_MAX_RESULTS) @Min(0)
        final Integer maxResults) {

        final List<City> list = cityService.list(
            countryCode, firstResult, maxResults);

        final GenericEntity<List<City>> entity
            = new GenericEntity<List<City>>(list) {
        };

        return Response.ok(entity).build();
    }


    @ApiOperation(
        value = "Reads a city identified by {id}",
        response = City.class
    )
    @ApiResponses(
        @ApiResponse(
            code = 404,
            message = "No city identified by {id}"
        )
    )
    @GET
    @Path("/{id: \\d+}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response read(
        @ApiParam(value = "city id", required = true)
        @PathParam("id") final int id) {

        final City city = entityManager.find(City.class, id);
        if (city == null) {
            throw new NotFoundException();
        }

        return Response.ok(city).build();
    }


    @PersistenceContext
    private EntityManager entityManager;


    @EJB
    private CityService cityService;


    @Context
    private UriInfo uriInfo;


    private transient String countryCode;


}

