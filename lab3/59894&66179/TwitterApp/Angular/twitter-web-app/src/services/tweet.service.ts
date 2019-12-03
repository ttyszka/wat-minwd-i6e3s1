import { Injectable } from '@angular/core';
import { HttpClientUtilsService } from 'src/common/http-client-utils.service';
import { Observable, of } from 'rxjs';
import { ITweetModel } from 'src/app/models/tweets-model';

@Injectable({
  providedIn: 'root'
})
export class TweetService {
  private tweetEndpointUrl = 'tweetids?tweets=2000';
  constructor(private httpService: HttpClientUtilsService) { }

  public getFirstAndLastTweetId(): Observable<ITweetModel> {
    return this.httpService.get<ITweetModel>(this.tweetEndpointUrl);
  }
}
