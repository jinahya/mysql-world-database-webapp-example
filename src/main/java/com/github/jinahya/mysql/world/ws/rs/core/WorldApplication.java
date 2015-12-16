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
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.util.Json;
import io.swagger.util.Yaml;
import java.io.IOException;
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

        final ObjectMapper mapper = new ObjectMapper();
        try {
            final BeanConfig config = mapper.readValue(
                getClass().getResource("/swagger/config.json"),
                BeanConfig.class);
        } catch (final IOException ioe) {
            ioe.printStackTrace(System.err);
        }

        Json.mapper().registerModule(new JaxbAnnotationModule());
        Yaml.mapper().registerModule(new JaxbAnnotationModule());
    }


}

