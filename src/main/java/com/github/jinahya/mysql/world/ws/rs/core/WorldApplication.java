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


package com.github.jinahya.mysql.world.ws.rs.core;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.github.jinahya.mysql.world.ws.rs.CitiesResource;
import com.github.jinahya.mysql.world.ws.rs.CountriesResource;
import com.github.jinahya.mysql.world.ws.rs.CountryLanguagesResource;
import com.github.jinahya.mysql.world.ws.rs.WorldXsdResource;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import io.swagger.util.Json;
import io.swagger.util.Yaml;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@ApplicationPath("/api")
public class WorldApplication extends Application {


    public WorldApplication() {

        super();

//        BeanConfig beanConfig = new BeanConfig();
//        beanConfig.setTitle("world");
//        beanConfig.setVersion("1.0.0");
//        beanConfig.setSchemes(new String[]{"http"});
//        beanConfig.setHost("localhost:8080");
//        beanConfig.setBasePath("world/api");
//        beanConfig.setResourcePackage("com.github.jinahya.mysql.world.ws.rs");
//        beanConfig.setScan(true);
        final ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.readValue(
                getClass().getResource("/bean-config.json"), BeanConfig.class);
        } catch (final IOException ioe) {
            ioe.printStackTrace(System.err);
        }

        Json.mapper().registerModule(new JaxbAnnotationModule());
        Yaml.mapper().registerModule(new JaxbAnnotationModule());
    }


    @Override
    public Set<Class<?>> getClasses() {

        final Set<Class<?>> classes = new HashSet();

        classes.add(CitiesResource.class);
        classes.add(CountriesResource.class);
        classes.add(CountryLanguagesResource.class);
        classes.add(WorldXsdResource.class);

        classes.add(ApiListingResource.class);
        classes.add(SwaggerSerializers.class);

        return classes;
    }


}

