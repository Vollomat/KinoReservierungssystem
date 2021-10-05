________________________________________________________________________________________________________________________
WAS NOCH ZU TUN IST:
________________________________________________________________________________________________________________________

Elisa:

- Hover Effekt Zusammenfassung bei Programm: Text hinzufügen


Nandini

-login/register: für jedes wird ein eigenes Mapping! benötigt

- Wenn man sich eingeloggt hat, sollte man seinen Bestellstatus einsehen können
(muss noch Serverdaten erhalten, Interface ist schon da (siehe footer))

- Login2 muss von der Datenbank überprüft werden (wie normaler Login, befindet sich unter 'footarea')


Dana:

- User Interface für die Mail erstellen, die der User erhält, wenn er sein Passwort vergessen hat

________________________________________________________________________________________________________________________
Falls keine Aufgaben mehr, einfach etwas rausnehmen :)
________________________________________________________________________________________________________________________

- Die Suchleiste muss implementiert werden (oder lassen wir die lieber ganz weg?)

- Die Karte auf der Anfahrtsseite muss erneuert werden (was für Warendingens???)

________________________________________________________________________________________________________________________
WAS AUF DEN EINZELNEN SEITEN NOCH ZU TUN IST:
________________________________________________________________________________________________________________________

saalplan.html:
Es müssen Funktionen geschrieben werden für:

- dass man nur auf Bestätigen klicken kann, wenn mindestens ein Sitz ausgewählt ist
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
