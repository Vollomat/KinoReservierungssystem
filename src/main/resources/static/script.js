const container = document.querySelector('.container');
const seats = document.querySelectorAll('.row .seat:not(.occupied)');
const count = document.getElementById('count');
const total = document.getElementById('total');
const movieSelect = document.getElementById('movie');

populateUI();

//let ticketPrice = +movieSelect.value;

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
/*movieSelect.addEventListener('change', e => {
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
updateSelectedCount(currentfilm);
 */


//aktuell ausgewählter Film wird mitgegeben und die zugehörigen Vorstellungen werden zurückgeliefert
function getAndShowVorstellungen(currentfilm){

    var xhr = new XMLHttpRequest(); //invoke a new instance of the XMLHttpRequest
    xhr.onload = success; // call success function if request is successful
    xhr.onerror = error;  // call error function if request failed
    xhr.open('GET', 'http://localhost:8080/vorstellungen'); // open a POST request
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.responseType
    var film = currentfilm;
    xhr.send(film); // send the request to the server.
    var text = xhr.responseText;
    console.log(text);
    alert(text);
}

function success() {
    //hier müssen die zurückgelieferten Vorstellungen in die buttons gespeichert werden
    //var film = 'freeguy';
    if(film == 'freeguy') {}
    document.getElementById("ersteVorst").innerText = vorstellungen[0];
    document.getElementById("zweiteVorst").innerText = vorstellungen[1];
    document.getElementById("dritteVorst").innerText = vorstellungen[2];
    document.getElementById("vierteVorst").innerText = vorstellungen[3];
    document.getElementById("fünfteVorst").innerText = vorstellungen[4];
}

// function to handle error
function error(err) {
  alert('Request Failed');
}


//HIER WERDEN ALLE DATEN EINER BESTELLUNG AN DEN SERVER GESENDET

