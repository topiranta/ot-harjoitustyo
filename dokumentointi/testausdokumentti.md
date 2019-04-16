# Testausdokumentti

*Testausdokumentti ver. 0.3, 16.4.2019*

## Testit

Sovelluksen testien rakenne noudattaa sovelluksen pakkausrakennetta. Sovelluksen testien rivikattavuus on hieman vajaa 50%, kun käyttöliittymäkoodia ei oteta huomioon. Sovelluksen testit testaavat valo-ohjainolion toimintaa, varsinaista sovelluslogiikkaa sekä URL-yhteydenottometodeja.

## Testien ulkopuolisia riippuvuuksia

Koska sovellus käyttää paljon yhteyksiä URL-osotteiden yli, on sovelluksessa testi, joka käyttää getJSON-metodia [JSONPlaceholder-testipalvelussa](https://jsonplaceholder.typicode.com/) olevan JSON-tiedon hakemiseen ja sen sisällön oikeellisuuden tarkistamiseen. Tämän vuoksi sanotun palvelun ollessa estynyt palvelemaan testejä tai palvelun muuttuessa ei yksi testi mene läpi.

## Maven, Jacoco, Checkstyle

Sovellus on noudettu Github-repositoriosta eri tietokoneelle kuin missä sovellus on kehitetty. Samalla on todennettu, että siitä saa ajettua Jacoco-testikattavuusraportin, kunhan Maven-testit on ensin ajettu.

Samoin Checkstyle on ajettavissa, kunhan Mavenilla on ensin ajanut jxr:jxr -goalin ensin.
