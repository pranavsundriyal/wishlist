/**
 * Created by psundriyal on 6/1/16.
 */
google.charts.load('current', {'packages':['line']});

function drawChart(data) {
    $("#advice").empty()
    trends = data.recommended.trends

    var price;
    if($("#price").val() > 0) {
        price = parseFloat($("#price").val())
    }
    var tripType = 1;
    if($("#arrival").val() == 0) {
        tripType = 2;
    }
    var x = new Array(trends.length);
    for (var i = 0; i < trends.length; i++) {
        x[i] = new Array(3);
    }

    for (var i=0; i< trends.length; i++) {
        x[i][0] = trends[i].searchDate
        x[i][1] = parseFloat(trends[i].min)/tripType
        x[i][2] = parseFloat(trends[i].median)/tripType
        x[i][3] = parseFloat(trends[i].max)/tripType
        if (price != null) {
            x[i][4] = price;
        }
    }


    var chartData = new google.visualization.DataTable();
    chartData.addColumn('string', 'Date');
    chartData.addColumn('number', 'Minimum Price');
    chartData.addColumn('number', 'Median Price');
    chartData.addColumn('number', 'High Price');
    chartData.addColumn('number', 'Price Limit');

    chartData.addRows(x);

    var options = {
        chart: {
            title: 'Date V/S Price',
            subtitle: 'in dollars (USD)'
        },
        width: 700,
        height: 500
    };

    var chart = new google.charts.Line(document.getElementById('chart'));

    chart.draw(chartData, options);
    prediction(data, tripType)
}

function prediction(data, tripType) {
    $("#advice").append('<b>Predication</b><br>'+'Lowest Price : ' + data.lowestPrice/tripType + ' in last 15 days<br>')
    var direction;
    var pricePredication;
    if(data.recommended.predictionNextWeek.prediction > 0) {
        direction = 'increase'
    } else {
        direction = 'decrease'
        pricePredication = - (parseFloat(data.recommended.predictionNextWeek.prediction))
    }
    $("#advice").append('Prediction for next week is price will ' + direction + ' by '
        + pricePredication+' with confidence ' +
        data.recommended.predictionNextWeek.confidence)

}
