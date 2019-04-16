# Käyttöohje

*Käyttöohje ver 0.3, 16.4.2019*

## Sovelluksen käyttäjälle

Sovellus käynnistää tekstikäyttöliittymän, jossa on mahdollista:

* Asettaa ja autentikoida sovellus lähiverkossa olevalle Philips Hue -valo-ohjaimelle
* Muuttaa edellisiä konfigurointeja
* Hakea kaikki konfiguroituun valo-ohjaimeen liitetyt älyvalot
* Sammuttaa kaikki haetut älyvalot
* Tallentaa valo-ohjaimen konfigurointi
* Ladata tallennettu ohjainkonfigurointi

### Valo-ohjaimen konfigurointi

Käyttäjän on tiedettävä lähiverkossa olevan valo-ohjaimen IP-osoite. Käyttäjä syöttää tekstikäyttöliittymästä sovellukselle valo-ohjaimen IP-osoitteen lähiverkossa ja antaa ohjaimelle nimen. Tämän jälkeen käyttäjän on painettava valo-ohjaimen päällä olevaa yhdistämispainiketta, jonka jälkeen käyttäjä voi autentikoida sovelluksen valo-ohjaimelle.

### Lamppujen hakeminen

Kun valo-ohjain on konfiguroitu, voi käyttäjä hakea kaikki ohjaimeen kytketyt lamput.

### Lamppujen sammuttaminen

Kaikki lamput voi sammuttaa, kunhan ne on ensin haettu ohjaimelta.

## Sovelluksen testaajalle

Sovelluksessa on mahdollista syöttää testi-ip-osoite käyttäjätestausta varten silloin, kun lähiverkossa ei ole käytettävissä valo-ohjainta tai siihen liityettyjä älyvaloja. Testi-ip on 0.0.0.0 ja se annetaan valo-ohjainta konfiguroidessa. Sovellus osaa tällöin olla ottamatta yhteyttä verkkoyhteyden yli ja siinä on mahdollista emuloida valojen hakemista valo-ohjaimelta ja konfigurointien tallentamista.

Toisin sanoen testausmoodissa käyttöliittymän kaikki toiminnot ovat käytettävissä.

## Lähdekoodin käyttäjälle

### Riippuvuudet (Dependencies)

Sovelluksen lähdekoodin mukana tulee JSON.Simple-kirjasto, joka on osoitettu riippuvuudeksi pom.xml -tiedostossa. Riippuvuutta ei tarvitse osoittaa erikseen ohjelmointiympäristölle.

### Main-luokan asettaminen

NetBeans ei välttämättä tunnista ensimmäisellä suorituskerralla Main-luokkaa sovellusta ajettaessa. Luokan voi osoittaa NetBeansille klikkaamalla projektia Projects-välilehdellä hiiren oikealla ja valitsemalla valikosta "Properties". Main-luokan voi asettaa avautuvan ikkunan kohdassa Run, jonka Main Class -riville tallennetaan arvo "topiranta.lightapplication.Main".
