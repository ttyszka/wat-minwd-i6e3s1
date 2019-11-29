let url = 'http://localhost:8080/info/user';

let getOverview = async function () {

    let response = await fetch(url);

    if (response.ok) {
        let json = await response.json();
        console.log(json);
    }
};


window.addEventListener("load", getOverview);


