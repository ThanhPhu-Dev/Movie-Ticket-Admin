function getDataStatisticsCinema() {
    return new Promise(resolve => {
        let url = '/api/statistics-cinema';
        axios.get(url).then(function (response) {
            return resolve(response.data)
        }).catch(function (error) {
            return resolve([])
        });
    })
}

function getDataStatisticsBookingByMonth() {
    return new Promise(resolve => {
        let url = '/api/statistics-booking-month';
        axios.get(url).then(function (response) {
            return resolve(response.data)
        }).catch(function (error) {
            return resolve([])
            alert(error);
        });
    })
}

function getDataStatisticsBookingByQuarter() {
    return new Promise(resolve => {
        let url = '/api/statistics-booking-quarter';
        axios.get(url).then(function (response) {
            return resolve(response.data)
        }).catch(function (error) {
            return resolve([])
            alert(error);
        });
    })
}

async function lineTurnoverMonth() {
    let datas = await getDataStatisticsBookingByMonth();
    const labels = datas.map(m => 'Tháng ' + m.name);
    const data = {
        labels: labels,
        datasets: [{
            label: 'Doanh Thu(VNĐ)',
            backgroundColor: 'rgb(255, 99, 132)',
            borderColor: 'rgb(255, 99, 132)',
            data: datas.map(m => m.total),
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

async function PieChartQuarter() {
    let datas = await getDataStatisticsBookingByQuarter();
    const data = {
        labels: datas.map(m => 'Quý '+m.name + ' VNĐ'),
        datasets: [{
            label: 'My First Dataset',
            data: datas.map(m => m.total),
            backgroundColor: [
                'rgb(255, 99, 132)',
                'rgb(54, 162, 235)',
                'rgb(255, 205, 86)',
                'rgb(100, 232, 90)'
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

async function BarSatisticsCinema() {
    let datas = await getDataStatisticsCinema();
    const data = {
        labels: datas.map(m => m.name),
        datasets: [{
            label: 'Doanh Thu VNĐ',
            data: datas.map(m => m.total),
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

window.addEventListener("load", async e => {
    await lineTurnoverMonth();
    await PieChartQuarter();
    await BarSatisticsCinema();
});