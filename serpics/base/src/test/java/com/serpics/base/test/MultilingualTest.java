/*******************************************************************************
 * Copyright 2014-2016  StepFour Srl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  
 *******************************************************************************/
package com.serpics.base.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Geocode;
import com.serpics.base.data.repositories.GeoCodeRepository;
import com.serpics.i18n.data.model.Locale;
import com.serpics.i18n.data.model.MultilingualString;
import com.serpics.i18n.data.repositories.LocaleRepository;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ 
	 "classpath:META-INF/i18n-serpics.xml",
	"classpath:META-INF/mediasupport-serpics.xml",
	"classpath:META-INF/base-serpics.xml"})
@SerpicsTest("default-store")
@RunWith(SpringJUnit4ClassRunner.class)
public class MultilingualTest  extends AbstractTransactionalJunit4SerpicTest {

    @Autowired
    LocaleRepository localeRepository;
    @Autowired
    GeoCodeRepository geoCodeRepository;

    @Test
    @Transactional
    public void test(){
        final Locale l = new Locale();
        l.setName("italiano");
        l.setlanguage("it");
        l.setCountry("IT");

        localeRepository.saveAndFlush(l);

        final Locale l1 = new Locale();
        l1.setName("inglese");
        l1.setlanguage("en");
        l1.setCountry("EN");

        localeRepository.saveAndFlush(l1);

        Geocode p = new Geocode();
        p.setCode("uno");

        final MultilingualString desc = new MultilingualString(l.getLanguage(), "descrizione");
        desc.addText(l1.getLanguage(), "description");
        p.setDescription(desc);

        p = geoCodeRepository.saveAndFlush(p);

        geoCodeRepository.detach(p);

        final Geocode p1 = geoCodeRepository.findOne(p.getGeocodeId());
        Assert.assertNotNull(p1);
        Assert.assertEquals("descrizione", p1.getDescription().getText(l.getLanguage()));


    }
}
