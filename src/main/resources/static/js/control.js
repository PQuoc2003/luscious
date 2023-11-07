fetch("http://localhost:8080/api/time/student", {
    method : 'GET'
})
    .then(res => res.json())
    .then (data => {

        console.log(data)
    })
    .catch(() => console.log("ERROR"))