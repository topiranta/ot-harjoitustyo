# Käyttöohje

*Käyttöohje ver 0.2, 9.4.2019*

## Sovelluksen käyttäjälle

Sovellus käynnistää tekstikäyttöliittymän, jossa on mahdollista:

* Asettaa ja autentikoida sovellus lähiverkossa olevalle Philips Hue -valo-ohjaimelle
* Muuttaa edellisiä konfigurointeja
* Hakea kaikki konfiguroituun valo-ohjaimeen liitetyt älyvalot
* Sammuttaa kaikki haetut älyvalot

### Valo-ohjaimen konfigurointi

Käyttäjän on tiedettävä lähiverkossa olevan valo-ohjaimen IP-osoite. Käyttäjä syöttää tekstikäyttöliittymästä sovellukselle valo-ohjaimen IP-osoitteen lähiverkossa ja antaa ohjaimelle nimen. Tämän jälkeen käyttäjän on painettava valo-ohjaimen päällä olevaa yhdistämispainiketta, jonka jälkeen käyttäjä voi autentikoida sovelluksen valo-ohjaimelle.

### Lamppujen hakeminen

Kun valo-ohjain on konfiguroitu, voi käyttäjä hakea kaikki ohjaimeen kytketyt lamput.

### Lamppujen sammuttaminen

Kaikki lamput voi sammuttaa, kunhan ne on ensin haettu ohjaimelta.

## Sovelluksen testaajalle

Sovelluksessa ei ole vielä erillistä emulointia valo-ohjaimelle, joten sen autentikointi vaatii Hue-ohjainta 
lähiverkkoon. Sovelluksessa voi silti tehdä epäonnistuneen autentikoinnin ja sen käyttöliittymää voi käyttää.

## Lähdekoodin käyttäjälle

### Riippuvuudet (Dependencies)

Sovelluksen lähdekoodin mukana tulee JSON.Simple-kirjasto, joka on osoitettu riippuvuudeksi pom.xml -tiedostossa. Riippuvuutta ei tarvitse osoittaa erikseen ohjelmointiympäristölle.

### Main-luokan asettaminen

NetBeans ei välttämättä tunnista ensimmäisellä suorituskerralla Main-luokkaa sovellusta ajettaessa. Luokan voi osoittaa NetBeansille klikkaamalla projektia Projects-välilehdellä hiiren oikealla ja valitsemalla valikosta "Properties". Main-luokan voi asettaa avautuvan ikkunan kohdassa Run, jonka Main Class -riville tallennetaan arvo "topiranta.lightapplication.Main".
