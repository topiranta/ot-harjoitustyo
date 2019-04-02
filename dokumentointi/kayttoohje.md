# Käyttöohje

Käyttöohje ver 0.1, päivitetty 2.4.2019

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

Sovelluksen lähdekoodin mukana tulee JSON.Simple-kirjasto, joka saattaa täytyä erikseen konfiguroida riippuvuudeksi 
ohjelmointiympäristölle.
