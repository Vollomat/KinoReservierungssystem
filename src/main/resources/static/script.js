// noinspection JSValidateTypes

const container = document.querySelector('.container');
const seats = document.querySelectorAll('.row .seat:not(.occupied)');
const count = document.getElementById('count');
const total = document.getElementById('total');
const movieSelect = document.getElementById('movie');

populateUI();

let ticketPrice = +movieSelect.value;

// Save selected movie index and price
function setMovieData(movieIndex, moviePrice) {
    localStorage.setItem('selectedMovieIndex', movieIndex);
    localStorage.setItem('selectedMoviePrice', moviePrice);
}

// Update total and count
function updateSelectedCount() {
    const selectedSeats = document.querySelectorAll('.row .seat.selected');

    const seatsIndex = [...selectedSeats].map(seat => [...seats].indexOf(seat));

    localStorage.setItem('selectedSeats', JSON.stringify(seatsIndex));

    const selectedSeatsCount = selectedSeats.length;

    count.innerText = selectedSeatsCount;
    total.innerText = selectedSeatsCount * ticketPrice;
}

// Get data from localstorage and populate UI
function populateUI() {
    const selectedSeats = JSON.parse(localStorage.getItem('selectedSeats'));

    if (selectedSeats !== null && selectedSeats.length > 0) {
        seats.forEach((seat, index) => {
            if (selectedSeats.indexOf(index) > -1) {
                seat.classList.add('selected');
            }
        });
    }

    const selectedMovieIndex = localStorage.getItem('selectedMovieIndex');

    if (selectedMovieIndex !== null) {
        movieSelect.selectedIndex = selectedMovieIndex;
    }
}

// Movie select event
movieSelect.addEventListener('change', e => {
    ticketPrice = +e.target.value;
    setMovieData(e.target.selectedIndex, e.target.value);
    updateSelectedCount();
});

// Seat click event
container.addEventListener('click', e => {
    if (
        e.target.classList.contains('seat') &&
        !e.target.classList.contains('occupied')
    ) {
        e.target.classList.toggle('selected');

        updateSelectedCount();
    }
});

// Initial count and total set
updateSelectedCount();

//Abbruch Button bei der Ticketbuchung
function abbruchStartseite() {
    var r = confirm("Dein Ticketbuchungsprozess wird nicht gespeichert, wenn du diese Seite verlässt. " +
        "Möchtest du trotzdem zurück zur Startseite?");
    if (r === true) {
        window.location.href="../index.html";
        sessionStorage.clear();
    }
}

// Saalplan: man kann nur weiter, wenn min. 1 Sitz ausgewählt ist und man kann nur so viele Leute eingeben, wie Sitzplätze ausgewählt sind
function sitzgewaehlt() {
    alert("Bin in sitzgewählt");
    const selectedSeats = document.querySelectorAll('.row .seat.selected');
    const selectedSeatsCount = selectedSeats.length;
    const unterNeun = document.getElementById('anzahl1').value;
    const unterAchtzehn = document.getElementById('anzahl2').value;
    const unterSechsundzwanzig = document.getElementById('anzahl3').value;

    const unterNeunInt = unterNeun * 1;
    const unterAchtzehnInt = unterAchtzehn * 1;
    const unterSechsundzwanzigInt = unterSechsundzwanzig * 1;

    const rabattiert = unterNeunInt + unterAchtzehnInt + unterSechsundzwanzigInt;

    if (selectedSeatsCount === 0) {
        alert("Du musst mindestens einen Sitz auswählen, um die Buchung fortsetzen zu können.");
    } else if (selectedSeatsCount < rabattiert) {
        alert("Die Anzahl an eingegebenen Personen übersteigt die Anzahl von ausgewählten Sitzen.");
    } else {
        window.location.href = "../ticketbuchung/registrierung.html";
        //sitzplaetze und Alter werden in ein Array und dann in localStorage gespeichert
        console.log(selectedSeats);
        let selectedSaeatsAndAge = new Array();
        let kleinerNeun = unterNeunInt;
        let kleinerAchtzehn = unterAchtzehnInt;
        let kleinerSechUndZwanzig = unterSechsundzwanzigInt;
        for (var i = 0; i < selectedSeats.length; i++) {
            const splitArray = selectedSeats[i].id.split(" ");
            console.log(splitArray);
            var reihe = parseInt(splitArray[1]);
            console.log(reihe);
            var spalte = parseInt(splitArray[3]);
            console.log(spalte);
            if (kleinerNeun > 0){
                selectedSaeatsAndAge[i] = [reihe, spalte, 8];
                kleinerNeun -= 1;
                console.log(selectedSaeatsAndAge[i]);
            } else if (kleinerAchtzehn > 0){
                selectedSaeatsAndAge[i] = [reihe, spalte, 17];
                kleinerAchtzehn -= 1;
                console.log(selectedSaeatsAndAge[i]);
            } else if (kleinerSechUndZwanzig > 0){
                selectedSaeatsAndAge[i] = [reihe, spalte, 25];
                kleinerSechUndZwanzig -= 1;
                console.log(selectedSaeatsAndAge[i]);
            } else {
                selectedSaeatsAndAge[i] = [reihe, spalte, 30];
                console.log(selectedSaeatsAndAge[i]);
            }
        }
        console.log(selectedSaeatsAndAge);
        sessionStorage.setItem('s_selectedSeats', JSON.stringify(selectedSaeatsAndAge));
    }
}


// Saalplan Checkboxen
function checkbox1() {
    var checkBox = document.getElementById("input1");
    var text = document.getElementById("anzahl1");
    if (checkBox.checked === true){
        text.style.display = "block";
    } else {
        text.style.display = "none";
    }
}

function checkbox2() {
    var checkBox = document.getElementById("input2");
    var text = document.getElementById("anzahl2");
    if (checkBox.checked === true){
        text.style.display = "block";
    } else {
        text.style.display = "none";
    }
}

function checkbox3() {
    var checkBox = document.getElementById("input3");
    var text = document.getElementById("anzahl3");
    if (checkBox.checked === true){
        text.style.display = "block";
    } else {
        text.style.display = "none";
    }
}

/*Registrierung: Überprüfung, ob Passwort1 und Passowrt2 den gleichen String enthalten
function passwortGleich() {
    const passwort1 = document.getElementById('passwort').value;
    const passwort2 = document.getElementById('passwort2').value;

    if (passwort1 === passwort2){
        window.location.href="../ticketbuchung/bezahlen.html";
    }else{
        alert("Die eingegebenen Passwörter stimmen nicht überein.");
    }
}--> ist in 'allesAusgefülltRegistrierung()' integriert*/

//Registrierung
async function UserAction() {
    var xhr = new XMLHttpRequest(); //invoke a new instance of the XMLHttpRequest
    xhr.onload = successRegister; // call success function if request is successful
    xhr.onerror = errorRegister;  // call error function if request failed
    xhr.open('POST', 'http://localhost:8080/kunden/register/'); // open a GET request
    xhr.setRequestHeader('Content-Type', 'application/json');
    let vorname = document.querySelector("#vorname").value;
    let nachname = document.querySelector("#nachname").value;
    let email = document.querySelector("#email").value;
    let alterInJahren = document.querySelector("#alter").value;
    let strasse = document.querySelector("#strasse").value;
    let hausnummer = document.querySelector("#hausnummer").value;
    let plz = document.querySelector("#plz").value;
    let ort = document.querySelector("#ort").value;
    let passwort = document.querySelector("#passwort").value;
    xhr.send(JSON.stringify({
        vorname:vorname,
        nachname:nachname,
        email:email,
        alterInJahren:alterInJahren,
        strasse:strasse,
        hausnummer:hausnummer,
        postleitzahl:plz,
        ort:ort,
        passwort:passwort
    })); // send the request to the server.

    //E-Mail wird in sessionstorage gespeichert
    sessionStorage.setItem('s_email', email);
    sessionStorage.setItem('s_vorname', vorname);
    sessionStorage.setItem('s_nachname', nachname);
    sessionStorage.setItem('s_strasse', strasse);
    sessionStorage.setItem('s_hausnummer', hausnummer);
    sessionStorage.setItem('s_plz', plz);
    sessionStorage.setItem('s_ort', ort);
}

function successRegister() {
    var data = JSON.parse(this.responseText.toString()); //parse the string to JSON
    alert(data);
    if(data === false){
        alert("Ihre Daten konnten nicht korrekt verarbeitet werden!");
    } else {
        bestellungAnlegen();
    }
}

// function to handle error
function errorRegister(err) {
    alert('Request Failed');
}

//Registrierung: Überprüfung, ob alle Felder ausgefüllt sind
function allesAusgefuelltRegistrierung(){
    const regVorname = document.getElementById('vorname').value;
    const regNachname = document.getElementById('nachname').value;
    const regMail = document.getElementById('email').value;
    const regAlter = document.getElementById('alter').value;
    const regStrasse = document.getElementById('strasse').value;
    const regHausnummer = document.getElementById('hausnummer').value;
    const regPLZ = document.getElementById('plz').value;
    const regOrt = document.getElementById('ort').value;
    const regPasswort = document.getElementById('passwort').value;
    const regPasswort2 = document.getElementById('passwort2').value;

    if ((regVorname === "") || (regNachname === "") || (regMail === "") || (regAlter === "") || (regStrasse === "") || (regHausnummer === "") || (regPLZ === "") || (regOrt === "") || (regPasswort === "") || (regPasswort2 === "")){
        alert("Du musst alle Felder ausfüllen, um deine Buchung fortsetzen zu können.");
        if (regPasswort !== regPasswort2){
            alert("Die eingegebenen Passwörter stimmen nicht überein.");
        }
    }else {
        UserAction();
    }
}

//login
async function login() {
    var xhr = new XMLHttpRequest(); //invoke a new instance of the XMLHttpRequest
    xhr.onload = successLogin; // call success function if request is successful
    xhr.onerror = errorLogin;  // call error function if request failed
    xhr.open('POST', 'http://localhost:8080/kunden/login'); // open a POST request
    xhr.setRequestHeader('Content-Type', 'application/json');
    let email = document.querySelector("#email").value; //email statt usernamen
    let passwort = document.querySelector("#passwort").value;
    xhr.send(JSON.stringify({
        email:email,
        passwort:passwort,
    })); // send the request to the server.
}

function successLogin() {
    const UserData = JSON.parse(this.responseText.toString()); //parse the string to JSON
    if (UserData != null) {
        var email = UserData.email;
        sessionStorage.setItem('s_email', email);
        sessionStorage.setItem('s_vorname', UserData.vorname);
        sessionStorage.setItem('s_nachname', UserData.nachname);
        bestellungAnlegen();
    } else {
        alert("Ihre Login-Daten sind falsch!");
    }
}

// function to handle error
function errorLogin(err) {
    alert('Request Failed');
}

//Login: Überprüfung, ob alle Felder ausgefüllt sind
function allesAusgefuelltLogin(){
    const loginMail = document.getElementById('email').value;
    const loginPassword = document.getElementById('passwort').value;

    if ((loginMail === "") || (loginPassword === "")){
        alert("Du musst alle Felder ausfüllen, um deine Buchung fortsetzen zu können.")
    }else {
        alert("vorLogin");
        login();
    }
}

//Gast: Überprüfung, ob alle Felder ausgefüllt sind
function allesAusgefuelltGast(){
    const gastVorname = document.getElementById('vorname').value;
    const gastNachname = document.getElementById('nachname').value;
    const gastMail = document.getElementById('email').value;
    const gastAlter = document.getElementById('alter').value;

    if ((gastVorname === "") || (gastNachname === "") || (gastMail === "") || (gastAlter === "")){
        alert("Du musst alle Felder ausfüllen, um deine Buchung fortsetzen zu können.")
    }else {
        sessionStorage.setItem("s_vorname", gastVorname);
        sessionStorage.setItem("s_nachname", gastNachname);
        sessionStorage.setItem("s_email", gastMail);
        bestellungAnlegen();
    }
}

//aktuell ausgewählter Film wird mitgegeben und die zugehörigen Vorstellungen werden zurückgeliefert
async function getAndShowVorstellungen(currentfilm){
    var xhr = new XMLHttpRequest(); //invoke a new instance of the XMLHttpRequest
    xhr.onload = success; // call success function if request is successful
    xhr.onerror = error;  // call error function if request failed
    xhr.open('POST', 'http://localhost:8080/vorstellungen/filmbekommen/'); // open a GET request
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(currentfilm);  // send the request to the server.
    //sessionstorage film setzen
    sessionStorage.setItem('s_Film', currentfilm);
}

//passt die zurückgelieferten Vorstellungs-Zeiten abhängig vom ausgewählten Film an
function success() {
    var film = sessionStorage.getItem('s_Film');
    var vorstellungen = JSON.parse(this.responseText.toString()); //parse the string to JSON
    if (film === 'Transformers') {
        document.getElementById("ersteVorst_transf").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_transf").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_transf").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_transf").innerText = vorstellungen[3].startuhrzeit;
        document.getElementById('ersteVorst_transf').value = vorstellungen[0].startuhrzeit;
        document.getElementById('zweiteVorst_transf').value = vorstellungen[1].startuhrzeit;
        document.getElementById('dritteVorst_transf').value = vorstellungen[2].startuhrzeit;
        document.getElementById('vierteVorst_transf').value = vorstellungen[3].startuhrzeit;
    } else if (film === 'Black Widow') {
        document.getElementById("ersteVorst_bw").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_bw").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_bw").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_bw").innerText = vorstellungen[3].startuhrzeit;
        document.getElementById('ersteVorst_bw').value = vorstellungen[0].startuhrzeit;
        document.getElementById('zweiteVorst_bw').value = vorstellungen[1].startuhrzeit;
        document.getElementById('dritteVorst_bw').value = vorstellungen[2].startuhrzeit;
        document.getElementById('vierteVorst_bw').value = vorstellungen[3].startuhrzeit
    } else if (film === 'Wonder Woman 1984') {
        document.getElementById("ersteVorst_ww").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_ww").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_ww").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_ww").innerText = vorstellungen[3].startuhrzeit;
        document.getElementById('ersteVorst_ww').value = vorstellungen[0].startuhrzeit;
        document.getElementById('zweiteVorst_ww').value = vorstellungen[1].startuhrzeit;
        document.getElementById('dritteVorst_ww').value = vorstellungen[2].startuhrzeit;
        document.getElementById('vierteVorst_ww').value = vorstellungen[3].startuhrzeit
    } else if (film === 'Soul') {
        document.getElementById("ersteVorst_soul").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_soul").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_soul").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_soul").innerText = vorstellungen[3].startuhrzeit;
        document.getElementById('ersteVorst_soul').value = vorstellungen[0].startuhrzeit;
        document.getElementById('zweiteVorst_soul').value = vorstellungen[1].startuhrzeit;
        document.getElementById('dritteVorst_soul').value = vorstellungen[2].startuhrzeit;
        document.getElementById('vierteVorst_soul').value = vorstellungen[3].startuhrzeit
    } else if (film === 'Free Guy') {
        document.getElementById("ersteVorst_fg").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_fg").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_fg").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_fg").innerText = vorstellungen[3].startuhrzeit;
        document.getElementById("ersteVorst_fg").value = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_fg").value = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_fg").value = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_fg").value = vorstellungen[3].startuhrzeit;
    } else if (film === 'The Day After Tomorrow') {
        document.getElementById("ersteVorst_tdat").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_tdat").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_tdat").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_tdat").innerText = vorstellungen[3].startuhrzeit;
        document.getElementById("ersteVorst_tdat").value = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_tdat").value = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_tdat").value = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_tdat").value = vorstellungen[3].startuhrzeit;
    } else if (film === 'The Hobbit: An Unexpected Journey') {
        document.getElementById("ersteVorst_hobbit").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_hobbit").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_hobbit").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_hobbit").innerText = vorstellungen[3].startuhrzeit;
        document.getElementById("ersteVorst_hobbit").value = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_hobbit").value = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_hobbit").value = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_hobbit").value = vorstellungen[3].startuhrzeit;
    } else if (film === 'Creed') {
        document.getElementById("ersteVorst_creed").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_creed").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_creed").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_creed").innerText = vorstellungen[3].startuhrzeit;
        document.getElementById("ersteVorst_creed").value = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_creed").value = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_creed").value = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_creed").value = vorstellungen[3].startuhrzeit;
    } else if (film === 'The Avengers') {
        document.getElementById("ersteVorst_avengers").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_avengers").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_avengers").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_avengers").innerText = vorstellungen[3].startuhrzeit;
        document.getElementById("ersteVorst_avengers").value = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_avengers").value = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_avengers").value = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_avengers").value = vorstellungen[3].startuhrzeit;
    } else if (film === 'Avatar') {
        document.getElementById("ersteVorst_avatar").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_avatar").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_avatar").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_avatar").innerText = vorstellungen[3].startuhrzeit;
        document.getElementById("ersteVorst_avatar").value = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_avatar").value = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_avatar").value = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_avatar").value = vorstellungen[3].startuhrzeit;
    } else if (film === 'Joker') {
        document.getElementById("ersteVorst_joker").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_joker").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_joker").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_joker").innerText = vorstellungen[3].startuhrzeit;
        document.getElementById("ersteVorst_joker").value = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_joker").value = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_joker").value = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_joker").value = vorstellungen[3].startuhrzeit;
    } else if (film === 'Terminator 2: Judgment Day') {
        document.getElementById("ersteVorst_t2jd").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_t2jd").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_t2jd").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_t2jd").innerText = vorstellungen[3].startuhrzeit;
        document.getElementById("ersteVorst_t2jd").value = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_t2jd").value = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_t2jd").value = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_t2jd").value = vorstellungen[3].startuhrzeit;
    }
}

// function to handle error
function error(err) {
    alert('Request Failed');
}

// Durch senden der E-Mail wird eine Bestellung in der Datenbank angelegt und eine BestellId
// als eindeutiger Identifikator zurückgesendet
function bestellungAnlegen(){
    var xhr = new XMLHttpRequest(); //invoke a new instance of the XMLHttpRequest
    xhr.onreadystatechange = function() {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            reserviereAlleSitzplaetze(xhr.responseText);
            sessionStorage.setItem('s_bestellId', xhr.responseText);
            window.location.href = "../ticketbuchung/bezahlen.html";
        }
    }
    xhr.open('POST', 'http://localhost:8080/bestellungen', true);
    var email = sessionStorage.getItem('s_email')
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify({
        email:email
    }));
    var bestellID = JSON.parse(this.responseText.toString());
    sessionStorage.setItem('s_bestellId', bestellID);
    reserviereAlleSitzplaetze(bestellID);
}


//die im Sessionstorage gespeicherten Sitzplätze werden ausgelesen und einzeln an die Datenbank gegeben -> Reservierung
function reserviereAlleSitzplaetze(bestellId){
    var selectedSeats = JSON.parse(sessionStorage.getItem('s_selectedSeats'));
    console.log(selectedSeats);
    var startuhrzeit = sessionStorage.getItem('s_Vorst');
    var filmname = sessionStorage.getItem('s_Film');
    for (let i = 0; i < selectedSeats.length; i++) {
        var sitzplatzreihe = selectedSeats[i][0];
        var sitzsplatzspalte = selectedSeats[i][1];
        var alterInJahren = selectedSeats[i][2];
        sitzplatzReservieren(alterInJahren, sitzplatzreihe, sitzsplatzspalte, startuhrzeit, filmname, bestellId);
    }
}

// sorgt dafür, dass ein Sitzplatz reserviert wird
function sitzplatzReservieren(alterInJahren, sitzplatzreihe, sitzsplatzspalte, startuhrzeit, filmname, bestellId){
    alert("bin in Sitzplatzreservieren");
    var xhr = new XMLHttpRequest(); //invoke a new instance of the XMLHttpRequest
    xhr.onload = successReservierung; // call success function if request is successful
    xhr.onerror = errorReservierung;  // call error function if request failed
    xhr.open('POST', 'http://localhost:8080/tickets'); // open a GET request
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify({
        startuhrzeit: startuhrzeit,
        filmName: filmname,
        preis: 28.50,
        alterInJahren: alterInJahren,
        sitzplatzreihe: sitzplatzreihe,
        sitzplatzspalte: sitzsplatzspalte,
        bestellungID: bestellId
    }));  // send the request to the server.
}

function successReservierung(){
    var isSitzplatzReserviert = JSON.parse(this.responseText.toString());
    if(isSitzplatzReserviert === false){
        alert("Leider wurde einer Ihrer Sitzplaetze in der Zwischenzeit ausgebucht. Bitte buchen sie erneut!");
    }
}

function errorReservierung(){
    alert("Sitzplatzreservierung ist fehlgeschlagen. Bitte wählen sie Ihre Sitze erneut aus.")
}


