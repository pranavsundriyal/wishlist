/**
 * Created by psundriyal on 6/1/16.
 */
$(document).ready(function() {
    $("#search").click(function () {
        $("#status").append("Searching for Results ........");
        $("#results").empty();
        $.ajax({
            type: "GET",
            url: "searchExp",
            data: {
                origin: $("#origin").val(),
                dest: $("#dest").val(),
                departure: $("#departure").val(),
                arrival: $("#arrival").val(),
                flex: $("input:radio[name=flex]:checked").val(),
                filters: getFilters()
            },
            success: function (data) {
                $("#status").empty();
                $("#results").empty();
                $("#results").append('Showing top '+data.searchResultList.length+' search results after applying filters<br>');
                $("#results").append('FILTERS : COUNT<br>')
                $.each( data.filterCountMap, function( key, value) {
                    $("#results").append(key+ ' : ' + value +'<br>');
                })
                $("#results").append('<br>');
                for(var i=0; i<data.searchResultList.length; i++){
                    $("#results").append('<hr>');
                    $("#results").append('<a href="'+data.searchResultList[i].url+'" target="_blank">' +
                        'Price :' + data.searchResultList[i].price + '</a><br>');
                    for (var j=0; j<data.searchResultList[i].legList.length; j++) {
                        var leg = data.searchResultList[i].legList[j];
                        $("#results").append('Departure Time : ' +getDateTime(leg.departureTime)
                            +' | Arrival Time: '+getDateTime(leg.arrivalTime)+ '<br>');
                        $("#results").append('Duration: '+getTime(leg.totalDuration)+' | Layover: '+getTime(leg.layover)
                            + '<br>');
                        for (var k=0; k<leg.segments.length; k++){
                            segment = leg.segments[k];
                            $("#results").append(segment.departureAirportLocation+'('+segment.departureAirportCode+') -> '
                                + segment.arrivalAirportLocation +'('+segment.arrivalAirportCode+')  | Flight No: '
                                +segment.airlineName+ ' '+segment.flightNumber+'<br>');
                        }
                        $("#results").append('<br>');
                    }
                    $("#results").append('<hr>');
                    $("#results").append('<br><br>');
                }
            },
            error: function(data) {
                //Do Something to handle error
            }
        });
    });
});

$(document).ready(function() {
    $("#save").click(function () {
        if ($("#email").val().length > 0) {
            $.ajax({
                type: "GET",
                url: "save",
                data: {
                    origin: $("#origin").val(),
                    dest: $("#dest").val(),
                    departure: $("#departure").val(),
                    arrival: $("#arrival").val(),
                    flex: $("input:radio[name=flex]:checked").val(),
                    filters: getFilters(),
                    email: $("#email").val()
                },
                success: function (data) {
                    $('#rules').empty();
                    if (data) {
                        $("#rules").append('<br>Sucessfully subscribed<br>');
                    } else {
                        $("#rules").append('<br>error while subscribing<br>');
                    }
                }
            });
        }
    });
});

$(document).ready(function() {
    $("#search").click(function () {
        $.ajax({
            type: "GET",
            url: "trend",
            data: {
                origin: $("#origin").val(),
                dest: $("#dest").val(),
                departure: $("#departure").val()
            },
            success: function (data) {
                $('#chart').empty();
                if (data) {
                    drawChart(data)
                } else {
                    $("#trendsResult").append('<br>error while getting trends<br>');
                }
            }
        });
    });
});

$(document).ready(function() {
    $("#lookup").click(function () {
        if ($("#email").val().length >0) {
            $.ajax({
                type: "GET",
                url: "lookup",
                data: {
                    email: $("#email").val()
                },
                success: function (data) {
                    $('#rules').empty();
                    $("#rules").append(data);
                }
            });
        }
    });
});

$(document).ready(function() {
    $("#unsubscribe").click(function () {
        if ($("#email").val().length >0) {
            $.ajax({
                type: "GET",
                url: "unsubscribe",
                data: {
                    email: $("#email").val()
                },
                success: function (data) {
                    $('#rules').empty();
                    if (data) {
                        $("#rules").append('<br>Sucessfully unsubscribed<br>');
                    } else {
                        $("#rules").append('<br>error while unsubscribing<br>');
                    }
                }
            });
        }
    });
});




function getTime(time) {
    return time.hour+' hr: ' + time.minute + ' min'
}


function getDateTime(dateTime) {
    return +dateTime.dayOfMonth+' '+dateTime.month+' '+dateTime.hour+' hr:'+dateTime.minute+' min'
}

function getFilters(){
    var comma= ',';
    var filters = '['
    if($("#price").val().length > 0) {
        var price = '{"filterType" : "price","filterMap" : {"lowerLimit" : "' + $("#price").val() + '"}}';
        filters = filters+price;
    }
    if($("#airline").val().length > 0) {
        var airline = ',{"filterType" : "airline","filterMap" : {"qualifier" : "'+
            $("input:radio[name=airline]:checked").val() +'","list" : "' +$("#airline").val()+'"}}'
        filters = filters+airline;
    }
    if($("#flightNumber").val().length > 0) {
        var flightNumber = ',{"filterType" : "flightNumber","filterMap" : {"qualifier" : "'+
            $("input:radio[name=flightNumber]:checked").val() +'","list" : "' +$("#flightNumber").val()+'"}}'
        filters = filters+flightNumber;
    }
    if($("#connection").val().length > 0) {
        var airline = ',{"filterType" : "connectionStop","filterMap" : {"qualifier" : "'+
            $("input:radio[name=connection]:checked").val()+'","list" : "' +$("#connection").val()+'"}}'
        filters = filters+airline;
    }
    if($("#duration").val().length > 0) {
        var duration = ',{"filterType" : "duration","filterMap" : {"hours" : "' + $("#duration").val() + '"}}';
        filters = filters+duration;
    }

    if($("#layover").val().length > 0) {
        var duration = ',{"filterType" : "layover","filterMap" : {"hours" : "' + $("#layover").val() + '"}}';
        filters = filters+duration;
    }

    if($("#stops").val().length > 0) {
        var stops = ',{"filterType" : "stops","filterMap" : {"noStops" : "' + $("#stops").val() + '"}}';
        filters = filters+stops;
    }

    if($("#departureTime").val().length > 0) {
        var departTime = ',{"filterType" : "departureTime","filterMap" : {"hours" : "' + $("#departureTime").val() +
            '","time" : "' + $("input:radio[name=departTime]:checked").val() + '"}}';
        filters = filters+departTime;
    }

    if($("#arrivalTime").val().length > 0) {
        var departTime = ',{"filterType" : "arrivalTime","filterMap" : {"hours" : "' + $("#arrivalTime").val() +
            '","time" : "' + $("input:radio[name=arrivalTime]:checked").val() + '"}}';
        filters = filters+departTime;
    }
    filters = filters+']';

    return filters;
}
$(function() {
    $( "#datepicker" ).datepicker({ dateFormat: 'yy-mm-dd' });
});

$(function() {
    $( "#departure" ).datepicker({
        dateFormat: 'yy-mm-dd',
        defaultDate: "+1w",
        changeMonth: true,
        numberOfMonths: 2,
        onClose: function( selectedDate ) {
            $( "#arrival" ).datepicker( "option", "minDate", selectedDate );
        }
    });
    $( "#arrival" ).datepicker({
        dateFormat: 'yy-mm-dd',
        defaultDate: "+1w",
        changeMonth: true,
        numberOfMonths: 2,
        onClose: function( selectedDate ) {
            $( "#departure" ).datepicker( "option", "maxDate", selectedDate );
        }
    });
});