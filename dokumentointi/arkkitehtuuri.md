# Arkkitehtuurikuvaus

*Arkkitehtuurikuvaus 1.0, 5.5.2019*

Tässä dokumentissa on kuvattu sovelluksen paketti- ja luokkarakenne sekä kuvattu näiden vastuut. Metodien yksityiskohtainen kuvailu on jätetty JavaDoc-dokumentoinnin vastuulle.

Lisäksi tässä dokumentissa on kuvattu riippuvuuksia kolmannen osapuolen sovelluksista ja kirjastoista.

## Sovelluksen pakettirakenne

Sovelluksella on viisi pakettia, joiden rakenne on seuraava:

![Pakettirakenne](https://github.com/topiranta/ot-harjoitustyo/blob/master/dokumentointi/kuvat/pakettirakenne-lightapplication.png?raw=true)

### topiranta.lightapplication

Paketti on kaikkien muiden sovelluspakettien parent ja sisältää ainoastaan Main-luokan.

#### Main.java

Main-luokan tehtävä on käynnistää sovelluslogiikka sekä käyttöliittymä ja antaa muodostettu sovelluslogiikka käyttöliittymän käyttöön.

### topiranta.lightapplication.logics

Paketti sisältää kaiken varsinaisen sovelluslogiikan.

#### Application.java

Application on käyttöliittymän vastinpari, joka tarjoaa käyttöliittymälle kaikki sen tarvitsemat metodit. Application on myös lähes poikkeuksetta luokka, jossa exception-käsittely tehdään. Application suorittaa käyttöliittymästä tulleet pyynnöt ja palauttaa tiedon kunkin operaation onnistumisesta merkkijonona, jonka käyttöliittymä voi tulostaa käyttäjälle. Application osaa palauttaa merkkijonojen mukana tietoa sen käsittelemistä poikkeuksista, mikäli sen edelleen käyttämät metodit heittävät sille poikkeuksia.

Applicationilla on oliomuuttujina valo-ohjainta kuvaava Bridge-olio, tietoa valo-ohjaimelta saatavista lampuista sekä käyttäjän haluamista päivitettävistä lampuista, valolaskin sekä tieto, mitä lampuille on viimeksi päivitetty automaattisten päivitysten yhteydessä.

#### ConfigurationOperations.java

Luokka, joka hoitaa Applicationin tarvitsemat konfiguraatioiden tallennukset ja luvut pysyväismuistista. ConfigurationOperations osaa luoda Bridge-olion pysyväismuistista luetun tiedon pohjalta. Varsinaiseen tekstitiedostojen luomiseen ja lukemiseen ConfigurationOperations käyttää apunaan utils-paketin LocalFiles-luokkaa.

#### DefaultAndTestValues.java

Luokka, jonka metodeihin on tallennettu vakiotoimintoja muun muassa käyttäjätestaustamoodia varten.

#### DeviceOperations.java

DeviceOperations-luokan vastuulla on suurempien laiteoperaatioiden, kuten sovelluksen autentikoinnin, hoitaminen. Kun valo-ohjaimelta tarvitaan sovelluksen käyttöön esimerkiksi tietoa sille konfiguroiduista lampuista, osaa DeviceOperations muodostaa valo-ohjaimen tietojen pohjalta lamppuoliolistan Applicationin käyttöön.

Kuitenkaan DeviceOperations ei hoida kaikkea liikennettä valo-ohjaimen kanssa, sillä yksinkertaisuuden vuoksi esimerkiksi lamppuoliot osaavat päivittää omia arvojaan valo-ohjaimelle.

Apunaan kommunikointiin valo-ohjaimen kanssa DeviceOperations käyttää utils-paketin Connections-luokkaa.

#### LightCalculator.java

Nimensä mukaisesti LightCalculator hoitaa kaiken valolaskennan sovelluksessa. Se käyttää SunTimes-luokkaa saadakseen tiedon auringon liikkeistä ja laskee näiden sekä kellonajan perusteella sopivat arvot päivitettäväksi älyvaloille. LightCalculator ylläpitää tietoa siitä, milloin sen auringonlaskuaika on viimeksi päivitetty ja osaa arvoja pyytäessä päivittää auringonlaskutiedon, mikäli uusi aamu on alkamassa tai auringolaskutieto on muuten päässyt vanhentumaan. LightCalculator pitää siis osaltaan huolen siitä, että Sunrise Sunset -rajapintaa käytetään vain silloin, kun sille on tarvetta.

LightCalculator käyttää valojen himmennyksen päättymisajaksi auringonlaskua, ellei auringonlasku oli rajapintatiedon mukaan vasta iltakahdeksan jälkeen. Tällöin LightCalculator asettaa himmennyksen päättymisajaksi iltakahdeksan.

#### SunTimes.java

SunTimes-luokka huolehtii auringonlaskutietojen hakemisesta Sunrise Sunset -rajapinnasta. Se palauttaa metodinsa kysyjälle tiedon siitä, milloin aurinko on korkeimmillaan sekä siitä, milloin aurinko laskee.

Tietojen noutamiseen SunTimes käyttää utils-paketin Connections-luokkaa.

### topiranta.lightapplication.devices

Pakettissa on fyysisiä Hue-laitteita kuvaavat oliot Bridge ja Lamp.

#### Bridge.java

Bridge on Applicationin pitämä olio, joita on sovelluksessa vain yksi. Bridgeen liittyvät kaikki käytettävät lamput ja bridgen ylläpitämien ip- ja id-tietojen avulla muodostetaan kaikkien lamppujen käyttämät GET- ja PUT-operaatioiden osoitteet. Lisäksi, koska Bridge kuvaa fyysistä laitetta, sisältää Bridge myös tiedon sijaintikoordinaateista, joita käytetään auringonlaskuaikatiedon hakemiseen rajapinnasta.

#### Lamp.java

Varsinaista älyvaloa kuvaava Lamp-luokka liittyy aina valo-ohjaimeen. Vaikka Application mahdollistaa tällä hetkellä vain yhden valo-ohjaimen ylläpidon, voisi valo-ohjaimia olla Lamp-luokan näkökulmasta useampiakin.

Lamp-olio osaa sammuttaa fyysisen vastinparinsa ja lähettää sille myös päivitettävät väri- ja himmennysarvot. Lamp-olio on sovelluksen ainoa luokka, jolla on ylikirjoitetut hashCode- ja equals-metodit, jotta listoilta voidaan tarkistaa, ettei niihin tallenneta duplikaattilamppuja.

### topiranta.lightapplication.utils

Utils-paketti sisältää staattiset metodit pysyväistallennukseen ja -lataukseen, URL-yhteydenottoihin sekä aikalaskentaan. Kaikki tämän paketin metodit ovat valosovelluksen varsinaisesta logiikasta irrallisia yleisiä metodeja, joten niitä voisi sellaisenaan käyttää minkä tahansa muun sovelluksen kanssa, joka vastaavia toimintoja tarvitsee.

#### Connections.java

Connections sisältää tarvittavat toiminnot yhteydenottoihin ja JSON-muotoisen tiedon välitykseen. Se osaa GET-, PUT- ja POST-operaatiot ja sen metodirakenne mahdollistaisi helposti myös muiden operaatioiden, esimerkiksi DELETEn, käyttämisen luokan metodien avulla.

#### LocalFiles.java

LocalFiles sisältää hyvin triviaaleja tiedosto-operaatioita: tiedostojen lukemista ja tallentamista.

#### Time.java

Time-luokassa on aikalaskentaan liittyviä metodeja, jotta esimerkiksi UTC-vyöhykkeen ajassa ilmoitettu aika saadaan käännettyä käyttöjärestelmän aikavyöhykkeelle.

### topiranta.lightapplication.ui

Paketti sisältää yksinkertaisen TextUi.java-käyttöliittymän, jonka tehtävänä on kerätä syötteet ja käyttäjän käskyt ja toimittaa ne Applicationille.

## Kolmannen osapuolen kirjastot ja palvelut

### JSON.Simple

Sovellus käyttää JSON-muotoisen datan parsimiseen JSON.Simple-kirjastoa. Kirjasto on lisensoitu [Apache 
2.0](http://www.apache.org/licenses/LICENSE-2.0) -lisenssillä ja se on vapaata lähdekoodia. Kirjasto löytyy myös 
[tämän sovelluksen 
repositoriosta](https://github.com/topiranta/ot-harjoitustyo/tree/master/light-application/external-libraries/json-simple). 
Kirjastoa ei ole muokattu tätä sovellusta varten.

[JSON.Simple -kirjaston dokumentointi](https://code.google.com/archive/p/json-simple/)

[JSON.Simple -kirjasto GitHubissa](https://github.com/fangyidong/json-simple)

### Sunrise Sunset -rajapinta

Sovellus hakee auringonlaskutiedot [Sunrise Sunset -rajapinnasta](https://sunrise-sunset.org/api). Rajapinnan tiedoista sovellus hyödyntää sunset ja solar noon -arvoja.

## Pysyväistallennus

Sovelluksessa on yksinkertainen konfiguraatiotallennus tekstitiedostoon ja vastaavan tekstitiedoston lukeminen tallennetun konfiguraation hakemiseksi sovelluksen käyttöön.

## Sekvenssikaavio: käyttäjä autentikoi sovelluksen fyysiselle valo-ohjaimelle

![Sekvenssikaavio: autentikointi](https://github.com/topiranta/ot-harjoitustyo/blob/master/dokumentointi/kuvat/lightapplication-auth-sequence-diagram.png)
