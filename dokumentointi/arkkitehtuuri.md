# Arkkitehtuurikuvaus

Arkkitehtuurikuvaus, versio 0.1, päivitetty 2.4.2019

## Päätoiminnallisuudet

Sovellus koostuu tällä hetkellä eriytetystä käyttöliittymästä ja sovelluslogiikasta. Lisäksi sovelluslogiikalla on 
käytössään valo-ohjainta kuvaava Bridge-luokka sekä Utils-luokka, jossa pidetään yleisiä staattisia metodeja, kuten 
yhteydenottoa valo-ohjaimeen http-protokollan yli.

## Käyttöliittymä (TextUi.java)

Sovellus käyttää vielä tekstipohjaista käyttöliittymää, jossa on kuusi toimintoa:

* Uuden valo-ohjaimen asettaminen ja konfigurointi
* Valo-ohjaimen nimen asettaminen
* Valo-ohjaimen IP-osoitteen asettaminen
* Valo-ohjaimen statuksen tulostaminen
* Käyttöliittymäkomentojen listaaminen
* Poistuminen sovelluksesta

Käyttöliittymälle annetaan sekä Scanner-olio että sovelluslogiikka (Application-olio).

## Sovelluslogiikka (Application.java)

Sovelluslogiikka hallitsee yhtä valo-ohjainta ja välittää käyttöliittymän komennot valo-ohjaimelle. Uutta 
valo-ohjainta asetettaessa sovelluslogiikka käyttää Utils-luokan staattisia metodeja saadakseen verkossa olevalta 
valo-ohjaimelta uuden autentikointiavaimen sovellukselle.

## Valo-ohjain (Bridge.java)

Valo-ohjaimelle voi asettaa nimen, ip-osoitteen sekä autentikointiavaimen fyysiseen valo-ohjaimeen. Bridge-luokka 
osaa perustaa sovellukseen Bridge-olion myös ilman autentikointiavainta. 

## Utils.java

Utils-luokka sisältää yleisiä työkaluja muuan muassa POST- ja GET-kutsujen tekoon ja yhteydenottoon http-protokollan 
yli valo-ohjaimelle.

### postMessage

postMessage-metodi tekee POST-kutsun sille parametrina annettuun url-osoitteeseen. Myös varsinainen 
JSON-muotoinen POST-viesti annetaan metodille parametrina. Metodi palauttaa sen saaman JSON-muotoisen vastauksen.

### authenticateApplication

Metodi käyttää postMessage-metodia autentikoidakseen sovelluksen käyttäjän valo-ohjaimelle. Metodi osaa heittää 
virheen autentikoinnin epäonnistuessa. Autentikoinnin epäonnistuminen ei kuitenkaan estä perustamasta Bridge-oliota 
sovellukseen.

## Kolmannen osapuolen kirjastot

### JSON.Simple

Sovellus käyttää JSON-muotoisen datan parsimiseen JSON.Simple-kirjastoa. Kirjasto on lisensoitu [Apache 
2.0](http://www.apache.org/licenses/LICENSE-2.0) -lisenssillä ja se on vapaata lähdekoodia. Kirjasto löytyy myös 
[tämän sovelluksen 
repositoriosta](https://github.com/topiranta/ot-harjoitustyo/tree/master/light-application/external-libraries/json-simple). 
Kirjastoa ei ole muokattu tätä sovellusta varten.

[JSON.Simple -kirjaston dokumentointi](https://code.google.com/archive/p/json-simple/)

[JSON.Simple -kirjasto GitHubissa](https://github.com/fangyidong/json-simple)

## Pysyväistallennus

Sovelluksessa ei ole vielä pysyväistallennusta.
