import { Injectable } from '@angular/core';
import { HttpClientUtilsService } from 'src/common/http-client-utils.service';
import { Observable, of } from 'rxjs';
import { IWordsMapModel } from 'src/app/models/words-map-model';
import { CloudData, CloudOptions } from 'angular-tag-cloud-module';


@Injectable({
  providedIn: 'root'
})
export class WordsMapService {
  private tweetEndpointUrl = 'wordsmap?';
  constructor(private httpService: HttpClientUtilsService) { }


  public getWordsMapKeyValueList(tweets : number): Observable<IWordsMapModel[]> {

    return this.httpService.get<IWordsMapModel[]>(this.tweetEndpointUrl +
      `tweets=${tweets}&ignoreUrls=1&wordsNo=20`);
  }
}
