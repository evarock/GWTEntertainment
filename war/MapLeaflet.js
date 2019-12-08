const mymap = L.map("osm").setView([51.660781, 39.200269], 12);
L.tileLayer('http://{s}.tile.osm.org/{z}/{x}/{y}.png', {
           attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors'
}).addTo(mymap);