<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<script src="http://maps.google.com/maps/api/js?sensor=true"></script>
	<script
			src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
<h2>Tramwaje Online</h2>

<div>
	<input type="text" placeholder="Wpisz linie" id="w-input-search" value="">
	<span>
			<button id="w-button-search" type="button">Znajdz swoj tramwaj</button>
		</span>
</div>

<script>
	$(document).ready(function () {

		$("#w-button-search").click(function () {

			$.getJSON("${pageContext.request.contextPath}/getLocation",
					{
						ipAddress: $('#w-input-search').val()
					},
					function (data) {
						showMap(data);
					})
					.done(function () {

					})
					.fail(function () {
					})
					.complete(function () {
					});

		});

		var map;

		function showMap(data) {

			var mapOptions = {
				zoom: 10,
				center: new google.maps.LatLng(52.237049, 21.017532),
				mapTypeId: google.maps.MapTypeId.ROADMAP
			};

			var mapDiv = document.getElementById("map");
			map = new google.maps.Map(mapDiv, mapOptions);

			var markers = data;

			addMarkers(map, markers);

		}

		function addMarkers(map, markers) {
			var bounds = new google.maps.LatLngBounds();

			// Loop through our array of markers & place each one on the map
			for (i = 0; i < markers.length; i++) {
				var position = new google.maps.LatLng(markers[i][0], markers[i][1]);
				bounds.extend(position);
				marker = new google.maps.Marker({
					position: position,
					map: map
				});

				// Automatically center the map fitting all markers on the screen
				map.fitBounds(bounds);
			}

			// Override our map zoom level once our fitBounds function runs (Make sure it only runs once)
			var boundsListener = google.maps.event.addListener((map), 'bounds_changed', function (event) {
				google.maps.event.removeListener(boundsListener);
			});
		}

	});
</script>
<br/>
<br/>
<div style="width:1200px;height:800px" id="map"></div>

</body>
</html>
