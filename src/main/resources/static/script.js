// noinspection JSValidateTypes

const container = document.querySelector('.container');
const seats = document.querySelectorAll('.row .seat:not(.occupied)');
const count = document.getElementById('count');
const total = document.getElementById('total');
const movieSelect = document.getElementById('movie');

//HIER WERDEN ALLE DATEN EINER BESTELLUNG AN DEN SERVER GESENDET
async function getAndShowSaalplan(){
    var xhr = new XMLHttpRequest(); //invoke a new instance of the XMLHttpRequest
    xhr.onload = successSaalplan; // call success function if request is successful
    xhr.onerror = errorSaalpan;  // call error function if request failed
    xhr.open('POST', 'http://localhost:8080/vorstellungen/sitzplaetze'); // open a GET request
    xhr.setRequestHeader('Content-Type', 'application/json');
    let filmName = sessionStorage.getItem('s_Film');
    let startuhrzeit = sessionStorage.getItem('s_Vorst');
    console.log(startuhrzeit);
    xhr.send(JSON.stringify({
        filmName:filmName,
        startuhrzeit:startuhrzeit
    })); // send the request to the server.
}

function successSaalplan(){
    var sitzplaetze = JSON.parse(this.responseText.toString()); //parse the string to JSON
    const AllSeats = document.querySelectorAll('.row .seat');
    console.log(AllSeats);
    console.log(sitzplaetze);
    for (var sitz = 0; sitz <= (sitzplaetze.length - 1); sitz++) {
        var IdName = "Reihe " + sitzplaetze[sitz].reihe + " Platz " + sitzplaetze[sitz].spalte;
        var reihe = sitzplaetze[sitz].reihe;
        console.log(IdName);
        document.getElementById(IdName).classList.remove("occupied");
        console.log("Status wurde zurückgesetzt");
        var statusVomSitzplatz = sitzplaetze[sitz].statusVomSitzplatz;
        console.log(statusVomSitzplatz);
        if(statusVomSitzplatz === "BESETZT"){
            console.log("Bin in besetzt");
            document.getElementById(IdName).classList.add("occupied");
        }
    }
}

function errorSaalpan(){
    alert('Request Failed');
}

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
    total.innerText = selectedSeatsCount * ticketPrice;s
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
    alert("bin im eventlistener");
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
    }
}

// Saalplan: man kann nur weiter, wenn min. 1 Sitz ausgewählt ist und man kann nur so viele Leute eingeben, wie Sitzplätze ausgewählt sind
function sitzgewaehlt() {
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
    }else if (selectedSeatsCount < rabattiert){
        alert("Die Anzahl an eingegebenen Personen übersteigt die Anzahl von ausgewählten Sitzen.");
    }else{
        window.location.href="../ticketbuchung/registrierung.html";
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
        document.getElementById('ersteVorst_transf').value = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_transf").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_transf").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_transf").innerText = vorstellungen[3].startuhrzeit;
    } else if (film === 'Black Widow') {
        document.getElementById("ersteVorst_bw").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_bw").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_bw").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_bw").innerText = vorstellungen[3].startuhrzeit;
    } else if (film === 'Wonder Woman 1984') {
        document.getElementById("ersteVorst_ww").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_ww").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_ww").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_ww").innerText = vorstellungen[3].startuhrzeit;
    } else if (film === 'Soul') {
        document.getElementById("ersteVorst_soul").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_soul").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_soul").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_soul").innerText = vorstellungen[3].startuhrzeit;
    } else if (film === 'Free Guy') {
        document.getElementById("ersteVorst_fg").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_fg").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_fg").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_fg").innerText = vorstellungen[3].startuhrzeit;
    } else if (film === 'The Day After Tomorrow') {
        document.getElementById("ersteVorst_tdat").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_tdat").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_tdat").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_tdat").innerText = vorstellungen[3].startuhrzeit;
    } else if (film === 'The Hobbit: An Unexpected Journey') {
        document.getElementById("ersteVorst_hobbit").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_hobbit").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_hobbit").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_hobbit").innerText = vorstellungen[3].startuhrzeit;
    } else if (film === 'Creed') {
        document.getElementById("ersteVorst_creed").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_creed").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_creed").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_creed").innerText = vorstellungen[3].startuhrzeit;
    } else if (film === 'The Avengers') {
        document.getElementById("ersteVorst_avengers").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_avengers").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_avengers").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_avengers").innerText = vorstellungen[3].startuhrzeit;
    } else if (film === 'Avatar') {
        document.getElementById("ersteVorst_avatar").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_avatar").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_avatar").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_avatar").innerText = vorstellungen[3].startuhrzeit;
    } else if (film === 'Joker') {
        document.getElementById("ersteVorst_joker").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_joker").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_joker").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_joker").innerText = vorstellungen[3].startuhrzeit;
    } else if (film === 'Terminator 2: Judgment Day') {
        document.getElementById("ersteVorst_t2jd").innerText = vorstellungen[0].startuhrzeit;
        document.getElementById("zweiteVorst_t2jd").innerText = vorstellungen[1].startuhrzeit;
        document.getElementById("dritteVorst_t2jd").innerText = vorstellungen[2].startuhrzeit;
        document.getElementById("vierteVorst_t2jd").innerText = vorstellungen[3].startuhrzeit;
    }
}

// function to handle error
function error(err) {
    alert('Request Failed');
}