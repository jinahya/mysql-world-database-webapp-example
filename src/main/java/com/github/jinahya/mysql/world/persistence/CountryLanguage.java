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


package com.github.jinahya.mysql.world.persistence;


import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Entity
@Table(name = "CountryLanguage")
@XmlRootElement
@XmlType(propOrder = {"language", "isOfficial", "percentage"})
public class CountryLanguage implements Serializable {


    @XmlAttribute
    public String getCountryCode() {

        if (country == null) {
            return null;
        }

        return country.getCode();
    }


    @Id
    @JoinColumn(name = "CountryCode", nullable = false)
    @ManyToOne
    @XmlTransient
    private Country country;


    @Column(name = "Language", nullable = false)
    @Id
    @NotNull
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    private String language;


    @Column(name = "IsOfficial", nullable = false)
    @Convert(converter = BooleanTFConverter.class)
    @NotNull
    @XmlElement(required = true)
    private Boolean isOfficial;


    @Column(name = "Percentage", nullable = false)
    @XmlElement(required = true)
    private float percentage;


}

