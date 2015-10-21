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


package com.github.jinahya.example.mysql.world.ws.rs;


import com.github.jinahya.example.mysql.world.ejb.CountryLanguageService;
import com.github.jinahya.example.mysql.world.persistence.CountryLanguage;
import java.util.List;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Path("/countryLanguages")
public class CountryLanguagesResource {


    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response read(
        @MatrixParam("country_code")
        final String countryCode) {

        final List<CountryLanguage> list
            = countryLanguageService.list(countryCode, null, null);

        final GenericEntity<List<CountryLanguage>> entity
            = new GenericEntity<List<CountryLanguage>>(list) {
            };

        return Response.ok(entity).build();
    }


    @PersistenceContext
    private EntityManager entityManager;


    @EJB
    private CountryLanguageService countryLanguageService;


}

