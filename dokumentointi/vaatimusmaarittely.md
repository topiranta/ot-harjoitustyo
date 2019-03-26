# Alustava vaatimusmäärittely

## Tietoa

Tämä on alustava vaatimusmäärittely, joka päivittyy ja tarkentuu kurssin myötä.

## Sovelluksen tarkoitus

Sovellus päivittää automaattisesti käyttäjän [Philips Hue -älyvaloja](https://developers.meethue.com). Sovellus laskee kellon- sekä auringolaskuajan mukaan 
sopivan kirkkauden sekä värisävyn käyttäjän älyvaloihin ja päivittää arvot säännöllisesti haluttuihin lamppuihin. Näin halutut valot palavat päivällä 
kirkkaampina ja valkoisempina taittuen pehmeämpiin sävyihin auringon laskiessa.

## Käyttäjät

Sovelluksella on yksi käyttäjärooli, johon viitataan jatkossa käyttäjänä. Sovelluksen jatkokehitystarpeet eivät tässä vaiheessa edellytä muiden käyttäjäroolien 
hahmottelua.

## Perustoiminnallisuudet

### Sovelluksen konfigurointi käyttöä varten

Sovelluksen käyttäjä voi autentikoida sovelluksen lähiverkkoonsa kytketylle Philips Hue Bridge -valo-ohjaimelle. Sovelluksen käyttäjän on muusta yhteydestä 
tiedettävä valo-ohjaimen IP-osoite lähiverkossa ja käyttäjällä on oltava fyysinen pääsy laitteelle autentikointia varten (autentikointikutsu hyväksytään 
valo-ohjaimen nappulalla). Autentikoinnin jälkeen autentikointia ei tarvitse suorittaa uudestaan, vaikka sovellus jouduttaisiin käynnistämään uudestaan.

Lisäksi käyttäjän on asetettava käyttökohteen sijainti koordinaatteinta, jotta auringonlaskun aika voidaan laskea.

### Sovelluksen peruskäyttö 

* Sovelluksen käyttäjä voi alkukonfiguroinnin jälkeen käynnistää ja lopettaa valojensa automaattisen päivityksen
* Käyttäjä voi sammuttaa valot
* Käyttäjä voi myös lisätä tai poistaa yksittäisiä valoja automaattisen päivityksen piiristä 
* Käyttäjä voi muuttaa alussa tehtyjä konfigurointeja

## Sovelluksen käyttöliittymä

Käyttöliittymä jakautuu alustavasti edellisten vaatimusten perusteella kahteen osaan: konfigurointinäkymään sekä peruskäyttöliittymään.

## Teknisiä huomioita

### Rajapinnat

Sovellus käyttää ainakin Hue-ohjaimen kanssa JSON-rajapintaa. Myös auringonlaskuaika voidaan hakea rajapinnan yli tai vaihtoehtoisesti laskenta voidaan tehdä 
sovelluksessa itsessään.

### Tiedon tallentaminen

Sovelluksen on voitava tallentaa ainakin konfigurointitiedot kovalevylle tai muuhun pysyväistallennukseen, jotta konfigurointitietoa ei tarvitse erikseen 
syöttää aina sovellusta käytettäessä. 

## Sovelluksen testaus

Sovellusta on voitava testata myös ilman fyysistä valo-ohjainta, joten testaamista varten sovelluksessa on oltava jonkinlainen valo-ohjaimen 
emulointimahdollisuus.

## Jatkokehitysmahdollisuuksia

* Automaattisen päivityksen käynnistäminen muualta kuin käyttöliittymästä
  * Esimerkiksi mobiiliapplikaatio, Google Home etc.
* Lokien pitäminen
  * Käyttölokeja voisi tallentaa myös internetkohteeseen
* Lokien kerääminen muista toiminnallisuuksista
  * Hue-järjestelmään voi kytkeä esimerkiksi liiketunnistmia, joiden havaintoja voisi kirjata lokiin

## Meta

Tämän vaatimusmäärittelydokumentin versio on 0.1.1 ja se on päivitetty 26.3.2019.
