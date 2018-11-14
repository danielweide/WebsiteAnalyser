import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';


@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  url: any;
  mark: any ="";


  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  async verifyWebsite(){
    var score = 0;
    var code = await this.getStatus(this.changeToHTTP());
    if(code >= 300 && code < 400){
      score+=1; //redirected
    } else {
      score+=0;
    }
    code = await this.getStatus(this.changeToHTTPS());
    if(code >= 300 && code < 400){
      score+=0; //redirected
    } else {
      score+=1; //redirected
    }

    if(score == 2){
      // System.out.println("Your site rating: A");
      this.mark = "Your site rating: A";
    } else if(score == 1) {
      // System.out.println("Your site rating: D");
      this.mark = "Your site rating: D";
    } else if(score == 0) {
      // System.out.println("Your site rating: F");
      this.mark = "Your site rating: F";
    }
  }

  changeToHTTP(){
    let urlHTTP = "http://" + this.url;
    return urlHTTP;
  }

  changeToHTTPS(){
    let urlHTTPS = "https://" + this.url;
    return urlHTTPS;
  }



  getStatus(url){
    console.log(url);
    const httpOptions = this.prepareHeader();
    const req = this.http.post('https://urlscan.io/api/v1/scan/', {
      "url": url,
      "public": "on",
    },  httpOptions)
      .subscribe(
        res => {
          console.log(res);
        },
        err => {
          console.log("Error occured ", err);
        }
      );
    return 0;
  }

  prepareHeader(): object {
    const headers = new HttpHeaders(
      {'Content-Type': 'application/json',
        'API-Key' : '3c33ca64-f976-47eb-adbd-10a8b06ed937',
        'Access-Control-Allow-Origin': '*',
      });

    return {
        headers: headers
    };
  }

}
