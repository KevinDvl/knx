import { XMLHttpRequest } from "xmlhttprequest";

var start = false;
var i = 0;

console.log("Trying to connect to the websocket server");

const button = document.getElementById("buttonChenillard");

var ws = new WebSocketClient('ws', '127.0.0.1', 8081, '/KNX_Server/endpoint');


ws.connect();
//ws.send("This is a test message");
var value;
var value2;
var value3;

/*var xhr = new XMLHttpRequest();
xhr.open("POST", '127.0.0.1:8081', true);
xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
xhr.onreadystatechange = function() {
  if(xhr.readyState === XMLHttpRequest.DONE && xhr.status === 201) {
    console.log(JSON.parse(xhr.responseText));
  }
  else if (xhr.readyState === XMLHttpRequest.DONE && xhr.status !== 201){
    console.log("Error");
  }
}*/

function changeImage(id) { 
    const xhr = new XMLHttpRequest();
    xhr.open("POST", "knx");
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    i++;
    var Image_Id = document.getElementById(id);
    const extractFilename = (path) => {
        const pathArray = path.split("/");
        const lastIndex = pathArray.length - 1;
        return pathArray[lastIndex];
    };
    var fileName = extractFilename(Image_Id.src);
    if (fileName == "lightbulb.png") 
    {
        Image_Id.src = "lightbulb2.png";
        console.log("led on : "+id)
        ws.send("led-on : " + id);
        value = id;
        xhr.send("LED="+id+"&state=on");
    }
    else 
    {
        Image_Id.src = "lightbulb.png";
        //ws.send("led-off : " + id);
        value = id;
        value2 = "off";
        xhr.send("LED="+id+"&state=off");
    }   
}
    
function eteindreLED(id){
  var Image_Id = document.getElementById(id);
    const extractFilename2 = (path) => {
      const pathArray = path.split("/");
      const lastIndex = pathArray.length - 1;
      return pathArray[lastIndex];
    };
    Image_Id.src = "lightbulb.png";
    value = id;
    //xhr.send("LED="+id+"&state=off");
}

function allumerLED(id){
  var Image_Id = document.getElementById(id);
    const extractFilename2 = (path) => {
      const pathArray = path.split("/");
      const lastIndex = pathArray.length - 1;
      return pathArray[lastIndex];
    };
    Image_Id.src = "lightbulb2.png";
    value = id;
    //xhr.send("LED="+id+"&state=on");
}

function stopChenillard(){
    start = false;
    //xhr.send("LED=Chenillaird&state=off");
}

function startChenillard(vitesse){
    start = true;

    const list = ["L1","L2","L3","L4"];

    for(let j = 0;j<list.length;j++){
      eteindreLED(list[j]);
      //xhr.send("LED=all&state=off");
    }


    var temps;
    var e = document.getElementById("speed-select");
    vitesse = e.value;
    if(vitesse==''){vitesse = 1;}
    //xhr.send("LED=Chenillaird&state=on&speed="+vitesse);
   
    //console.log(vitesse);
    //i=100;
    //ws.send("Chenillard Requested at speed : "+vitesse);

    

    //ws.send('Chenillard started');
    //temps = 1000/vitesse;
    //  chenillard(temps);
}

function chenillard(temps){
  const list = ["L1","L2","L3","L4"];
  //ws.send('Chenillard started');

  /**while(i<100){
    setTimeout(function() {changeImage(list[i%4]);},i*temps);
    if(i%4==3){
      setTimeout(function() {changeImage(list[1]);},i*temps);
    }

    if(i%4==2){
      setTimeout(function() {changeImage(list[0]);},i*temps);
    }

    if(i>3 && i%4==0){
      setTimeout(function() {changeImage(list[2]);},i*temps);
    }

    if(i>3 && i%4==1){
      setTimeout(function() {changeImage(list[3]);},i*temps);
    }
  }**/
}



     