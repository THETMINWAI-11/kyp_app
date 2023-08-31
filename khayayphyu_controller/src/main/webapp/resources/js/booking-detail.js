
	document.addEventListener("DOMContentLoaded", () => {
		const markerPoints = [];
		let cost = 0;
		$("#cost-history [name=history]").each((index, element) => {
			const latitude = parseFloat($("[name=latitude]", element).text());
			const longitude = parseFloat($("[name=longitude]", element).text());
			const temp  = cost + parseFloat($("[name=cost]", element).text());
			const time = $("[name=time]", element).text();
			const distance = $("[name=distance]", element).text();
			const info = $("[name=info]", element).text();
			cost = temp;
			markerPoints.push({latitude , longitude, time, cost : temp, distance, index, info});
		})
		const map = createMap("booking-trip-map", createLntLng(markerPoints[0]));
		markerPoints.forEach(markerPoint => createMarker(markerPoint, map));
		var flightPath = new google.maps.Polyline({
		    path: createPath(markerPoints),
		    geodesic: true,
		    strokeColor: '#FF0000',
		    strokeOpacity: 1.0,
		    strokeWeight: 2
		});
		flightPath.setMap(map); 
	})
	
	function addMapInfoToMarker(content, marker, map) {
		const info =  new google.maps.InfoWindow({content})
		marker.addListener("click", () => info.open(map, marker) )
	}
	
	function createLntLng(markerPoint) {
		return new google.maps.LatLng(markerPoint.latitude, markerPoint.longitude);
	}
	
	function createMap(selector, centerLatLng) {
		return new google.maps.Map(document.getElementById(selector), {
            zoom: 14,
            center: centerLatLng,
            mapTypeId: google.maps.MapTypeId.ROADMAP
     });
	}
	
	function createMarker(markerPoint, map) {
		const marker =  new google.maps.Marker({
            position: createLntLng(markerPoint),
            map: map,
            label: (markerPoint.index+1) + "",
            title: markerPoint.info 
          });
		const template = document.getElementById("booking-marker-info-template").content.cloneNode(true);
		template.querySelector("td[name=time]").textContent = markerPoint.time;
		template.querySelector("td[name=distance]").textContent = markerPoint.distance;
		template.querySelector("td[name=cost]").textContent = markerPoint.cost;
		template.querySelector("td[name=location]").textContent = `${markerPoint.latitude}, ${markerPoint.longitude}`;
		//template.querySelector("td[name=longitude]").textContent = markerPoint.longitude;
		addMapInfoToMarker(template, marker, map);
		return marker;
	}
	
	function createPath(markerPoints) {
		const list = []
		
		markerPoints.forEach(points => {
			list.push(createLntLng(points));
		});
		return list;
	}