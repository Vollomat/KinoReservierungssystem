Information für Frontend:
Wenn ihr das Ticket mit POST an den Spring Boot Server sendet dann wie folgt:
POST an: http://localhost:8080/tickets

Beispiel JSON:
{
"email":"patrick.vollstedt2@gmx.de",
"startuhrzeit":"18:00",
"kinosaalNummer":2,
"filmName":"Terminator Genisys",
"preis":"22,50",
"status":"BEZAHLT"
}

--> Preis mit drinnen da zuvor ja schon vom Server der Preis angefragt werden kann

Aktuelles Problem: Mehrere Sitzplätze abspeichern im Ticket



________________________________________________________________________________________________________________________
WAS NOCH ZU TUN IST:
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
WAS AUF DEN EINZELNEN SEITEN NOCH ZU TUN IST:
________________________________________________________________________________________________________________________

saalplan.html:
Es müssen Funktionen geschrieben werden für:

- dass nur so viele Zahlen unten eingegeben werden können, wie Sitze ausgewählt wurden
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