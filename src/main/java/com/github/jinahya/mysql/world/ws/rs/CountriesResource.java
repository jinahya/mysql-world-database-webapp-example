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
import com.github.jinahya.mysql.world.ejb.CountryLanguageService;
import com.github.jinahya.mysql.world.persistence.City;
import com.github.jinahya.mysql.world.persistence.Country;
import com.github.jinahya.mysql.world.persistence.CountryLanguage;
import com.github.jinahya.mysql.world.persistence.Country_;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Api(value = "countries")
@Path("/countries")
public class CountriesResource {


    @ApiOperation(value = "Reads countries",
                  response = Country.class,
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

        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        final CriteriaQuery<Country> criteria
            = builder.createQuery(Country.class);
        final Root<Country> from = criteria.from(Country.class);
        criteria.select(from);
        criteria.orderBy(builder.asc(from.get(Country_.code)));

        final TypedQuery<Country> query = entityManager.createQuery(criteria);
        if (firstResult != null) {
            query.setFirstResult(firstResult);
        }
        if (maxResults != null) {
            query.setMaxResults(maxResults);
        }
        final List<Country> list = query.getResultList();

        final GenericEntity<List<Country>> entity
            = new GenericEntity<List<Country>>(list) {
        };

        return Response.ok(entity).build();
    }


    @ApiOperation(value = "Reads a country identified by {code}",
                  response = Country.class)
    @ApiResponses(
        @ApiResponse(code = 404, message = "No country identified by {code}")
    )
    @GET
    @Path("/{code: [A-Z]{3}}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response readSingle(
        @ApiParam(value = "country code", required = true)
        @PathParam("code")
        final String code) {

        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        final CriteriaQuery<Country> criteria
            = builder.createQuery(Country.class);
        final Root<Country> from = criteria.from(Country.class);
        criteria.select(from);
        criteria.where(builder.equal(from.get(Country_.code), code));

        final TypedQuery<Country> query = entityManager.createQuery(criteria);
        final Country single;
        try {
            single = query.getSingleResult();
        } catch (final NoResultException nre) {
            throw new NotFoundException();
        }

        return Response.ok(single).build();
    }


    @GET
    @Path("/{code: [A-Z]{3}}/cities")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response readSingleCities(
        @PathParam("code")
        final String countryCode,
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


    @GET
    @Path("/{code: [A-Z]{3}}/countryLanguages")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response readSingleLanguages(
        @PathParam("code")
        final String countryCode,
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


    @PersistenceContext
    private EntityManager entityManager;


    @EJB
    private CityService cityService;


    @EJB
    private CountryLanguageService countryLanguageService;


}

