package com.serpics.base.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.serpics.base.data.model.Geocode;
import com.serpics.base.data.model.Locale;
import com.serpics.base.data.model.MultilingualString;
import com.serpics.base.data.repositories.GeoCodeRepository;
import com.serpics.base.data.repositories.LocaleRepository;

public class MultilingualTest extends BaseTest {

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

        p = geoCodeRepository.create(p);

        geoCodeRepository.detach(p);

        final Geocode p1 = geoCodeRepository.findOne(p.getGeocodeId());
        Assert.assertNotNull(p1);
        Assert.assertEquals("descrizione", p1.getDescription().getText(l.getLanguage()));


    }
}
