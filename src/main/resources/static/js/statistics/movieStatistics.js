function callData(date1, date2) {
    return new Promise(resolve => {
        let data = {
            "startDate": date1,
            "endDate": date2
        };
        let url = "/api/statistics-movie";
        axios.post(url, JSON.stringify(data),{
            headers:{
                'Content-Type': 'application/json'
            }
        }).then(function (response) {
            return resolve(response.data);
        }).catch(function (error) {
            alert(error)
        });
    })
}

async function callChart(datas) {
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
                    text: "Biểu đồ doanh thu phim",
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

    let myChart = new Chart(document.getElementById('StatisticsMovieChart'), config);
}

document.getElementById("btn-submit").addEventListener("click", async e => {
    e.preventDefault();
    let start = document.getElementById("startDate").value;
    let end = document.getElementById("endDate").value;
    if (new Date(start) < new Date(end)) {
        let data = await callData(start, end);
        document.getElementById("render_chart").innerHTML = `<canvas height="180" id="StatisticsMovieChart" width="400"></canvas>`
        await callChart(data);
    } else {
        document.getElementById("render_chart").innerHTML = `<div class="alert alert-danger" style="text-align: center;" role="alert">
        Ngày Không Hợp Lệ
      </div>`;
    }
})

// window.addEventListener("load", e => {
//     callChart();
// });