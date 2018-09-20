function chart() {
    $.ajax({
        type: "GET",
        url: "/operation/monthsProfit/" + $('[name="year"]').val(),
        dataType: "json",
        async: false,
        success: function (jsonResult) {
            if ("200" == jsonResult.code) {
                var result = jsonResult.result;

                var ctx1 = document.getElementById("chart_1").getContext("2d");
                var data1 = {
                    labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                    datasets: [
                        {
                            label: "期权",
                            backgroundColor: "rgba(60,184,120,0.4)",
                            borderColor: "rgba(60,184,120,0.4)",
                            pointBorderColor: "rgb(60,184,120)",
                            pointHighlightStroke: "rgba(60,184,120,1)",
                            // data: [0, 59, 80, 58, 20, 55, 40, 80, 58, 20, 55, 20000000],
                            data: [result[0]['01'], result[0]['02'], result[0]['03'], result[0]['04'], result[0]['05'], result[0]['06'], result[0]['07'], result[0]['08'], result[0]['09'], result[0]['10'], result[0]['11'], result[0]['12']]
                        },
                        {
                            label: "配资",
                            backgroundColor: "rgba(252,176,59,0.4)",
                            borderColor: "rgba(252,176,59,0.4)",
                            pointBorderColor: "rgb(252,176,59)",
                            pointBackgroundColor: "rgba(252,176,59,0.4)",
                            // data: [28, 48, 40, 19, 86, 27, 90, 80, 20000000, 20, 55, 40],
                            data: [result[1]['01'], result[1]['02'], result[1]['03'], result[1]['04'], result[1]['05'], result[1]['06'], result[1]['07'], result[1]['08'], result[1]['09'], result[1]['10'], result[1]['11'], result[1]['12']],
                        }

                    ]
                };

                var areaChart = new Chart(ctx1, {
                    type: "line",
                    data: data1,

                    options: {
                        tooltips: {
                            mode: "label"
                        },
                        elements: {
                            point: {
                                hitRadius: 90
                            }
                        },

                        scales: {
                            yAxes: [{
                                stacked: true,
                                gridLines: {
                                    color: "#eee",
                                },
                                ticks: {
                                    fontFamily: "Varela Round",
                                    fontColor: "#2f2c2c"
                                }
                            }],
                            xAxes: [{
                                stacked: true,
                                gridLines: {
                                    color: "#eee",
                                },
                                ticks: {
                                    fontFamily: "Varela Round",
                                    fontColor: "#2f2c2c"
                                }
                            }]
                        },
                        animation: {
                            duration: 3000
                        },
                        responsive: true,
                        maintainAspectRatio: false,
                        legend: {
                            display: false,
                        },
                        tooltips: {
                            backgroundColor: 'rgba(47,44,44,.9)',
                            cornerRadius: 0,
                            footerFontFamily: "'Varela Round'"
                        }

                    }
                });
            } else {
                swal(jsonResult.message)
            }
        }
    })
}

chart();


$('[name="year"]').change(function () {
    // $(".panel-wrapper").html('');
    chart();
})