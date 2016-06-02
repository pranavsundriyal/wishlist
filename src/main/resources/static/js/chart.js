/**
 * Created by psundriyal on 6/1/16.
 */
google.charts.load('current', {'packages':['line']});

function drawChart(trends) {
    var tripType = 1;
    if($("arrival").val() == null) {
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
    }
    var data = new google.visualization.DataTable();
    data.addColumn('string', 'Date');
    data.addColumn('number', 'Minimum Price');
    data.addColumn('number', 'Median Price')

    data.addRows(x);

    var options = {
        chart: {
            title: 'Date V/S Price',
            subtitle: 'in dollars (USD)'
        },
        width: 700,
        height: 500
    };

    var chart = new google.charts.Line(document.getElementById('chart'));

    chart.draw(data, options);
}
