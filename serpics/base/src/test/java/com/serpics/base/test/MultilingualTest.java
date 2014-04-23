package com.serpics.base.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.serpics.base.persistence.Locale;
import com.serpics.base.persistence.MultilingualString;
import com.serpics.base.persistence.Paymethod;
import com.serpics.base.repositories.LocaleRepository;
import com.serpics.base.repositories.PaymethodRepository;

public class MultilingualTest extends BaseTest {

    @Autowired
    LocaleRepository localeRepository;
    @Autowired
    PaymethodRepository paymethodRepository;

    @Test
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

        Paymethod p = new Paymethod();
        p.setName("uno");

        final MultilingualString desc = new MultilingualString(l.getLanguage(), "descrizione");
        desc.addText(l1.getLanguage(), "description");
        p.setDescription(desc);

        p = paymethodRepository.saveAndFlush(p);

        paymethodRepository.detach(p);

        final Paymethod p1 = paymethodRepository.findOne(p.getPaymethodId());
        Assert.assertNotNull(p1);
        Assert.assertEquals("descrizione", p1.getDescription().getText(l.getLanguage()));

        final Paymethod p2 = paymethodRepository.findOne(paymethodRepository.makeSpecification(new Paymethod("uno")));
        Assert.assertNotNull(p1);
        Assert.assertEquals("descrizione", p1.getDescription().getText(l.getLanguage()));


    }
}
