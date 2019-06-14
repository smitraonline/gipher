import { Component, OnInit } from '@angular/core';
import { UserSearchHistory } from '../../user-search-history';
import { GipherService } from '../../gipher.service';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { DISABLE_AUTO_SAVE } from 'src/app/modules/authentication/authentication.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  historical: Map<string, Array<UserSearchHistory>>;
  searches: Array<UserSearchHistory>;
  checked: boolean;

  constructor(public router: Router, private gipherService: GipherService, private matSnackbar: MatSnackBar) { }

  ngOnInit() {
    this.checked = !JSON.parse(sessionStorage.getItem(DISABLE_AUTO_SAVE));
    this.searches = [];
    // this.searchKey = this.activatedRoute.snapshot.queryParams['q'];
    this.gipherService.getAllSearchHistory().subscribe(response => this.processResponse(response))
  }

  // processResponse(response: any) {
  //   const data = response;
  //   var searches: Array<UserSearchHistory>;
  //   searches = [];
  //   data.forEach(targetData => {
  //     var date = new Date(targetData.logtime).toLocaleDateString();
  //     var search = new UserSearchHistory();
  //     search.id = targetData.id;
  //     search.keyword = targetData.keyword;
  //     search.logtime = new Date(targetData.logtime).toLocaleTimeString();
  //     if(this.historical.has(date)) {
  //       this.historical.get(date).push(search);
  //     } else {
  //       var searches = [];
  //       searches.push(search);
  //       this.historical.set(date, searches);
  //     }
  //   });
  //   console.log(this.historical);
  // }

  processResponse(response: any) {
    const data = response;
    this.searches = [];
    data.forEach(targetData => {
      var search = new UserSearchHistory();
      search.id = targetData.id;
      search.keyword = targetData.keyword;
      search.logtime = new Date(targetData.logtime).toLocaleDateString() + " " + new Date(targetData.logtime).toLocaleTimeString();
      this.searches.push(search);
    });
  }
  
  deleteSearch(search: UserSearchHistory) {
    this.gipherService.deleteUserHistory(search.id).subscribe(response => {
      const index = this.searches.indexOf(search)
      this.searches.splice(index, 1);
      this.matSnackbar.open("Successfully deleted", " ", {
        duration: 1000
      });
    
    
    });
  }

  toggleAutoSave() {
    console.log(this.checked);
    sessionStorage.setItem(DISABLE_AUTO_SAVE, (this.checked)? "false" : "true");
  }
}
