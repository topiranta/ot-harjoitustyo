# Arkkitehtuurikuvaus

*Arkkitehtuurikuvaus, ver 0.2, 9.4.2019*

Sovellus koostuu tällä hetkellä Main-metodin lisäksi eriytetystä käyttöliittymästä sekä sovelluslogiikasta, Hue-laitteita kuvaavista luokista sekä staattisia metodeja sisältävästä Utils-luokasta. Alla on kuvattu pääpiirteittäin sovelluksen paketit ja niissä olevat luokat, muut kuin kurssin vaatimat kolmannen osapuolen kirjastoriippuvuudet sekä muutamia käyttötapauksia sekvenssikaaviona.

## topiranta.lightapplication (Main.java)

Paketissa on Main-metodi, joka luo sovelluslogiikan ja antaa sen käynnistettävän käyttöliittymän käyttöön yhdessä Scanner-olion kanssa.

## topiranta.lightapplication.logics (Application.java)

Paketissa on varsinainen sovelluslogiikka, joka ylläpitää tietoa Hue-valo-ohjaimesta sekä siihen liitetyistä lampuista. Sovelluslogiikka käyttää topiranta.lightapplication.connections-paketissa olevaa Utils-luokkaa, jonka avulla se ottaa yhteyden valo-ohjaimeen ja suorittaa siellä operaatioita. Sovelluslogiikalle tallennetaan oliomuuttujina valo-ohjain (Bridge) sekä lista mainittuun valo-ohjaimeen liitetyistä lampuista (ArrayList<Lamp>).

Sovelluslogiikka osaa:

* Autentikoida sovelluksen valo-ohjaimelle ja tallentaa valo-ohjaimen tiedot ajonaikaiseen muistiin
* Hakea valo-ohjaimeen kytketyt lamput
* Sammuttaa haetut lamput

## topiranta.lightapplication.devices (Bridge.java, Lamp.java)

Paketti sisältää Hue-valo-ohjainta ja lamppua kuvaavat luokat. Bridge-luokalle tallennetaan konstruktorissa IP-osoite, nimi sekä sovelluskohtainen autentikointiavain. Lampulle tallennetaan sen id-numero valo-ohjaimessa sekä valo-ohjain (Bridge), johon se liittyy.

Lamppu tarjoaa metodin oman PUT-urlinsa hakemiseksi ja lamppu osaa Utils-luokan yhteydenottometodeja hyödyntäen sammuttaa itsensä.

## topiranta.lightapplication.connections (Utils.java)

Utils-luokka sisältää staattisina metodeina yleisiä työkaluja muuan muassa POST-, PUT- ja GET-kutsujen tekoon sekä yhteydenottoon http-protokollan 
yli valo-ohjaimelle.

### getAllLamps

Metodi ottaa yhteyden sille parametrina annettuun valo-ohjaimeen ja hakee kaikki siihen kytketyt lamput. Metodi palauttaa löytyneet lamput ArrayListina.

### authenticateApplication

Metodi käyttää postMessage-metodia autentikoidakseen sovelluksen käyttäjän valo-ohjaimelle. Metodi osaa heittää 
virheen autentikoinnin epäonnistuessa. Autentikoinnin epäonnistuminen ei kuitenkaan estä perustamasta Bridge-oliota 
sovellukseen.



### postMessage

postMessage-metodi tekee POST-kutsun sille parametrina annettuun url-osoitteeseen. Myös varsinainen 
JSON-muotoinen POST-viesti annetaan metodille parametrina. Metodi palauttaa sen saaman JSON-muotoisen vastauksen.

### getJSON

Metodi hakee parametrina annetusta urlista halutun tiedon ja palauttaa sen JSONObjectina sitä kutsuneelle metodille.

### putJSON

Metodi välittää sille merkkijonoparametrina annetun viestin haluttuun urliin.

### openNewConnetion, writeMessage, getResponse

Metodit osaavat ottaa yhteyden ja lähettää viestejä ja hakea paluuarvoja yhteyden yli.

## Käyttöliittymä (TextUi.java)

Sovellus käyttää vielä tekstipohjaista käyttöliittymää, jossa on kuusi toimintoa:

* Uuden valo-ohjaimen asettaminen ja konfigurointi
* Valo-ohjaimen nimen asettaminen
* Valo-ohjaimen IP-osoitteen asettaminen
* Valo-ohjaimen statuksen tulostaminen
* Käyttöliittymäkomentojen listaaminen
* Kaikkien valojen hakeminen
* Kaikkien valojen sammuttaminen
* Poistuminen sovelluksesta

Käyttöliittymälle annetaan sekä Scanner-olio että sovelluslogiikka (Application-olio).

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
