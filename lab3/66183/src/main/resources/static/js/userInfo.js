let userInfoURL = 'http://localhost:8080/info/user';
let latestHashTagsURL = 'http://localhost:8080/info/hashtag_cloud';


//get general information about user
let getOverview = async function () {


    let response = await fetch(userInfoURL);

    if (!response.ok) {
        return;
    }

    let json = await response.json();
    let contentDiv = document.getElementById('info_content');

    contentDiv.innerHTML = '';
    let infoTable = styleOverview(contentDiv);

    if (json['profileImageURL'] != null)
        document.images[0].setAttribute('src', json['profileImageURL']);

    for (let key in json) {
        if (json.hasOwnProperty(key)) {
            if (key === 'profileImageURL') continue;
            let row = infoTable.insertRow();
            row.style.padding = '1%';
            row.insertCell(0).innerHTML = key;
            row.insertCell(1).innerHTML = json[key];
        }

    }

    infoTable.tHead.style.background = 'white';
    infoTable.style.borderSpacing = '0';
    infoTable.style.border = '1px';
    infoTable.style.borderRadius = '2px'


};

//dynamically style overwiew
function styleOverview(contentDiv) {

    let profilePhoto = document.createElement('div');
    profilePhoto.setAttribute('id', 'profile');
    profilePhoto.style.width = '300px';
    profilePhoto.style.height = '300px';
    profilePhoto.style.borderRadius = '50%';
    profilePhoto.style.color = 'grey';
    profilePhoto.style.margin = '1% auto';
    contentDiv.appendChild(profilePhoto);

    let profileImg = document.createElement('img');
    profileImg.style.width = '300px';
    profileImg.style.height = '300px';
    profileImg.style.borderRadius = '50%';
    profileImg.style.margin = '1% auto';
    profilePhoto.appendChild(profileImg);

    let infoTable = document.createElement('table');

    let headerRow = infoTable.createTHead().insertRow(0);
    headerRow.insertCell(0).innerHTML = 'Property';
    headerRow.insertCell(1).innerHTML = 'Value';

    infoTable.style.width = '30%';
    infoTable.style.height = '30%';
    infoTable.style.margin = '1% auto';
    infoTable.style.color = 'black';
    infoTable.style.textAlign = 'center';
    infoTable.style.boxShadow = '5px 5px 10px black';

    contentDiv.appendChild(infoTable);


    return infoTable;
}

async function getHashTagsCloud(tweets = 20) {

    let url = new URL(latestHashTagsURL),
        params = {tweets_number: tweets};
    Object.keys(params).forEach(key => url.searchParams.append(key, params[key]));

    let response = await fetch(url.toString());

    if (!response.ok) {
        return;
    }

    let json = await response.json();
    console.log(json);
    let hashtags = document.getElementById('info_content');

    hashtags.innerHTML = '';

    let hashesDiv = styleHashtags(hashtags);

    console.log(json.length);
    for (let mainArray of json) {
        for (let tweetHashArrayIt = 0; tweetHashArrayIt < mainArray.length; tweetHashArrayIt++) {

            let tagDiv = document.createElement('div');
            tagDiv.setAttribute('class', 'tag');
            tagDiv.innerHTML = '#' + mainArray[tweetHashArrayIt].text;
            hashesDiv.appendChild(tagDiv);
        }
    }

    let spanElements = document.querySelectorAll('.tag');

    for (let divElement of spanElements) {
        divElement.style.border = '1px black';
        divElement.style.borderRadius = '2px';
        divElement.style.boxShadow = '2px 2px black 10px';
        divElement.style.margin = '10px 10px';
        divElement.style.display = 'inline-block';
    }

    addInputEvent();


}

function styleHashtags(hashTagsDiv) {

    let form = '<h1>Latest hashtags</h1><form><input id="tweets_number" type="number" min="0" value="20"></form><div id="hashes"></div>';


    hashTagsDiv.innerHTML = '';
    hashTagsDiv.innerHTML = form;
    let h1Element = hashTagsDiv.querySelector('h1');
    h1Element.style.textAlign = 'center';

    let hashes = document.getElementById('hashes');

    hashes.style.margin = '2% auto';

    return hashes;

}

function addInputEvent() {
    let input = document.getElementById('tweets_number');
    input.addEventListener('change', function () {
        getHashTagsCloud(input.value)
            .then(response => console.log("Hash cloud fetching done"));
    }, false);
}


function addListeners() {


    let buttons = document.body.querySelectorAll(".button");

    console.log(buttons.length);
    buttons[0].addEventListener('click', getOverview);
    buttons[1].addEventListener('click', getHashTagsCloud);


}

window.addEventListener("load", getOverview);
window.addEventListener('load', addListeners);




