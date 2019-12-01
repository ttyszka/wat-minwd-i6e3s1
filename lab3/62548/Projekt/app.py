from flask import Flask, request, render_template
from flask_googlemaps import GoogleMaps
from flask_googlemaps import Map, icons
import requests as requests
import json

app = Flask(__name__)

#Write your own API key
app.config['GOOGLEMAPS_KEY'] = "xxx"

GoogleMaps(app)

@app.route("/", methods=['GET', 'POST']) 
def index():
    return render_template('index.html')

@app.route("/map", methods=['GET', 'POST'])
def showMap():
    inputText = request.form.get('inputField')
    dataZTM=[]
    if inputText is  None: 
        inputText="Do not exist"
    elif inputText == "buses":
        inputTextHelp="0"
        typeLine="1"
        dataZTM = loadData(inputTextHelp, typeLine)
    elif inputText == "trams":
        inputTextHelp="0"
        typeLine="2"
        dataZTM = loadData(inputTextHelp, typeLine)
    elif len(inputText) == 2:
        typeLine="2"
        dataZTM = loadData(inputText, typeLine)
    elif len(inputText) == 3:
        typeLine="1"
        dataZTM =  loadData(inputText, typeLine) 
    else:
        inputText="Do not exist"

    mymap = Map(
        identifier="mymap",
        lat=52.231597,
        lng=21.006645
    )
    if dataZTM is None or len(dataZTM) == 0:
        inputText="Do not exist"
        return render_template('map.html', mymap=mymap,line_number=inputText)

    for row in dataZTM:
        lat=float(row['Lat'])
        lon=float(row['Lon'])
        #infobox="Line number: " + row['Lines']+"<br> Brigade: " + row['Brigade'] + "<br> Time: "+row['Time']
        mymap.add_marker(lat, lon)
   
    return render_template('map.html', mymap=mymap, line_number=inputText)

def loadData(inputText, typeLine):
    startPage = "https://api.um.warszawa.pl/api/action/busestrams_get/?resource_id=f2e5503e-927d-4ad3-9500-4ab9e55deb59&apikey=21d2bfef-45d1-455c-b014-3d32cad35dfe&type="
    startPage = startPage + typeLine
    if inputText != "0":
        line_number = inputText
        startPage = startPage + "&line=" + inputText
    mainData = requests.get(startPage)

    parsedData = mainData.json()['result']
    print(parsedData)
    if parsedData is None or len(parsedData) == 0:
        return None

    for row in parsedData:
        print(row)
        print(row['Lat'])
        print(row['Lon'])

    return parsedData

if __name__ == "__main__":
    app.run(debug='True')