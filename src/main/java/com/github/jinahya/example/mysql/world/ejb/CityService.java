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


package com.github.jinahya.example.mysql.world.ejb;


import com.github.jinahya.example.mysql.world.persistence.City;
import com.github.jinahya.example.mysql.world.persistence.City_;
import com.github.jinahya.example.mysql.world.persistence.Country;
import com.github.jinahya.example.mysql.world.persistence.Country_;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.validation.constraints.Min;


/**
 *
 * @author Jin Kwon &lt;onacit at gmail.com&gt;
 */
@Stateless
public class CityService {


    /**
     * Selects all or specified range of cities.
     *
     * @param countryCode the {@code country.code} to filter or {@code null}
     * @param firstResult first result index
     * @param maxResults max result count
     *
     * @return a list of {@code City}.
     */
    public List<City> list(final String countryCode,
                           @Min(0)
                           final Integer firstResult,
                           @Min(0)
                           final Integer maxResults) {

        final CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        final CriteriaQuery<City> criteria = builder.createQuery(City.class);

        final Root<City> from = criteria.from(City.class);
        criteria.select(from);
        final Path<Country> country = from.get(City_.country);
        if (countryCode != null) {
            criteria.where(builder.equal(
                country.get(Country_.code), countryCode));
        }
        criteria.orderBy(builder.asc(country.get(Country_.code)),
                         builder.desc(from.get(City_.population)));

        final TypedQuery<City> query = entityManager.createQuery(criteria);
        if (firstResult != null) {
            query.setFirstResult(firstResult);
        }
        if (maxResults != null) {
            query.setMaxResults(maxResults);
        }

        return query.getResultList();
    }


    @PersistenceContext
    private EntityManager entityManager;


}

