let userInfoURL = 'http://localhost:8080/info/user';
let latestHashTagsURL = 'http://localhost:8080/info/hashtag_cloud?';
let firstLastTweetURL = 'http://localhost:8080/info/tweet';
let signOutURL = 'http://localhost:8080/auth/signout';


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

async function getHashTagsCloud(e, tweets = 20) {

    let params = {
        "tweets_number": e.target.value
    };

    console.log(tweets);
    let query = Object.keys(params)
        .map(k => encodeURIComponent(k) + '=' + encodeURIComponent(params[k]))
        .join('&');

    let url = latestHashTagsURL + query;
    console.log(url);
    let response = await fetch(url);

    if (!response.ok) {
        console.log('Not ok');
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
            tagDiv.style.border = '1px black';
            tagDiv.style.borderRadius = '10px';
            tagDiv.style.boxShadow = '2px 2px black 10px';
            tagDiv.style.margin = '10px 10px';
            tagDiv.style.display = 'inline-block';
            tagDiv.innerHTML = '#' + mainArray[tweetHashArrayIt].text;
            tagDiv.style.backgroundColor = 'white';
            tagDiv.style.color = 'black';
            tagDiv.style.padding = '10px';
            hashesDiv.appendChild(tagDiv);
        }
    }


    addInputEvent();


}

var currentInputValue = 20;
function styleHashtags(hashTagsDiv) {

    let form = '<h1>Latest hashtags</h1><form><input id="tweets_number" type="number" min="0" max="180"></form><div id="hashes"></div>';


    hashTagsDiv.innerHTML = '';
    hashTagsDiv.innerHTML = form;
    let h1Element = hashTagsDiv.querySelector('h1');
    h1Element.style.textAlign = 'center';

    let hashes = document.getElementById('hashes');

    hashes.style.margin = '2% 10px';

    return hashes;

}

function addInputEvent() {
    let input = document.getElementById('tweets_number');
    input.setAttribute('value', currentInputValue);
    input.addEventListener('onblur', getHashTagsCloud, false);
    input.addEventListener('onchange', function () {
        currentInputValue--;
        getHashTagsCloud();
    }, false);


}

function addListeners() {


    let buttons = document.body.querySelectorAll(".button");

    console.log(buttons.length);
    buttons[0].addEventListener('click', getOverview);
    buttons[1].addEventListener('click', getHashTagsCloud, false);
    buttons[2].addEventListener('click', getFirstLastTweets, false);
    buttons[3].addEventListener('click', signOut, false);


}


let getFirstLastTweets = async function () {


    let response = await fetch(firstLastTweetURL);

    if (!response.ok) {
        return;
    }

    let json = await response.json();
    let contentDiv = document.getElementById('info_content');

    contentDiv.innerHTML = '';
    styleFirstLastTweet(contentDiv);
    let createdAt = document.getElementsByClassName('created_at');
    let tweetTexts = document.getElementsByClassName('twitter_text');
    createdAt[0].innerHTML = new Date(json['firstTweet']['createdAt']).toLocaleDateString();
    createdAt[1].innerHTML = new Date(json['lastTweet']['createdAt']).toLocaleDateString();

    tweetTexts[0].innerHTML = json['firstTweet']['text'];
    tweetTexts[1].innerHTML = json['lastTweet']['text'];

};

//dynamically style overwiew
function styleFirstLastTweet(contentDiv) {

    let twoColumn = '<div class="columnTweet"><h1>Latest tweet</h1><p class="created_at"></p><p class="twitter_text"></p></div><div class="columnTweet"><h1>First tweet</h1><p class="created_at"></p><p class="twitter_text"></p></div>';

    let columns = document.getElementsByClassName("columnTweet");

    contentDiv.innerHTML = twoColumn;

    for (let column of columns) {
        column.style.backgroundColor = 'white';
        column.style.margin = '10px 10px';
        column.style.border = '1px black';
        column.style.borderRadius = '10px';
        column.style.boxShadow = '5px 5px black 5px';
        column.style.padding = '2%';
        column.style.display = 'flex';
        column.style.flexDirection = 'column';
        column.style.flexBasis = '100%';
        column.style.color = 'black'

    }


}

async function signOut() {
    let response = await fetch(signOutURL);

    if (!response.ok) {
        return;
    }

    document.location.href = "/";
}

window.addEventListener("load", getOverview);
window.addEventListener('load', addListeners);




