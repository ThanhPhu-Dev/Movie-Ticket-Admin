function lineTurnoverMonth(){
    const labels = [
        'tháng 1',
        'tháng 2',
        'tháng 3',
        'tháng 4',
        'tháng 5',
        'tháng 6',
        'tháng 7',
        'tháng 8',
        'tháng 9',
        'tháng 10',
        'tháng 11',
        'tháng 12',
    ];
    const data = {
        labels: labels,
        datasets: [{
            label: 'Doanh Thu(VNĐ)',
            backgroundColor: 'rgb(255, 99, 132)',
            borderColor: 'rgb(255, 99, 132)',
            data: [0, 10, 5, 2, 20, 30, 45, 50, 45, 15, 70, 60],
        }]
    };

    const config = {
        type: 'line',
        data,
        options: {
            plugins: {
                legend: {
                    display: false,
                },
                title: {
                    display: true,
                    text: "Biểu đồ doanh thu Theo Tháng",
                }
            },
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            },
            responsive: true,
            locale: 'vi'
        }
    };
    let myChart = new Chart(document.getElementById('StatisticsTurnoverMonthChart'), config);
}

function PieChartQuarter(){

    const data = {
        labels: [
            'Red',
            'Blue',
            'Yellow'
        ],
        datasets: [{
            label: 'My First Dataset',
            data: [300, 50, 100],
            backgroundColor: [
                'rgb(255, 99, 132)',
                'rgb(54, 162, 235)',
                'rgb(255, 205, 86)'
            ],
            hoverOffset: 4
        }]

    };
    const config = {
        type: 'doughnut',
        data: data,
        options: {
            plugins: {
                title: {
                    display: true,
                    text: "Biểu đồ doanh thu Theo Quý",
                }
            },
            responsive: true,
            locale: 'vi'
        }
    };

    let myChart = new Chart(document.getElementById('StatisticsTurnoverQuarterChart'), config);
}

function BarSatisticsCinema(){
    const data = {
        labels: ["cinema 1", "cinema 2"],
        datasets: [{
            label: 'Doanh Thu VNĐ',
            data: [65, 59, 80, 81, 56, 55, 40],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(255, 159, 64, 0.2)',
                'rgba(255, 205, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(201, 203, 207, 0.2)'
            ],
            borderColor: [
                'rgb(255, 99, 132)',
                'rgb(255, 159, 64)',
                'rgb(255, 205, 86)',
                'rgb(75, 192, 192)',
                'rgb(54, 162, 235)',
                'rgb(153, 102, 255)',
                'rgb(201, 203, 207)'
            ],
            borderWidth: 1
        }]
    };

    const config = {
        type: 'bar',
        data: data,
        options: {
            plugins: {
                legend: {
                    display: false,
                },
                title: {
                    display: true,
                    text: "Biểu đồ doanh thu Rạp Phim",
                }
            },
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            },
            responsive: true,
            locale: 'vi'
        }
    };

    let myChart = new Chart(document.getElementById('StatisticsTurnoverCinemaChart'), config);
}

window.addEventListener("load", e => {
    lineTurnoverMonth();
    PieChartQuarter();
    BarSatisticsCinema();
});