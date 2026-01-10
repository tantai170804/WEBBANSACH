document.getElementById('paymentOption').addEventListener('change', function() {
    var paymentMethodDiv = document.getElementById('paymentMethodDiv');
    if (this.value === 'postpay') {
        paymentMethodDiv.style.display = 'block';
        document.getElementById('paymentMethod').required = true;
        document.getElementById('bankAccountNumber').required = true;
    } else {
        paymentMethodDiv.style.display = 'none';
        document.getElementById('paymentMethod').required = false;
        document.getElementById('bankAccountNumber').required = false;
    }
});