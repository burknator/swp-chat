+function ($) {
    var connection = new WebSocket('ws://localhost:8080/socket'),
        $timeline = $('#timeline'),
        $message = $('#message');

    // When the connection is open, send some data to the server
    connection.onopen = function () {
        $timeline.append('<li>[Verbindung hergestellt. Öffne einen zweiten Browser um mit dir selbst zu reden.]</li>')
    };

    // Log errors
    connection.onerror = function (error) {
        $timeline.append('<li>[Es ist ein Fehler aufgetreten. Lade die Seite, falls das nicht hilft starte den Server neu und probiere es erneut.]</li>')
    };

    // Log messages from the server
    connection.onmessage = function (e) {
        $timeline.append('<li>' + e.data + '</li>');
    };

    // Ping senden um Verbindung aufrecht zu erhalten. Sieht mir aus wie ein Workaround, keine Ahnung ob man das so macht.
    setInterval(function() {
        connection.send('---ping');
    }, 2000);

    $('#send').on('click', function(e) {
        connection.send($message.val());

        $message.val('');
    });

    $message.on('keypress', function(e) {
       if (e.which == 13) $('#send').click();
    });
}(jQuery);