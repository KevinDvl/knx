//import { XMLHttpRequest } from "xmlhttprequest";

const sendHttpRequest = (method, url, instruction) => {
  params = 'instruction='+instruction
  const promise = new Promise((resolve, reject) => {
    const xhr = new XMLHttpRequest();
    xhr.open(method, url);
    xhr.responseType = 'json';
    if (instruction) {
      xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    }
    xhr.onload = () => {
      if (xhr.status >= 400) {
        reject(xhr.response);
      } else {
        resolve(xhr.response);
      }
    };
    xhr.onerror = () => {
      reject('Something went wrong!');
    };
    xhr.send(params);
  });
  return promise;
};

const sendData = (instruction) => {
  sendHttpRequest('POST', 'http://localhost:8081/knx/knx', instruction)
    .then(responseData => {
      console.log(responseData);
    })
    .catch(err => {
      console.log(err);
    });
};

var start = false;
var i = 0;

//console.log("Trying to connect to the websocket server");

//var ws = new WebSocketClient('ws', '127.0.0.1', 8081, '/KNX_Server/endpoint');


//ws.connect();
//ws.send("This is a test message");
var value;
var value2;
var value3;

function changeImage(id) { 
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
        value = id;
        sendData("allume"+id);
    }
    else 
    {
        Image_Id.src = "lightbulb.png";
        value = id;
        value2 = "off";
        sendData("eteins"+id);
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
}

function stopChenillard(){
    start = false;
    sendData("stopChenillard");
    document.getElementById("buttonStopChenillard").disabled = true;
}

function startChenillard(numChenillard){
	if(numChenillard == 1) {
		sendData("startChenillard1");		
	}
	else {
		sendData("startChenillard2");
	}
	document.getElementById("buttonStopChenillard").disabled = false;
    /*start = true;

    const list = ["L1","L2","L3","L4"];

    for(let j = 0;j<list.length;j++){
      eteindreLED(list[j]);
      //xhr.send("LED=all&state=off");
    }


    var temps;
    var e = document.getElementById("speed-select");
    vitesse = e.value;
    if(vitesse==''){vitesse = 1;}*/
}



     