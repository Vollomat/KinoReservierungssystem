Information für Frontend:

!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
Zusammengefasst: Siehe PDF ,,Befehle fuers Frontend".

Pro Sitzplatz wird ein Ticket angelegt!!! BestellungsID bekommt man indem man zuvor Bestellung anlegt:
POST an: http://localhost:8080/bestellungen

Beispiel JSON:
{
"email":"patrick.vollstedt@gmx.de",
"bezahlart":"Kasse im Kino"
}

return vom Server--> BestellungsID



Wenn das Ticket mit POST an den Spring Boot Server gesendet wird, dann wie folgt:
POST an: http://localhost:8080/tickets

Beispiel JSON:
{
"email":"patrick.vollstedt@gmx.de",
"startuhrzeit":"23:00",
"kinosaalNummer":4,
"filmName":"James Bond",
"preis":"22,50",
"sitzplatzreihe":"2",
"sitzplatzspalte":"3",
"bestellungID":1
}

--> Preis muss danach noch gelöst werden, geht allerdings erst wenn alles läuft, damit ich weiß, wann der berechnet
werden muss


!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

Idee für Präsi:
Jeder von uns nimmt eine Persona an und führt dementsprechend den Buchungsprozess anders durch
________________________________________________________________________________________________________________________
WAS NOCH ZU TUN IST:

- Bezahlart mitsenden

- Header

- Preisliste Essen + Trinken

- Bestellungen ansehen raus

- Präsentation: Personas erwähnen, Pflichtenheft

- Reservieren der Tickets -> Uhrzeit mitschicken

!!!- Vorstellungen Buttons vereinheitlichen

- Ticketbuchungsprozess: Zurücksperre bei Kasse
________________________________________________________________________________________________________________________

Elisa:

- Header muss ganz am Ende auf allen Seiten (außer Ticketbuchung) gleich sein (siehe Index)

Nandini

-login/register: für jedes wird ein eigenes Mapping! benötigt

- Wenn man sich eingeloggt hat, sollte man seinen Bestellstatus einsehen können
(muss noch Serverdaten erhalten, Interface ist schon da (siehe footer))

- Login2 muss von der Datenbank überprüft werden (wie normaler Login, befindet sich unter 'footarea')


Dana:

- User Interface für die Mail erstellen, die der User erhält, wenn er sein Passwort vergessen hat

________________________________________________________________________________________________________________________
TO-DO Dana & Elisa:
________________________________________________________________________________________________________________________

- Präsentation

evtl.:
- Kundenkonto löschen Button bei Bestellungen Seite (footer)

- E-Mail: Passwort vergessen Interface

________________________________________________________________________________________________________________________
WAS AUF DEN EINZELNEN SEITEN NOCH ZU TUN IST:
________________________________________________________________________________________________________________________

saalplan.html:
Es müssen Funktionen geschrieben werden für:

- dass ausgewählte Sitze den Status "occupied" (belegt) bekommen, wenn das Ticket gebucht wurde
-> eventuell muss jedem Sitz eine ID gegeben werden -> mit Patrick absprechen
- die ausgewählten Sitze müssen auch in der Datenbank als gebucht/reserviert/... markiert werden

______________________________________________________

bezahlen.html:
- hier müssen alle eingegebenen Daten richtig angezeigt werden -> wird aus der Datenbank gezogen

______________________________________________________

gast.html, login.html, login2.html (unter footarea) & registrierung.html:
- alle Daten müssen richtig in die Datenbank übertragen werden, bzw. im Browser zwischengespeichert werden

______________________________________________________

passwort.html:
Es müssen Funktionen geschrieben werden für:

- die Überprüfung, ob die E-Mail Adresse überhaupt schon in der Datenbank vorhanden ist, d.h. ob
überhaupt ein bestehendes Konto zu dieser Adresse existiert
- ist dies der Fall, so muss das zugehörige Passwort an die eingegebene Mailadresse geschickt werden
-> hierzu muss auch noch ein Mailinterface erstellt werden

______________________________________________________

vorstellunganlegen.html:
- alle Daten müssen richtig in die Datenbank gespeichert werden

______________________________________________________

bestellungen.html:
- man hat sich zuvor mit login2.html eingeloggt -> die entsprechende Mailadresse muss gespeichert werden
- die zugehörigen Bestellungen müssen dann auf dieser Seite angezeigt werden

________________________________________________________________________________________________________________________
Was bei Langeweile gemacht werden kann, wenn ALLES ANDERE fertig ist :)
________________________________________________________________________________________________________________________

- Die Suchleiste muss implementiert werden

- Für Karriere könnte man sich eine offene Stelle ausdenken (z.B. Aushilfe als Ticketverkäufer an der Kasse oder so)