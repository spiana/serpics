package com.serpics.base.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Geocode;
import com.serpics.base.data.model.Locale;
import com.serpics.base.data.model.MultilingualString;
import com.serpics.base.data.repositories.GeoCodeRepository;
import com.serpics.base.data.repositories.LocaleRepository;
import com.serpics.stereotype.SerpicsTest;
import com.serpics.test.AbstractTransactionalJunit4SerpicTest;

@ContextConfiguration({ "classpath:META-INF/base-serpics.xml"})
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
