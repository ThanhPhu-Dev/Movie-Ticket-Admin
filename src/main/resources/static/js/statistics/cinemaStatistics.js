function callData() {
    return new Promise(resolve => {
        let url = "/api/statistics-cinema";
        axios.get(url).then(function (response) {
            return resolve(response.data);
        }).catch(function (error) {
            alert(error)
        });
    })
}

async function callChart() {
    let datas =  await callData();
    console.log(datas);
    const config =  {
        type: 'bar',
        data: {
            labels: datas.map(m => m.name),
            datasets: [{
                data: datas.map(m => m.total),
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            plugins: {
                legend: {
                    display: false,
                },
                title: {
                    display: true,
                    text: "Biểu đồ doanh thu rap",
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

    let myChart = new Chart(document.getElementById('myChart'), config);
}
window.addEventListener("load", e => {
    callChart();
});