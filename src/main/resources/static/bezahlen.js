// Als erstes verbergen wir den Schuh
document.getElementById("schuh").style.display = "none";

function produktAnzeigen(nr) {
  if (nr === 1) {
    document.getElementById("tshirt").style.display = "block";
    document.getElementById("schuh").style.display = "none";
  }
  if (nr === 2) {
    document.getElementById("tshirt").style.display = "none";
    document.getElementById("schuh").style.display = "block";
  }
}

function initPayPalButton() {
  paypal.Buttons({
    style: {
      shape: 'pill',
      color: 'blue',
      layout: 'vertical',
      label: 'buynow',
      
    },

    createOrder: function(data, actions) {
      return actions.order.create({
        purchase_units: [{"description":"T-Shirt","amount":{"currency_code":"EUR","value":1}}]
      });
    },

    onApprove: function(data, actions) {
      return actions.order.capture().then(function(details) {
        alert('Das hat geklappt! Super cool! ' + details.payer.name.given_name + '!');
      });
    },

    onError: function(err) {
      console.log(err);
    }
  }).render('#paypal-button-tshirt');
  paypal.Buttons({
    style: {
      shape: 'pill',
      color: 'blue',
      layout: 'vertical',
      label: 'buynow',
      
    },

    createOrder: function(data, actions) {
      return actions.order.create({
        purchase_units: [{"description":"Schuh","amount":{"currency_code":"EUR","value":1}}]
      });
    },

    onApprove: function(data, actions) {
      return actions.order.capture().then(function(details) {
        alert('Das hat geklappt! Super cool! ' + details.payer.name.given_name + '!');
      });
    },

    onError: function(err) {
      console.log(err);
    }
  }).render('#paypal-button-schuh');
}
initPayPalButton();