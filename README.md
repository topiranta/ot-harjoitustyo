# Readme

*Readme ver. 1.0.1 5.5.2019*

Sovellus mahdollistaa lähiverkossa olevien [Philips Hue -älyvalojen](https://developers.meethue.com) automaattisen 
päivittämisen niin, että vuorokaudenajasta ja auringonlaskun ajankohdasta riippuen valojen väri ja kirkkaus muuttuvat 
sopiviksi.

Sovellus käyttää [JSON.Simple -kirjastoa](https://github.com/fangyidong/json-simple) JSON-muotoisen tiedon käsittelyyn. Tiedot auringonlaskuajasta sekä auringon huippuajasta sovellus hakee [Sunrise Sunset -rajapinnasta](https://sunrise-sunset.org/api)

## Dokumentaatio

[Vaatimusmäärittely](https://github.com/topiranta/ot-harjoitustyo/blob/master/dokumentointi/vaatimusmaarittely.md)

[Käyttöohje](https://github.com/topiranta/ot-harjoitustyo/blob/master/dokumentointi/kayttoohje.md)

[Arkkitehtuuri](https://github.com/topiranta/ot-harjoitustyo/blob/master/dokumentointi/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/topiranta/ot-harjoitustyo/blob/master/dokumentointi/testausdokumentti.md)

[Työaikakirjanpito](https://github.com/topiranta/ot-harjoitustyo/blob/master/dokumentointi/tyoaikakirjanpito.md)

[Ensimmäinen release](https://github.com/topiranta/ot-harjoitustyo/releases/tag/1.0)

## Komentorivitoiminnot

### Testien ajaminen

> mvn test

### Testikattavuusraportti (target/site/jacoco/index.html)

> mvn jacoco:report

### Checkstyle (target/site/checkstyle.html)

> mvn jxr:jxr checkstyle:checkstyle

### .jar-tiedoston muodostaminen (/target)

> mvn package

