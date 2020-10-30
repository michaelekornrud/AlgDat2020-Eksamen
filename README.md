# Mappeeksamen i Algoritmer og Datastrukturer Høst 2020



# Krav til innlevering

Se oblig-tekst for alle krav, og husk spesielt på følgende:

* Git er brukt til å dokumentere arbeid (minst 2 commits per oppgave, beskrivende commit-meldinger)	
* git bundle er levert inn
* Hovedklassen ligger i denne path'en i git: src/no/oslomet/cs/algdat/Eksamen/EksamenSBinTre.java
* Ingen debug-utskrifter
* Alle testene i test-programmet kjører og gir null feil (også spesialtilfeller)
* Readme-filen her er fyllt ut som beskrevet


# Beskrivelse av oppgaveløsning (4-8 linjer/setninger per oppgave)

Jeg har brukt git til å dokumentere arbeidet vårt. Jeg har 21 commits totalt, og hver logg-melding beskriver det jeg har gjort av endringer.

* Oppgave 1: I oppgave 1 har jeg brukt programkode 5.2.3 a) og fant ut at jeg måtte implementere forelder ved å gi knytte 
             en variabel til forelder og en annen variabel til verdien. Disse variabelene oppdaterer seg etter hvert som 
             treet fylles opp. Metoden sjekker om verdien som skal bli lagt inn er større eller mindre enn roten, og går til
             høyre dersom den er større, og til venstre dersom den er mindre. Dette gjør metoden for hver node den møter på
             under innlegging av ny verdi. 
             
* Oppgave 2: Først sjekker jeg om treet er tomt og om det faktisk inneholder verdien vi søker etter ved hjelp av den ferdig-
             kodede inneholder metoden. Deretter sier jeg at dersom rot ikke er lik null, så skal jeg sammenligne input-verdi
             med verdien til den noden vi står på. Dersom input-verdien er lik node-verdien skal en teller økes med en, og 
             node-verdien oppdateres til høyrebarnet. Og hvis input-verdien er større enn node-verdien skal node-verdien oppdateres
             til høyrebarnet, men telleren skal ikke økes. Men hvis input-verdien er mindre enn node-verdien skal node-verdien 
             oppdateres til venstrebarnet. Deretter returnerer jeg telleren. Dersom input-verdien ikke er lik noen andre verdier 
             skal metoden returnere 0. 
             
* Oppgave 3: I denne oppgaven har jeg fulgt programmkodene fra delkapittel 5.1.7 i læreboka om preorden, inorden og postorden. 
             Jeg har spesielt fulgt programmkode 5.1.7 h) men de andre eksemplene fra boka har vert til god hjelp.
             førstePostorden(): først sjekker jeg om input er lik null eller ikke og retrunerer null dersom verdien er null. 
                                I postorden heter det venstre, høyre også node. Det første jeg gjør er å finne bladnoden lengst
                                til venstre, så langt det går og til høyre dersom det er nødvendig. 
             nestePostorden(): For å finne neste postorden, traverserer jeg gjennom treet og finer neste node i postorden enten 
                               til høyre for start-node hvis høyre ikke er null, og deretter opp til forelder så lenge forelder ikke 
                               er null. Dersom høyrebarnet er lik null, så skal metoden hoppe fra venstrebarn opp til forelder.
                               
* Oppgave 4: I denne oppgaven har jeg brukt metodene fra oppgave 3 og noe rekursjon for å finne en løsning. 
             postorden(): I denne metoden starter jeg med å sjekke om treet er tomt og returnerer da det er en void metode. 
                          Først så oppretter jeg en node som skal hente første node i postorden fra treet (starter i rot og traverserer nedover).
                          Deretter sier jeg at så lenge p (i dette tilfellet) ikke er null skal metoden skrive ut /lagre verdien, også
                          oppdaterer jeg den med nestePostorden. 
             postordenRecursive(): Her har eksemplene fra forelesing om postorden hjulpet meg. Dersom venstrebarnet til input (p) ikk er 
                                   er null, kaller jeg rekursivt på metoden for å hente venste sub-tre. Deretter gjør jeg nøyaktig
                                   det samme for høyre sub-tre før jeg utfører oppgaven som er å lagre verdiene i postorden. 

* Oppgave 5: I denne oppgaven har jeg brukt metoden printLevelOrder fra forelesing som eksempel.
             serialice(): Først så finner jeg roten og oppretter en deque  og en arrayList. Deretter legger jeg roten inn i både kø og liste.
                          Så lenge køen ikke er tom, så skal jeg ta ut den første i køen og legger til venstrebarnet i både kø og liste så lenge
                          verdien ikke er null. Deretter gjør jeg det samme med høyrebarnet helt til køen er tom, og da returnerer jeg listen. 
             deserialize(): Her sjekker jeg først om input-listen er tom eller ikke og returnerer null dersom den er tom. 
                            Så oppretter jeg et nytt tre som jeg kan legge verdiene fra listen inn i. Dette gjør jeg ved hjelp av 
                            leggInn metoden fra oppgave 1, så lenge input-listen ikke er tom. Til slutt returnerer jeg et tre. 

* Oppgave 6: fjern(): Her følger jeg prgrammkode 5.2.8 d)
                      Dette er løst ved å itterere gjennom treet og fjerne noden og tilhørende pekere hver gang comparatoren returnerer 0 
                      altså sier at input-verdi og verdien i noden er like. Hvis input er større en verien i noden skal metoden ta deg et steg ned til høyre,
                      og ned til venstre hvis ikke. Deretter oppdaterer jeg de resterende pekerene slik at det ikke blir noen "hull" i treet. ****
             fjernAlle(): Først sjekker jeg om roten er null eller ikke, altså om treet et tomt eller ikke. Så oppretter jeg en teller. 
                          Deretter sier jeg at så lenge fjern-metoden returnerer true på input-verdien, så skal telleren økes med 1.         
             nullstill(): Her har jeg brukt en privat hjelpemetode for å nullstille rekursivt, slik at jeg kan kalle på denne i den offentlige
                          metoden. Disse metodene sammen sier at dersom treet ikke er tomt (rot != null) skal den kalle på hjelpemetoden for 
                          å sette pekerne til null, og deretter sette rot-noden til null.  
                                       
                 