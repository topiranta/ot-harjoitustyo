package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        
        assertEquals("saldo: 0.10", kortti.toString());
        
    }
    
    @Test
    public void lataaminenKasvattaaSaldoaOikein() {
        
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 10.10", kortti.toString());
        
    }
    
    @Test
    public void saldoVaheneeJosRahaaOnTarpeeksi() {
        
        kortti.lataaRahaa(1000);
        kortti.otaRahaa(610);
        
        assertEquals(400, kortti.saldo());
        
    }
    
    @Test
    public void saldoEiVahaneJosRahaaEiOleTarpeeksi() {
        
        kortti.otaRahaa(20);
        
        assertEquals("saldo: 0.10", kortti.toString());
        
    }
    
    @Test
    public void trueJosMaksuOnnistuu() {
        
        assertTrue(kortti.otaRahaa(5));
        
    }
    
    @Test
    public void falseJosMaksuEiOnnistu() {
        
        assertFalse(kortti.otaRahaa(11));
    }
}
