import { TestBed } from '@angular/core/testing';

import { GipherService } from './gipher.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { Gif } from './gif';

describe('GipherService', () => {

  let gif = new Gif();
  gif = {
    id : "VaHIP3dsOeyLC",
    title : "pride GIF",
    rendition : {
      fixed_width_downsampled : {
          url : "https://media0.giphy.com/media/VaHIP3dsOeyLC/200w_d.gif?cid=625746d25cf8b216506530464d660d44&rid=200w_d.gif",
          width : "200",
          height : "175",
          size : "26362",
          frames: null,
          mp4: null,
          mp4_size: null,
          webp: null,
          webp_size: null,
          hash: null,
      },
      fixed_height_still: null,
      original_still: null,
      fixed_width: null,
      fixed_height_small_still: null,
      fixed_height_downsampled: null,
      preview: null,
      fixed_height_small: null,
      downsized_still: null,
      downsized: null,
      downsized_large: null,
      fixed_width_small_still: null,
      preview_webp: null,
      fixed_width_still: null,
      fixed_width_small: null,
      downsized_small: null,
      downsized_medium: null,
      original: null,
      fixed_height: null,
      looping: null,
      original_mp4: null,
      preview_gif: null,
    },
    type: null,
    slug: null,
    url: null,
    bitly_gif_url: null,
    bitly_url: null,
    embed_url: null,
    username: null,
    source: null,
    rating: null,
    content_url: null,
    source_tld: null,
    source_post_url: null,
    is_sticker: 0,
    import_datetime: null,
    trending_datetime: null,
  };

  const springEndPoint = "http://"+ location.hostname + ":9080/giphermanagerservice/api/v1/";
  const gipherRecommnederEndPoint = "http://"+ location.hostname + ":9080/gipherrecommenderservice/api/v1/";
  let gipherService : GipherService;
  let httpTestingController :  HttpTestingController;


  beforeEach(() => {TestBed.configureTestingModule({
    imports: [HttpClientTestingModule],
    providers: [GipherService]
  });

  gipherService = TestBed.get(GipherService);
  httpTestingController = TestBed.get(HttpTestingController);
  });

  it('should be created', () => {
    //const service: GipherService = TestBed.get(GipherService);
    expect(gipherService).toBeTruthy();
  });

  it('#addGifToFavourite() should fetch proper response from Http call', () => {
    gipherService.addGifToFavourite(gif).subscribe(res => {
      console.log(res);
      expect(res.body).toBe(gif);
    });

    const url = springEndPoint + "user/" + "test" + "/gif";
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('POST');
    expect(httpMockReq.request.url).toEqual(url);
  });

  it('#getAllGifsForFavourite() should fetch proper response from Http call', () => {
    gipherService.getAllGifsForFavourite().subscribe(res => {});

    const url = springEndPoint + "user/" + "test" + "/gifs"
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('GET');
    expect(httpMockReq.request.url).toEqual(url);
  });
  
  it('#deleteGifFromFavourite() should fetch proper response from Http call', () => {
    gipherService.deleteGifFromFavourite(gif).subscribe(res => {});

    const url = springEndPoint + "user/" + "test" + "/" + gif.id;
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('DELETE');
    expect(httpMockReq.request.url).toEqual(url);
  });

  it('#getAllSearchHistory() should fetch proper response from Http call', () => {
    gipherService.getAllSearchHistory().subscribe(res => {});

    const url = springEndPoint + "user/" + "test" + "/histories"
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('GET');
    expect(httpMockReq.request.url).toEqual(url);
  });
  
  it('#deleteUserHistory() should fetch proper response from Http call', () => {
    gipherService.deleteUserHistory(gif.id).subscribe(res => {});

    const url = springEndPoint + "user/" + "test" + "/history/" + gif.id;
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('DELETE');
    expect(httpMockReq.request.url).toEqual(url);
  });

  it('#getAllGifsForRecommendation() should fetch proper response from Http call', () => {
    gipherService.getAllGifsForRecommendation().subscribe(res => {});

    const url = gipherRecommnederEndPoint + "recommendation"
    const httpMockReq = httpTestingController.expectOne(url);
    expect(httpMockReq.request.method).toBe('GET');
    expect(httpMockReq.request.url).toEqual(url);
  });
  
});
