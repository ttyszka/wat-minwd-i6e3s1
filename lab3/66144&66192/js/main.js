var apiKey = '85a9397fa0e00f9607d0474e83a1c239';
var accentMap = {'ą': 'a','ć':'c','ę':'e','ł' : 'l', 'ń' : 'n', 'ó':'o', 'ś':'s', 'ż':'z', 'ź':'z',
    'Ą' : 'A', 'Ć' : 'C', 'Ę' : 'E', 'Ł' : 'L', 'Ó' : 'O', 'Ś' : 'S', 'Ż' : 'Z', 'Ź' : 'Z'};
var city = "";


window.onload = function () {
    loadDoc('Warsaw');
};

document.getElementById('city-input').onchange = function () {
    resetBox();
    city = getCity();
    city = cutCityName(city);
    city = accentFold(city);
    loadDoc(city);
};

function resetBox() {
    var boxElements = document.getElementsByTagName('data-elem');
    var icon = document.getElementsByClassName('icon');
    while (icon[0]) {
        icon[0].parentNode.removeChild(icon[0]);
    }
    while (boxElements[0]) {
        boxElements[0].parentNode.removeChild(boxElements[0]);
    }
}

function getCity() {
    return document.getElementById('city-input').value;
}


function cutCityName(city) {
    var pos = city.indexOf(",");
    if (pos !== -1) {
        return city.substr(0, pos);
    } else {
        return city;
    }

}

//changes accented letters
function accentFold(s){
    if (!s) {
        return '';
    }
    var ret = '';
    for(var i = 0; i<s.length;i++) {
        ret += accentMap[s.charAt(i)] || s.charAt(i);
    }
    return ret;
}

function setInputBorder(width, style, color ) {
    document.getElementById('city-input').style.border =  width + " " + style + " " + color;
}


//checks whether customization checkbox is checked
function ifNotChecked(name) {
    var param = document.getElementById(name);

    for (var i = 0; i < 4;i++) {
        if (!param.checked) {
            document.getElementsByClassName(name)[i].style.display = 'none';
        } else {
            document.getElementsByClassName(name)[i].style.display = 'block';
        }
    }
}


function loadDoc(city) {

    var dataFile = "http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&units=metric&APPID=" + apiKey;
    var xhttp = new XMLHttpRequest();


    xhttp.onreadystatechange = function () {

        if (this.readyState == 4 && this.status == 200) {
            var jsonObj = JSON.parse(xhttp.responseText);

            if (city == jsonObj.city.name) {

                //set input border in case first input was wrong
                setInputBorder("1px", "solid", "#ccc");

                for (var i = 0; i < 4; i++) {
                    //add hour and get substring so it shows only hour
                    //wrapping text node to easily remove it in resetBox()
                    var hour = jsonObj.list[i].dt_txt;
                    hour = hour.substr(11, 5);
                    var wrapHourElement = document.createElement('data-elem');
                    var hourNode = document.createTextNode(hour);
                    wrapHourElement.appendChild(hourNode);
                    document.getElementsByClassName("hour-disp")[i].appendChild(wrapHourElement);

                    //add temperature
                    var wrapTempElem = document.createElement('data-elem');
                    var temp = Math.round(jsonObj.list[i].main.temp);
                    var tempNode = document.createTextNode(temp + " " + String.fromCharCode(8451));
                    wrapTempElem.appendChild(tempNode);
                    document.getElementsByClassName("temp")[i].appendChild(wrapTempElem);

                    //add downfall
                    var wrapDwnfallElem = document.createElement('data-elem');

                    if (!('rain' in jsonObj.list[i]) || !('3h' in jsonObj.list[i].rain)) {
                        var noDwnfallNode = document.createTextNode("Brak");
                        wrapDwnfallElem.appendChild(noDwnfallNode);
                        document.getElementsByClassName("downfall")[i].appendChild(wrapDwnfallElem);
                    } else {
                        var dwnFall = jsonObj.list[i].rain["3h"].toFixed(2);
                        var dwnfallNode = document.createTextNode(dwnFall);
                        wrapDwnfallElem.appendChild(dwnfallNode);
                        document.getElementsByClassName("downfall")[i].appendChild(wrapDwnfallElem);
                    }

                    //add wind
                    var wrapWindElem = document.createElement('data-elem');
                    var wind = Math.round(jsonObj.list[i].wind.speed);
                    var windNode = document.createTextNode(wind + " m/s");
                    wrapWindElem.appendChild(windNode);
                    document.getElementsByClassName("wind")[i].appendChild(wrapWindElem);

                    //add clouds
                    var wrapCloudsElem = document.createElement('data-elem');
                    var cloudsNode = document.createTextNode(jsonObj.list[i].clouds.all + " %");
                    wrapCloudsElem.appendChild(cloudsNode);
                    document.getElementsByClassName("clouds")[i].appendChild(wrapCloudsElem);

                    //add pressure
                    var wrapPressureElem = document.createElement('data-elem');
                    var pressure = Math.round(jsonObj.list[i].main.pressure);
                    var pressureNode = document.createTextNode(pressure + " hPa");
                    wrapPressureElem.appendChild(pressureNode);
                    document.getElementsByClassName("pressure")[i].appendChild(wrapPressureElem);

                    //add icon
                    var iconElem = document.createElement('img');
                    iconElem.className = 'icon';
                    document.getElementsByClassName('gen-icon')[i].appendChild(iconElem);
                    var icon = jsonObj.list[i].weather[0].icon;
                    iconElem.src = "http://openweathermap.org/img/w/" + icon + ".png"

                    //add general status
                    var wrapStatusElem = document.createElement('data-elem');
                    var statusNode = document.createTextNode(jsonObj.list[i].weather[0].description);
                    wrapStatusElem.appendChild(statusNode);
                    document.getElementsByClassName("gen-status")[i].appendChild(wrapStatusElem);
                }
            }
        } else if(this.status == 400) {
            var cityInput = document.getElementById('city-input');
            //First clear input. The placeholder won't change without doing it.
            cityInput.value = "";
            cityInput.placeholder = "Brak danych dla podanego miasta";
            setInputBorder("2px", "solid", "red");
        }
    };
    xhttp.open("GET", dataFile, true);
    xhttp.send();
}

// autocomplete using google api
function initializeAutocomplete() {
    var input = document.getElementById('city-input');
    new google.maps.places.Autocomplete(input, {types: ['(cities)']});
}

google.maps.event.addDomListener(window, 'load', initializeAutocomplete);

var tempCheckbox = document.getElementById('temp');
var dwnFallCheckbox = document.getElementById('downfall');
var windCheckbox = document.getElementById('wind');
var cloudsCheckbox = document.getElementById('clouds');
var pressureCheckbox = document.getElementById('pressure');


tempCheckbox.onchange = function () {
    ifNotChecked('temp');
};

dwnFallCheckbox.onchange = function () {
    ifNotChecked('downfall');
};

windCheckbox.onchange = function () {
    ifNotChecked('wind');
};

cloudsCheckbox.onchange = function () {
    ifNotChecked('clouds');
};

pressureCheckbox.onchange = function () {
    ifNotChecked('pressure');
};


//prevent customization dropdown from hiding when clicked
document.getElementsByClassName('dropdown-menu')[0].onclick = function () {
    event.stopPropagation();
};