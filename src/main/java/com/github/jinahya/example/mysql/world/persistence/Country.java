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


package com.github.jinahya.example.mysql.world.persistence;


import java.io.Serializable;
import javax.persistence.AttributeConverter;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Converter;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Entity
@Table(name = "Country")
@XmlRootElement
@XmlType(propOrder = {"code", "name", "continent", "region", "surfaceArea",
                      "indepYear", "population", "lifeExpectancy", "gnp",
                      "gnpOld", "localName", "governmentForm", "headOfState",
                      "capital", "code2"})
public class Country implements Serializable {


    @XmlEnum
    public static enum Continent {


        ASIA("Asia"),
        EUROPE("Europe"),
        NORTH_AMERICA("North America"),
        AFRICA("Africa"),
        OCEANIA("Oceania"),
        ANTARCTICA("Antarctica"),
        SOUTH_AMERICA("South America");


        private Continent(final String dbData) {

            this.dbData = dbData;
        }


        private String dbData;


    }


    @Converter
    public static class ContinentConverter
        implements AttributeConverter<Continent, String> {


        @Override
        public String convertToDatabaseColumn(final Continent attribute) {

            return attribute.dbData;
        }


        @Override
        public Continent convertToEntityAttribute(final String dbData) {

            for (final Continent value : Continent.values()) {
                if (value.dbData.equals(dbData)) {
                    return value;
                }
            }

            throw new IllegalArgumentException("no dbData matches: " + dbData);
        }


    }


    // -------------------------------------------------------------------- code
    public String getCode() {

        return code;
    }


    public void setCode(final String code) {

        this.code = code;
    }


    @Column(name = "Code")
    @Id
    @NotNull
    @XmlElement(required = true)
    private String code;


    @Column(name = "Name")
    @NotNull
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlElement(required = true)
    private String name;


    @Column(name = "Continent")
    @Convert(converter = ContinentConverter.class)
    @XmlElement(required = true)
    private Continent continent = Continent.ASIA;


    @Column(name = "Region")
    @NotNull
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    private String region;


    @Column(name = "SurfaceArea")
    @XmlElement(required = true)
    private float surfaceArea;


    @Column(name = "IndepYear")
    @XmlElement(nillable = true, required = true)
    private Integer indepYear;


    @Column(name = "Population")
    @XmlElement(required = true)
    private int population;


    @Column(name = "LifeExpectancy")
    @XmlElement(nillable = true, required = true)
    private Float lifeExpectancy;


    @Column(name = "GNP")
    @XmlElement(nillable = true, required = true)
    private Float gnp;


    @Column(name = "GNPOld")
    @XmlElement(nillable = true, required = true)
    private Float gnpOld;


    @Column(name = "LocalName")
    @NotNull
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    private String localName;


    @Column(name = "GovernmentForm")
    @NotNull
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    private String governmentForm;


    @Column(name = "HeadOfState")
    @XmlElement(nillable = true, required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    private String headOfState;


    @Column(name = "Capital")
    @XmlElement(nillable = true, required = true)
    private Integer capital;


    @Column(name = "Code2")
    @NotNull
    @XmlElement(required = true)
    private String code2;


}

