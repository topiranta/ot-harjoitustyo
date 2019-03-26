package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class KassapaateTest {
    
    Kassapaate kassapaate;
    Maksukortti maksukortti;
    
    @Before
    public void setUp() {
        
        kassapaate = new Kassapaate();
        maksukortti = new Maksukortti(300);
        
    }
    
    @Test
    public void kassassaOikeaMaaraRahaaAlussa() {
        
        assertEquals(100000, kassapaate.kassassaRahaa());
        
    }
    
    @Test
    public void rahamaaraKasvaaOikeinOstossa() {
        
        kassapaate.syoEdullisesti(500);
        assertEquals(100240, kassapaate.kassassaRahaa());
        
    }
    
    @Test
    public void oikeaVaihtoRahaOstosta() {
        
        assertEquals(100, kassapaate.syoMaukkaasti(500));
        
    }
    
    @Test
    public void lounaidenMaaraKasvaaOstosta() {
        
        kassapaate.syoEdullisesti(500);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());;
        
    }
    
    @Test
    public void kaikkiRahatTakaisinJosRahaaLiianVahan() {
        
        assertEquals(300, kassapaate.syoMaukkaasti(300));
        
    }
    
    @Test
    public void lounaitaEiMyydaJosRahaaLiianVahan() {
        
        kassapaate.syoMaukkaasti(300);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
        
    }
    
    @Test
    public void kortiltaVeloitetaanRahaa() {
        
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(60, maksukortti.saldo());
        
    }
    
    @Test
    public void maksukorttiPalauttaaTrueOnnistuneestaKorttiostosta() {
        
        assertTrue(kassapaate.syoEdullisesti(maksukortti));
        
    }
    
    @Test
    public void myytyjenMaukkaidenLounaidenMaaraKasvaaJosKortillaRiittavastiRahaa() {
        
        maksukortti.lataaRahaa(200);
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
        
    }
    
    @Test
    public void myytyjenMaukkaidenLounaidenMaaraEiMuutuJosKortillaEiRiittavastiRahaa() {
        
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
        
    }
    
    @Test
    public void falseJosKortinRahatEiRiita() {
        
        assertFalse(kassapaate.syoMaukkaasti(maksukortti));
        
    }
    
    @Test
    public void kortinRahamaaraEiMuutuJosRahatEiRiitaOstoon() {
        
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(300, maksukortti.saldo());
        
    }
    
    @Test
    public void kassanRahamaaraEiMuutuKorttiostonYhteydessa() {
        
        maksukortti.lataaRahaa(5000);
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
        
    }
    
    @Test
    public void kassanRahamaaraKasvaaKorttiaLadatessa() {
        
        kassapaate.lataaRahaaKortille(maksukortti, 500);
        assertEquals(100500, kassapaate.kassassaRahaa());
        
        
    }
    
    @Test
    public void kortinRahamaaraKasvaaKorttiaLadatessa() {
        
        kassapaate.lataaRahaaKortille(maksukortti, 500);
        assertEquals(800, maksukortti.saldo());
        
    }
    
    @Test
    public void negatiivistaArvoaEiLadataKortille() {
        
        kassapaate.lataaRahaaKortille(maksukortti, -700);
        assertEquals(300, maksukortti.saldo());
        
    }
    
    @Test
    public void edullistakaanLounastaEiMyydaJosKortinRahatEiRiita() {
        
        kassapaate.syoEdullisesti(maksukortti);
        assertFalse(kassapaate.syoEdullisesti(maksukortti));
        
    }
    
    @Test
    public void edullistaLounastaEiMyydaLiianPienellaKateismaarallakaan() {
        
        kassapaate.syoEdullisesti(150);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        
    }
}
