# Arkkitehtuurikuvaus

*Arkkitehtuurikuvaus, ver 0.4, 16.4.2019*

## Sovelluksen pakettirakenne

Sovelluksella on viisi pakettia, joiden rakenne on seuraava:

![Pakettirakenne](https://github.com/topiranta/ot-harjoitustyo/blob/master/dokumentointi/kuvat/light-application-packages.png?raw=true)

### topiranta.lightapplication (Main.java)

Paketissa on Main-metodi, joka luo sovelluslogiikan ja antaa sen käynnistettävän käyttöliittymän käyttöön yhdessä Scanner-olion kanssa.

### topiranta.lightapplication.logics (Application.java, ConfigurationOperations.java, DeviceOperations.java, UserTestMode.java)

Paketissa on varsinainen sovelluslogiikka, joka ylläpitää tietoa Hue-valo-ohjaimesta sekä siihen liitetyistä lampuista.Sovelluslogiikalle tallennetaan oliomuuttujina valo-ohjain (Bridge) sekä lista mainittuun valo-ohjaimeen liitetyistä lampuista (ArrayList<Lamp>). Sovelluslogiikan lisäksi paketissa on kaksi sovelluksen ulkopuolisia operaatioita hoitavaa luokkaa: ConfigurationOperations hoitaa konfiguraatioiden tallentamisen pysyväistallennukseen ja DeviceOperations hoitaa kommunikaatiot valo-ohjaimen kanssa. Molemmat luokat käyttävät yleisessä Utils-paketissa olevia yleisiä staattisia tallennus- ja yhteydenottometodeja tehtäviensä hoitamiseen.
  
Lisäksi paketissa on UserTestMode.java, jossa on metodeja, jotka auttavat sovellusta tuottamaan käyttäjätestauksen sellaisille käyttäjille, joilla ei ole lähiverkossa fyysistä valo-ohjainta tai Hue-valoja.

Sovelluslogiikka osaa:

* Autentikoida sovelluksen valo-ohjaimelle ja tallentaa valo-ohjaimen tiedot ajonaikaiseen muistiin
* Hakea valo-ohjaimeen kytketyt lamput
* Sammuttaa haetut lamput
* Tallentaa valo-ohjaimen konfiguraation pysyväismuistiin (tekstitiedosto)
* Ladata tallennetun valo-ohjainkonfiguraation

### topiranta.lightapplication.devices (Bridge.java, Lamp.java)

Paketti sisältää Hue-valo-ohjainta ja lamppua kuvaavat luokat. Bridge-luokalle tallennetaan konstruktorissa IP-osoite, nimi sekä sovelluskohtainen autentikointiavain. Lampulle tallennetaan sen id-numero valo-ohjaimessa sekä valo-ohjain (Bridge), johon se liittyy.

Lamppu tarjoaa metodin oman PUT-urlinsa hakemiseksi ja lamppu osaa Utils-luokan yhteydenottometodeja hyödyntäen sammuttaa itsensä.

## topiranta.lightapplication.utils (Connections.java, LocalFiles.java)

Utils-paketti sisältää staattiset metodit pysyväistallennukseen ja -lataukseen sekä URL-yhteydenottoihin. Kaikki tämän paketin metodit ovat valosovelluksen logiikasta irrallisia yleisiä metodeja, joten niitä voisi sellaisenaan käyttää minkä tahansa muun sovelluksen kanssa, joka vastaavia toimintoja käyttää.

## topiranta.lightapplication.ui (TextUi.java)

Sovellus käyttää vielä tekstipohjaista käyttöliittymää, jossa on seuraavat toiminnot:

* Uuden valo-ohjaimen asettaminen ja konfigurointi
* Valo-ohjaimen nimen asettaminen
* Valo-ohjaimen IP-osoitteen asettaminen
* Valo-ohjaimen statuksen tulostaminen
* Käyttöliittymäkomentojen listaaminen
* Kaikkien valojen hakeminen
* Kaikkien valojen sammuttaminen
* Valo-ohjaimen konfiguraation tallentaminen
* Valo-ohjaimen konfiguraation lataaminen
* Poistuminen sovelluksesta

Käyttöliittymälle annetaan sekä Scanner-olio että sovelluslogiikka (Application-olio). Käyttöliittymä käyttää vain sovelluslogiikan metodeja.

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

Sovelluksessa on yksinkertainen konfiguraatiotallennus tekstitiedostoon ja vastaavan tekstitiedoston lukeminen tallennetun konfiguraation hakemiseksi sovelluksen käyttöön.

## Sekvenssikaavio: käyttäjä autentikoi sovelluksen fyysiselle valo-ohjaimelle

![Sekvenssikaavio: autentikointi](https://github.com/topiranta/ot-harjoitustyo/blob/master/dokumentointi/kuvat/lightapplication-auth-sequence-diagram.png)
