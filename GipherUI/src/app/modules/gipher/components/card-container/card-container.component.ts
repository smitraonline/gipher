import { Component, OnInit } from '@angular/core';
import { GipherService } from '../../gipher.service';
import { MatSnackBar } from '@angular/material';
import { Gif } from '../../gif';
import { Rendition } from '../../rendition';
import { Image } from '../../image';
import { ActivatedRoute, Router } from '@angular/router';
import { DISABLE_AUTO_SAVE } from 'src/app/modules/authentication/authentication.service';

@Component({
  selector: 'app-card-container',
  templateUrl: './card-container.component.html',
  styleUrls: ['./card-container.component.css']
})
export class CardContainerComponent implements OnInit {
  gifs: Array<Gif>;
  searchedGifs: Array<Gif>;
  searchKey: string;
  offset: number;
  autosave: boolean;
  statusCode: number;
  errorStatus: string;

  constructor(public router: Router, private activatedRoute: ActivatedRoute, private gipherService: GipherService, private matSnackbar: MatSnackBar) {
    this.gifs = [];
  }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
      this.searchKey = params['q'];
    });
    this.autosave = !JSON.parse(sessionStorage.getItem(DISABLE_AUTO_SAVE));
    this.searchKey = this.activatedRoute.snapshot.queryParams['q'];
    this.gipherService.getGifs(this.searchKey, 0).subscribe(response => this.processResponse(response))
  }

  onKey(event: any) {
    this.searchKey = event.target.value;
    if(this.searchKey.length < 3) return; 
    this.router.navigate(['explore'], { queryParams: { q: this.searchKey } });
    
    // this.activatedRoute.queryParams.subscribe(params => {
    //   this.searchKey = params['q'];
    // });
    // this.searchKey = this.activatedRoute.snapshot.queryParams['q'];
    
    if(this.autosave) {
      var key = this.searchKey;
      setTimeout(() => {this.autoSaveSearch(key);},10000);
    }
    this.gifs = [];
    this.gipherService.searchGifs(this.searchKey, 0).subscribe(response => this.processResponse(response))

  }

  autoSaveSearch(keyword: string) {
    console.log(keyword)
    if(keyword == this.searchKey) {
      this.gipherService.storeSearch(this.searchKey).subscribe(response => {console.log("auto saved")});
    }
  }

  saveSearch() {
    this.gipherService.storeSearch(this.searchKey).subscribe(response => {console.log(response)});
  }

  showMore() {
    if(!this.offset)this.offset=0;
    this.offset +=12;
    this.gipherService.getGifs(this.searchKey, this.offset).subscribe(response => this.processResponse(response))

  }

  processResponse(response: any) {
    console.log(this.searchKey);
    const data = response['data'];

    data.forEach(targetData => {
      var gif = new Gif();
      var rendition = new Rendition();
      gif = targetData;
      for (var attribute in targetData["images"]) {
        if (typeof targetData["images"][attribute] === "object") {
          rendition[attribute] = <Image>targetData["images"][attribute];
        } else {
          rendition[attribute] = attribute;
        }
      }
      gif.rendition = rendition;
      delete gif['images'];

      this.gifs.push(gif);
      this.searchedGifs = this.gifs;
      
    });
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
