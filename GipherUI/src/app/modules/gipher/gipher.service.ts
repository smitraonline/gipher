import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, empty } from 'rxjs';
import { Gif } from './gif';
import { USER_NAME } from '../authentication/authentication.service';
import { UserSearchHistory } from './user-search-history';

@Injectable({
  providedIn: 'root'
})
export class GipherService {
  giphyTrendingAPI: string;
  giphySearchAPI: string;
  gipherManagerEndPoint: string;
  gipherRecommnederEndPoint: string;
  apiKey: string;
  username: string;

  constructor(private httpClient: HttpClient) {
    this.giphyTrendingAPI = 'http://api.giphy.com/v1/gifs/trending?limit=12';
    this.giphySearchAPI = 'http://api.giphy.com/v1/gifs/search?limit=12&q=';
    this.apiKey = '&api_key=LnhMbz6XUQNMTjnf3Y8n1O8YMP88gYs1';
    this.gipherManagerEndPoint = "http://"+ location.hostname + ":9080/giphermanagerservice/api/v1/";
    this.gipherRecommnederEndPoint = "http://"+ location.hostname + ":9080/gipherrecommenderservice/api/v1/";
  };

  getGifs(keyword: string, offset: number): Observable<any> {
    console.log(keyword);
    if(keyword) {
      return this.searchGifs(keyword, offset);
    } else {
      return this.getTrendingGifs(offset);
    }
  };
  
  getTrendingGifs(offset: number): Observable<any> {
    let url = `${this.giphyTrendingAPI}${this.apiKey}`;
    if(offset) {
      url = `${url}&offset=${offset}`;
    }
    return this.httpClient.get(url);
  };
  
  searchKey: string;
  searchGifs(keyword: string, offset: number): Observable<any> {
    this.searchKey = keyword;
    let url = `${this.giphySearchAPI}${keyword}${this.apiKey}`;
    if(offset) {
      url = `${url}&offset=${offset}`;
    }
    return this.httpClient.get(url);
  };

  storeSearch(keyword: string) {
    this.username = sessionStorage.getItem(USER_NAME);
    if(this.username) {
      var when = new Date();
      console.log('storing search: '+keyword);
      var history = new UserSearchHistory();
      history.keyword = keyword;
      history.logtime = when.toISOString();
      history.id = when.toISOString() + "-" + this.username;
      const url = this.gipherManagerEndPoint + "user/" + this.username + "/history";
      return this.httpClient.post(url, history, {
        observe: "response"
      });
    } else {
      return empty();
    }
  }

  deleteUserHistory(id: string) {
    this.username = sessionStorage.getItem(USER_NAME);
    const url = this.gipherManagerEndPoint + "user/" + this.username + "/history/" + `${id}`;
    return this.httpClient.delete(url);
  }

  getAllSearchHistory(): Observable<UserSearchHistory[]> {
    this.username = sessionStorage.getItem(USER_NAME);
    const url = this.gipherManagerEndPoint + "user/" + this.username + "/histories";
    return this.httpClient.get<UserSearchHistory[]>(url);
  }

  addGifToFavourite(gif: Gif) {
    this.username = sessionStorage.getItem(USER_NAME);
    const url = this.gipherManagerEndPoint + "user/" + this.username + "/gif";
    return this.httpClient.post(url, gif, {
      observe: "response"
    });
  };

  getAllGifsForFavourite(): Observable<Gif[]> {
    this.username = sessionStorage.getItem(USER_NAME);
    const url = this.gipherManagerEndPoint + "user/" + this.username + "/gifs";
    return this.httpClient.get<Gif[]>(url);
  }

  deleteGifFromFavourite(gif: Gif) {
    this.username = sessionStorage.getItem(USER_NAME);
    const url = this.gipherManagerEndPoint + "user/" + this.username + "/" + `${gif.id}`;
    return this.httpClient.delete(url);
  }

  getAllGifsForRecommendation(): Observable<Gif[]> {
    this.username = sessionStorage.getItem(USER_NAME);
    const url = this.gipherRecommnederEndPoint + "recommendation";
    return this.httpClient.get<Gif[]>(url);
  }

}
