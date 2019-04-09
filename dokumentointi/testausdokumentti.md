# Testausdokumentti

Testausdokumentti ver. 0.2, päivitetty 9.4.2019

## Testit

Sovelluksella on kaksi testiä, jotka testaavat, tallentaako toinen Bridge-luokan konstruktori parametreina saamansa 
IP-osoitteen sekä valo-ohjaimen nimen Bridge-oliolle.

## Ongelmat testisuunnittelussa

Sovelluksen kehittäjä ei toistaiseksi ole keksinyt hyvää tapaa testata sovellusta, jonka merkittävä osa perustuu erilaisiin http-protokollan yli tapahtuviin GET-, POST ja SET-yhteydenottoihin. Siksi sovellukselle ei ole kirjoitettu kattavampaa testausta. Sovelluksen kehittäjä pyrkii keksimään ratkaisun, jolla yhteydenottometodeja voidaan asiallisesti testata ja testauskattavuus siten saada kohdilleen ilman, että testejä on ajettava samassa lähiverkossa sijaitsevan fyysisen Philips Hue -valo-ohjaimen kanssa tai testit muuten keskittyisivät turhanpäiväiseen getterien ja setterin kokeiluihin.

Sovelluksen kehittäjä pyrkii todennäköisesti etsimään kolmannen osapuolen palvelun, johon Utils-luokan metodit voivat ottaa yhteyden mistä tahansa vakiomuotoisen URL-osoitteen yli ja johon voi etukäteen konfiguroida testejä varten halutut vastaukset, jolloin myös muita metodeja pääsee järkevästi testaamaan.

## Maven, Jacoco ja testikattavuus

Sovellus on noudettu Github-repositoriosta eri tietokoneelle kuin missä sovellus on kehitetty. Samalla on todennettu, että siitä saa ajettua Jacoco-testikattavuusraportin, kunhan Maven-testit on ensin ajettu.
