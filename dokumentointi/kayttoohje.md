# Käyttöohje

*Käyttöohje ver 0.2, 9.4.2019*

## Sovelluksen käyttäjälle

Sovellus käynnistää tekstikäyttöliittymän, jossa on mahdollista asettaa sovelluksen kanssa samassa paikallisverkossa 
oleva Philips Hue -älyvalo-ohjain sovellukseen. 

Käyttäjä voi konfiguroida sovellukseen uuden valo-ohjaimen. Konfigurointivaiheessa sovellus kysyy valo-ohjaimen osoitetta sisäverkossa sekä valo-ohjaimelle annettavaa nimeä.

Näiden tietojen antamisen jälkeen käyttäjän on painettava valo-ohjaimen painiketta, jonka jälkeen ohjain sallii 
autentikoitumisen. Tässä vaiheessa käyttäjä voi jatkaa autentikoitumisvaiheeseen sovelluksessa ja sovellus ilmoittaa 
autentikoinnin onnistumisesta.

Vaikka autentikointi ei onnistuisi, tallentaa sovellus kuitenkin nimi- ja ip-osoite-tiedon.

Konfiguroinnin jälkeen valo-ohjaimen nimeä ja ip-osoitetta voi muuttaa.

## Sovelluksen testaajalle

Sovelluksessa ei ole vielä erillistä emulointia valo-ohjaimelle, joten sen autentikointi vaatii Hue-ohjainta 
lähiverkkoon. Sovelluksessa voi silti tehdä epäonnistuneen autentikoinnin ja sen käyttöliittymää voi käyttää.

## Lähdekoodin käyttäjälle

### Riippuvuudet (Dependencies)

Sovelluksen lähdekoodin mukana tulee JSON.Simple-kirjasto, joka on osoitettu riippuvuudeksi pom.xml -tiedostossa. Riippuvuutta ei tarvitse osoittaa erikseen ohjelmointiympäristölle.

### Main-luokan asettaminen

NetBeans ei välttämättä tunnista ensimmäisellä suorituskerralla Main-luokkaa sovellusta ajettaessa. Luokan voi osoittaa NetBeansille klikkaamalla projektia Projects-välilehdellä hiiren oikealla ja valitsemalla valikosta "Properties". Main-luokan voi asettaa avautuvan ikkunan kohdassa Run, jonka Main Class -riville tallennetaan arvo "topiranta.lightapplication.Main".
