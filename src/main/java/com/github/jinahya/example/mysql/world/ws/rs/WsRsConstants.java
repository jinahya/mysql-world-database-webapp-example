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


/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
public final class WsRsConstants {


    public static final String PARAM_FIRST_RESULT = "firstResult";


    public static final String PARAM_MAX_RESULTS = "maxResults";


    public static final String DEFAULT_FIRST_RESULT = "0";


    public static final String DEFAULT_MAX_RESULTS = "1024";


    public static final int MIN_FIRST_RESULT = 0;


    public static final int MAX_FIRST_RESULT = Integer.MAX_VALUE;


    public static final int MIN_MAX_RESULTS = 0;


    public static final int MAX_MAX_RESULTS = 1024;


    private WsRsConstants() {

        super();
    }


}

