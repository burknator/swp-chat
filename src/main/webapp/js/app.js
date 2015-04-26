+function ($) {
    var connection = new WebSocket('ws://localhost:8080/echo');

    // When the connection is open, send some data to the server
    connection.onopen = function () {
    };

    // Log errors
    connection.onerror = function (error) {
        console.log('WebSocket Error ' + error);
    };

    // Log messages from the server
    connection.onmessage = function (e) {
        $('#timeline').append('<li>' + e.data + '</li>');
        //console.log('Server: ' + e.data);
    };

    // Ping senden um Verbindung aufrecht zu erhalten. Sieht mir aus wie ein Workaround, keine Ahnung ob man das so macht.
    setInterval(function() {
        connection.send('---ping');
    }, 2000);

    $('#send').on('click', function() {
        var message = $('#message').val();

        connection.send(message);
    });
}(jQuery);