# Käyttöohje

*Käyttöohje ver 1.0, 5.5.2019*

Tämä käyttöohje sisältää omat osiot sovelluksen käyttäjälle ja testaajalle sekä lähdekoodin käyttäjälle.

## Sovelluksen käyttäjälle

### Sovelluksen asentaminen

Sovelluksen .jar-tiedosto ei tarvitse ulkopuolisia tiedostoja toimiakseen. Ei-testikäytössä sovelluksen kanssa samassa lähiverkossa on oltava Philips Hue -valo-ohjain, jota sovellus käyttää. Lisäksi sovellus tarvitsee internetyhteyden auringolaskuajan hakemiseen. Konfigurointeja tallennettaessa sovellus luo kansioon config.txt-tiedoston.

### Sovelluksen käyttöliittymä

Sovellus käynnistää tekstikäyttöliittymän, jossa on mahdollista:

* Asettaa ja autentikoida sovellus lähiverkossa olevalle Philips Hue -valo-ohjaimelle
* Muuttaa edellisiä konfigurointeja
* Hakea kaikki konfiguroituun valo-ohjaimeen liitetyt älyvalot
* Sammuttaa kaikki haetut älyvalot
* Tallentaa valo-ohjaimen konfigurointi
* Ladata tallennettu ohjainkonfigurointi
* Asettaa ja poistaa valoja automaattisen päivityksen piiristä
* Käynnistää valojen automaattinen päivitys
* Tulostaa auringonlaskuajan sekä ohjelman laskemat väriarvot valoille tulostushetkellä

### Valo-ohjaimen konfigurointi

Käyttäjän on tiedettävä lähiverkossa olevan valo-ohjaimen IP-osoite. Käyttäjä syöttää tekstikäyttöliittymästä sovellukselle valo-ohjaimen IP-osoitteen lähiverkossa ja antaa ohjaimelle nimen. Tämän jälkeen käyttäjän on painettava valo-ohjaimen päällä olevaa yhdistämispainiketta, jonka jälkeen käyttäjä voi autentikoida sovelluksen valo-ohjaimelle.

Lisäksi käyttäjän on annettava koordinaatit, joissa valo-ohjain sijaitsee, jotta sovellus voi hakea auringonlaskuajan. Koordinaatit annetaan muodossa xx.xxxxxxx, yy.yyyyyyy leveys- ja pituuskoordinaatti erikseen. Jos ensimmäisen koordinaatin jättää tyhjäksi, asettaa sovellus valo-ohjaimen koordinaateiksi Helsingin koordinaatit.

### Lamppujen hakeminen

Kun valo-ohjain on konfiguroitu, hakee sovellus kaikki ohjaimeen kytketyt lamput.

### Lamppujen sammuttaminen

Kaikki lamput voi sammuttaa, kunhan ne on ensin haettu ohjaimelta.

### Valojen automaattinen päivittäminen

Sovelluksessa voi kopioida kaikki valo-ohjaimelta haetut valot automaattisen päivityksen piiriin. Lisäksi tämän automaattisen päivityksen piiristä voi poistaa yksittäisiä valoja tai kaikki valot.

Kun lista on asetettu sopivaksi, automaattisen päivityksen voi käynnistää. Automaattiselle päivitykselle pitää antaa päättymisaika, jolloin päivitys lopetetaan ja ohjelma palaa käyttöliittymän aloitusvaiheeseen. Mikäli aikaa ei anneta, pyöritetään päivitystä siihen saakka, että ohjelma ajetaan käyttöjärjestelmän avulla alas (kuitenkaan ei pidempään kuin siihen hetkeen, että käyttöjärjestelmä antaa sovellukselle ajankohdaksi 1.1.2050).

Automaattinen päivitys päivittää aluksi kaikkia listalla olevia valoja. Päivitys ja värilaskenta tapahtuvat kerran minuutissa. Sovellus ei päivitä sellaisia valoja, jotka eivät ole valo-ohjaimen mukaan saatavilla (engl. reachable) tai sellaisia valoja, joita jokin muu sovellus päivittää automaattisen päivittämisen alkamisen jälkeen. Tämä mahdollistaa sen, että jollain muulla sovelluksella voi vaikkapa sammuttaa valot tai muuttaa niiden arvoja ilman, että tämä sovellus niitä enää muuttaa.

Samaten on mahdollista asettaa jollain kolmannella sovelluksella listalla oleville valoille brightness- ja color temperature -arvoiksi arvot 211, jolloin sovellus palauttaa ne automaattisen päivityksen piiriin.

Edellä kuvatut toiminnot mahdollistavat sovelluksen jatkuvan pyörittämisen esimerkiksi Raspberry Pi -minitietokoneella, jolloin muilla sovelluksilla on mahdollista kontrolloida, ovatko lamput kulloinkin automaattisen päivityksen piirissä vai eivät.

## Sovelluksen testaajalle

Sovelluksessa on mahdollista syöttää testi-ip-osoite käyttäjätestausta varten silloin, kun lähiverkossa ei ole käytettävissä valo-ohjainta tai siihen liityettyjä älyvaloja. Testi-ip on 0.0.0.0 ja se annetaan valo-ohjainta konfiguroidessa. Sovellus osaa tällöin olla ottamatta yhteyttä verkkoyhteyden yli konfigurointivaiheessa.

Koska varsinaisia lamppuja ei ole sovelluksessa simuloitu, voi käyttöliittymästä testikonfiguroinnin jälkeen hakea auringonlaskuajan sekä tiedot värilaskimelta siitä, mitkä arvot se antaa sillä hetkellä kun niitä kysytään.

## Lähdekoodin käyttäjälle

### Riippuvuudet (Dependencies)

Sovelluksen lähdekoodin mukana tulee JSON.Simple-kirjasto, joka on osoitettu riippuvuudeksi pom.xml -tiedostossa. Riippuvuutta ei tarvitse osoittaa erikseen ohjelmointiympäristölle.

### Main-luokan asettaminen

NetBeans ei välttämättä tunnista ensimmäisellä suorituskerralla Main-luokkaa sovellusta ajettaessa. Luokan voi osoittaa NetBeansille klikkaamalla projektia Projects-välilehdellä hiiren oikealla ja valitsemalla valikosta "Properties". Main-luokan voi asettaa avautuvan ikkunan kohdassa Run, jonka Main Class -riville tallennetaan arvo "topiranta.lightapplication.Main".
