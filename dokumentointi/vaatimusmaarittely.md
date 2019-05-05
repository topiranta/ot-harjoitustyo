# Alustava vaatimusmäärittely

*Vaatimusmäärittely 1.0 5.5.2019*

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
valo-ohjaimen nappulalla). Autentikoinnin jälkeen autentikointia ei tarvitse suorittaa uudestaan, vaikka sovellus jouduttaisiin käynnistämään uudestaan, vaan autentikointitiedot voi ladata sovelluksen käyttöön.

Lisäksi käyttäjän on asetettava käyttökohteen sijainti koordinaatteinta, jotta auringonlaskun aika voidaan laskea.

### Sovelluksen peruskäyttö 

* Sovelluksen käyttäjä voi alkukonfiguroinnin jälkeen käynnistää valojensa automaattisen päivityksen ja asettaa sille halutessaan päättymisajan
* Käyttäjä voi sammuttaa valot
* Käyttäjä voi myös lisätä tai poistaa yksittäisiä valoja automaattisen päivityksen piiristä 
* Käyttäjä voi muuttaa alussa tehtyjä konfigurointeja
* Valo-ohjaimen konfiguroinnin voi tallentaa ja ladata pitkäaikaistallennuksesta

## Sovelluksen käyttöliittymä

Sovelluksella on yksi tekstikäyttöliittymä

## Teknisiä huomioita

### Rajapinnat

Sovellus käyttää Hue-ohjaimen kanssa JSON-rajapintaa. Myös auringonlaskuaika haetaan [Sunrise Sunset -rajapinnasta](https://sunrise-sunset.org/api).

### Tiedon tallentaminen

Sovelluksen on voitava tallentaa ainakin konfigurointitiedot kovalevylle tai muuhun pysyväistallennukseen, jotta konfigurointitietoa ei tarvitse erikseen 
syöttää aina sovellusta käytettäessä. 

## Sovelluksen testaus

Sovellusta voi pieneltä osin käyttää myös ilman fyysistä valo-ohjainta.

## Jatkokehitysmahdollisuuksia

* Automaattisen päivityksen käynnistäminen muualta kuin käyttöliittymästä
  * Esimerkiksi mobiiliapplikaatio, Google Home etc.
* Lokien pitäminen
  * Käyttölokeja voisi tallentaa myös internetkohteeseen
* Lokien kerääminen muista toiminnallisuuksista
  * Hue-järjestelmään voi kytkeä esimerkiksi liiketunnistmia, joiden havaintoja voisi kirjata lokiin
