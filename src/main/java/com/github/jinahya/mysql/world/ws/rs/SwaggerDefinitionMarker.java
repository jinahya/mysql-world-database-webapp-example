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


import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;


/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@SwaggerDefinition(
    info = @Info(
        description = "MySQL World Database API d",
        version = "1",
        title = "MySQL World Database API t",
        contact = @Contact(
            name = "Jin Kwon",
            email = "onacit@gmail.com",
            url = "http://jinahya.wordpress.com"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "http://www.apache.org/licenses/LICENSE-2.0"
        )
    )
)
public interface SwaggerDefinitionMarker {

    // this is just a maker interface
}

