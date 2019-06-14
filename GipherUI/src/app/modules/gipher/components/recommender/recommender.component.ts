import { Component, OnInit } from '@angular/core';
import { Gif } from '../../gif';
import { GipherService } from '../../gipher.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-recommender',
  templateUrl: './recommender.component.html',
  styleUrls: ['./recommender.component.css']
})
export class RecommenderComponent implements OnInit {

  gifs: Array<Gif>;
  favourite = false;
  statusCode: number;
  errorStatus: string;

  constructor(private gipherService: GipherService, private matSnackbar: MatSnackBar) { }

  ngOnInit() {
    const message = "There is no recommendation right now";
    this.gipherService.getAllGifsForRecommendation().subscribe(data => {
      this.gifs = data;
      if(data.length === 0) {
        this.matSnackbar.open(message, " ", {
          duration: 1000
        })
      }
    })
  }


  addToFavourite(gif) {
    this.gipherService.addGifToFavourite(gif).subscribe(data => {

      this.statusCode = data.status;
      if(this.statusCode === 201) {
        console.log("Success", this.statusCode);
        this.matSnackbar.open("Gif Successfully added !!!", " ", {
          duration: 1000
        });
      }
    },
    error => {
      this.errorStatus = `${error.status}`;
      const errorMsg = `${error.error.message}`;
      this.statusCode = parseInt(this.errorStatus, 10);
      if(this.statusCode === 409) {
        this.matSnackbar.open(errorMsg, "", {
          duration: 1000
        });
        this.statusCode = 0;
      }

    })
  }

}
