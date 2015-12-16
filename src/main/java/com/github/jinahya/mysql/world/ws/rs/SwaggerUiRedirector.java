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


import java.io.IOException;
import java.net.URI;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;


/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Path("/swagger-ui")
public class SwaggerUiRedirector {


    @GET
    public Response read() throws IOException {

        // ...://.../<context-root>/<application-path>/swagger.json
        final URI swaggerJson
            = uriInfo.getBaseUriBuilder()
            .path("swagger.json")
            .build();

        // ...://.../<context-root>
        final URI contextRoot
            = URI.create(servletRequest.getRequestURL().toString())
            .resolve(servletRequest.getContextPath());

        // ...://.../<context-root>/swagger-ui/index.html?url=swaggerJson
        final URI swaggerUi
            = UriBuilder.fromUri(contextRoot)
            .path("swagger-ui")
            .path("index.html")
            .queryParam("url", swaggerJson).build();

        // Post/Redirect/Get
        return Response.seeOther(swaggerUi).build();
    }


    @Context
    private UriInfo uriInfo;


    @Context
    private HttpServletRequest servletRequest;


}

