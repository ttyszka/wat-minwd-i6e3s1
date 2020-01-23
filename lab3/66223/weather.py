import requests
from flask import Flask, render_template, request
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)




@app.route('/', methods = ['GET', 'POST'])
def index():
    cities = []
    if request.method == 'POST':
        ncity = request.form.get('city1')
        cities.append(ncity)
        ncity = request.form.get('city2')
        cities.append(ncity)
        ncity = request.form.get('city3')
        cities.append(ncity)
        ncity = request.form.get('city4')
        cities.append(ncity)
    
    url = 'http://api.openweathermap.org/data/2.5/weather?q={}&units=metric&APPID=4073e9391f1178f239914773b49066d6'
    
    list_city =[]
    
    
    for x in cities:
        r=requests.get(url.format(x)).json()
        weather = {
            'city' : x,
            'temperature' : r['main']['temp'],
            'description' : r['weather'][0]['description'],
            'icon' : r['weather'][0]['icon'],
        }
        list_city.append(weather)
        print(list_city)
        print(cities)
    return render_template('index.html', list_city=list_city)




if __name__ == '__main__':
    app.run(debug=True)
    