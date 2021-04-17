$(document).ready(function (){

    let data = {
        labels: [1,2,3,4,5,6,7],
        datasets: []
    };

    data.datasets.push({
        data: [12, 19, 3, 5, 2, 3]
    });

    let config = {
        type: 'line',
        data: data,
        options: {

        }
    };



    const ctx = document.getElementById('myChart').getContext('2d');
    let myChart = new Chart(ctx, config);
});