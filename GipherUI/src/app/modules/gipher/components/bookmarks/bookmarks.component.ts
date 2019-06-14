import { Component, OnInit } from '@angular/core';
import { Gif } from '../../gif';
import { GipherService } from '../../gipher.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-bookmarks',
  templateUrl: './bookmarks.component.html',
  styleUrls: ['./bookmarks.component.css']
})
export class BookmarksComponent implements OnInit {

  gifs: Array<Gif>;
  favourite = true;
  statusCode: number;

  constructor(private gipherService: GipherService, private matSnackbar: MatSnackBar) { }

  ngOnInit() {
    const message = "Favourite is empty"
    this.gipherService.getAllGifsForFavourite().subscribe(data => {
      this.gifs = data;
      if(data.length === 0) {
        this.matSnackbar.open(message, " ", {
          duration: 1000
        })
      }
    })
  }

  deleteFromWishList(gif) {
    this.gipherService.deleteGifFromFavourite(gif).subscribe(data => {
      console.log("deleted", data);
      const index = this.gifs.indexOf(gif)
      this.gifs.splice(index, 1);
      this.matSnackbar.open("Successfully deleted", " ", {
        duration: 1000
      });
    });
  }

}
