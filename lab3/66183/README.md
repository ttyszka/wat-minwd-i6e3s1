# wat-minwd-i6e3s1

<Header>Autorzy:<br>Adam Wozniakowski 66242<br>Marek Roskowicz 66183</Header>
<h1>Projekt z laboratorium numer 3 z przedmiotu MINWD</h1>
<p>Zadanie polegalo na pobraniu informacji z Twitter API i zaprezntowanie wynikow w przyjazny, przyjemny dla oka sposob. 
Prezentacja oraz logowanie do konta Twitter-owego odbywa sie poprzez aplikacje webowa. </p>

![Sign In page](img/sign_page.PNG)

<p>Po udanym zalogowaniu sie do apliacji uzytkownikowi przedstawione sa dane:</p>
<p>Aplikacja pozwala zallogowac sie uzytkownikowi tylko i wylacznie z poziomu systemu bedacego localhostem</p>
<ul>
<li>uzyskanie informacji o użytkowniku</li>
<li>pierwszy i ostatni tweet</li>
<li>chmura tagów z ostatnich X tweetów</li>
</ul>
<p>Tokeny dostepowe dla sesji sa przechowywane przez dlugosc trwania sesji czyli do zamkniecia przegladarki. 
Aplkacaj umozliwia rowniez wylogowanie sie.
</p>
<h2>Uruchamie aplikacji</h2>
<p>Aby uruchomic aplikacje, w folderze zawierajacym projekt oraz plik pom.xml nalezy wpisac polecenie<br><br><b>mvn clean -D skipTests package spring-boot:run</b>
<br><br>
Aplikacja zostala stworzona z uzyciem:
</p>
<ul>
<li>TwitterAPI</li>
<li>Spring-boot</li>
<li>HTML, CSS, JavaScript</li>
<li>Biblioteki Twitter4j</li>
</ul>
