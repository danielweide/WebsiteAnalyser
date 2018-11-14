const express = require('express');
const router = express.Router();
const jwt = require('jsonwebtoken');
const XMLHttpRequest = require('xhr2');


//--------------------------Functions------------------------------------------


function changeToHTTP(){
  let urlHTTP = "http://" + this.url;
  return urlHTTP;
}

function changeToHTTPS(){
  let urlHTTPS = "https://" + this.url;
  return urlHTTPS;
}

function getStatus(){

}

function sendResponse(req, res){
  // res.set('Con', 'text/plain');
  // const Http = new XMLHttpRequest();
  // Http.open("GET", req.body.url, true);
  // Http.send();
  // Http.onload = function () {
  //   console.log('DONE', Http.status); // readyState will be 4
  //   res.status(200).send({"status": Http.status});
  // };

  var score = 0;
  // var code = await this.getStatus(this.changeToHTTP());
  const Http = new XMLHttpRequest();
  const Https = new XMLHttpRequest();
  Http.open("GET", changeToHTTP(req.body.url), true);
  Http.send();
  Http.onload = function () {
    console.log('HTTP status', Http.status); // readyState will be 4
    if(Http.status >= 300 && Http.status < 400){
      score+=1; //redirected
    } else {
      score+=0;
    }
    Https.open("GET", changeToHTTPS(req.body.url), true);
    Https.send();
    Https.onload = function () {
      console.log('HTTPS status', Http.status); // readyState will be 4
      if(Https.status >= 300 && Https.status < 400){
        score+=0; //redirected
      } else {
        score+=1; //redirected
      }
      if(score == 2){
        // System.out.println("Your site rating: A");
        res.status(200).send({"mark": "Your site rating: A"});
        // this.mark = "Your site rating: A";
      } else if(score == 1) {
        // System.out.println("Your site rating: D");
        res.status(200).send({"mark": "Your site rating: D"});

        // this.mark = "Your site rating: D";
      } else if(score == 0) {
        // System.out.println("Your site rating: F");
        res.status(200).send({"mark": "Your site rating: F"});
        // this.mark = "Your site rating: F";
      }
    }
  };
}

//---------------------------MiddleWare-----------------------------------------



router.post('/', (req, res, next) => {
  sendResponse(req, res);
});





module.exports = router;
