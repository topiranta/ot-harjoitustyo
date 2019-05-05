# Testausdokumentti

*Testausdokumentti ver. 1.0, 5.5.2019*

## Testit

Sovelluksen testien rakenne noudattaa sovelluksen pakkausrakennetta. Sovelluksen testit testaavat valo-ohjainolion toimintaa, varsinaista sovelluslogiikkaa sekä URL-yhteydenottometodeja.

## Testien ulkopuolisia riippuvuuksia

Koska sovellus käyttää paljon yhteyksiä URL-osotteiden yli, on sovelluksessa testejä, joka käyttävät [JSONPlaceholder-testipalvelua](https://jsonplaceholder.typicode.com/) JSON-tiedon hakemiseen ja sen sisällön oikeellisuuden tarkistamiseen. Tämän vuoksi sanotun palvelun ollessa estynyt palvelemaan testejä tai palvelun muuttuessa ei yksi tai useampi testi mene läpi.

Lisäksi testit tekevät tarkoituksella virheellisiä yhteydenottoja esimerkiksi kehittäjän kotisivuille saadakseen tarkistettua, että sivut palauttavat testeille 404-koodin.

## Maven, Jacoco, Checkstyle

Sovellus on noudettu Github-repositoriosta eri tietokoneelle kuin missä sovellus on kehitetty. Samalla on todennettu, että siitä saa ajettua Jacoco-testikattavuusraportin, kunhan Maven-testit on ensin ajettu.

Samoin Checkstyle on ajettavissa, kunhan Mavenilla on ensin ajanut jxr:jxr -goalin ensin.
