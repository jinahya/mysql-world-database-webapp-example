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


import com.github.jinahya.example.mysql.world.persistence.City;
import com.github.jinahya.example.mysql.world.persistence.Country;
import com.github.jinahya.example.mysql.world.persistence.CountryLanguage;
import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;


/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Path("/world.xsd")
public class WorldXsdResource {


    @GET
    @Produces({MediaType.APPLICATION_XML})
    public Response read() throws JAXBException {

        final JAXBContext context = JAXBContext.newInstance(
            City.class, Country.class, CountryLanguage.class);

        return Response.ok((StreamingOutput) (output -> {
            context.generateSchema(new SchemaOutputResolver() {

                @Override
                public Result createOutput(final String namespaceUri,
                                           final String suggestedFileName)
                    throws IOException {

                    final Result result = new StreamResult(output);
                    result.setSystemId(
                        uriInfo.getAbsolutePath().toURL().toString());

                    return result;
                }

            });
        })).build();
    }


    @Context
    private UriInfo uriInfo;


}

