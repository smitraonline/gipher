import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { MatDialog } from '@angular/material';
import { Gif } from '../../gif';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
  @Input()
  gif: Gif;

  @Input()
  favourite: boolean;

  @Output()
  addToFavourite = new EventEmitter();

  @Output()
  deleteFromFavourite = new EventEmitter();

  constructor(private dialog: MatDialog) { }

  ngOnInit() {
  }

  addButtonClick(gif) {
    console.log(gif);
    this.addToFavourite.emit(gif);
  }

  deleteButtonClick(gif) {
    this.deleteFromFavourite.emit(gif);
  }

}
