import {Component, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CloudData, CloudOptions} from 'angular-tag-cloud-module';
import {Observable, of} from 'rxjs';
import {MatSnackBar} from '@angular/material/snack-bar';


import {Tags} from './models/tags';
import {map} from 'rxjs/operators';
import {User} from './models/user';
import {Tweet} from './models/tweet';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
@Injectable()
export class AppComponent {
  private areTweetsVisible = false;
  private areDetailsVisible = false;
  private isCloudVisible = false;
  private dataSource: PeriodicElement[];

  constructor(private http: HttpClient, private snackBar: MatSnackBar) {
  }

  /*httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*'
    })
  };*/
  resultUser: User = {
    uzytkownik: '',
    nazwaUzytkownika: '',
    miejscowosc: '',
    obserwujacy: '',
    obserwowani: '',
    iloscPostow: '',
  };

  resultTweetArray: Tweet[] = [];
  resultTagsArray: Tags[] = [];
  displayedColumns: string[] = ['uzytkownik', 'nazwaUzytkownika', 'miejscowosc', 'obserwujacy', 'obserwowani', 'iloscPostow'];
  username = 'ventus_34';
  options: CloudOptions = {
    // if width is between 0 and 1 it will be set to the size of the upper element multiplied by the value
    width: 1700,
    realignOnResize: false,
    height: 300,
    overflow: false,
  };

  data: CloudData[];
  ELEMENT_DATA: PeriodicElement[] = [];

  showUserTweets() {
    console.log('Show user\'s tweets');
    this.isCloudVisible = false;
    this.areTweetsVisible = true;
    this.areDetailsVisible = false;
  }

  showUserTagCloud() {
    console.log('Show user\'s tag\'s cloud');
    this.isCloudVisible = true;
    this.areTweetsVisible = false;
    this.areDetailsVisible = false;
    this.data = [];
    for (const tag of this.resultTagsArray) {

      const changedData$: Observable<CloudData[]> = of([{text: tag.tag, weight: tag.occurrences}]);
      changedData$.subscribe(res => this.data = this.data.concat(res));
    }
  }

  showUserPersonals() {
    console.log(this.resultUser.miejscowosc);
    this.dataSource = this.ELEMENT_DATA;
    console.log(this.resultUser.miejscowosc);
    this.isCloudVisible = false;
    this.areTweetsVisible = false;
    this.areDetailsVisible = true;
    console.log('Show user\'s personals');

  }

  openComment() {

    this.snackBar.openFromComponent(CommentComponent, {
      duration: 500,
    });
  }

  retrieveTweets() {
    return this.http
      .get('http://localhost:3000/timeline')
      .pipe(
        map((responseData: { [key: string]: Tweet }) => {
          const postsArray: Tweet[] = [];
          for (const key in responseData) {
            if (responseData.hasOwnProperty(key)) {
              postsArray.push({...responseData[key]});
              this.resultTweetArray.push(
                new Tweet(postsArray[key].createTime,
                  postsArray[key].message,
                  postsArray[key].lang,
                  postsArray[key].retweeted));
            }
          }
          return postsArray;
        })
      )
      .subscribe(posts => {
        console.log(posts);
      });
  }

  retrieveTags() {
    return this.http
      .get('http://localhost:3000/tags')
      .pipe(
        map((responseData: { [key: string]: Tags }) => {
          const postsArray: Tags[] = [];
          for (const key in responseData) {
            if (responseData.hasOwnProperty(key)) {
              postsArray.push({...responseData[key]});
              this.resultTagsArray.push(
                new Tags(postsArray[key].tag, postsArray[key].occurrences));
            }
          }
          return postsArray;
        })
      )
      .subscribe(posts => {
        console.log(posts);
      });
  }

  retrieveUser() {

    return this.http
      .get('http://localhost:3000/user')
      .pipe(
        map(responseData => {
          const postsArray = [];
          for (const key in responseData) {
            if (responseData.hasOwnProperty(key)) {
              postsArray.push(responseData[key]);
            }
          }
          this.resultUser.uzytkownik = postsArray[0];
          this.resultUser.nazwaUzytkownika = postsArray[1];
          this.resultUser.miejscowosc = postsArray[2];
          this.resultUser.obserwujacy = postsArray[3];
          this.resultUser.obserwowani = postsArray[4];
          this.resultUser.iloscPostow = postsArray[5];
          this.ELEMENT_DATA = [
            {
              uzytkownik: this.resultUser.uzytkownik,
              nazwaUzytkownika: this.resultUser.nazwaUzytkownika,
              miejscowosc: this.resultUser.miejscowosc,
              obserwujacy: this.resultUser.obserwujacy,
              obserwowani: this.resultUser.obserwowani,
              iloscPostow: this.resultUser.iloscPostow
            },
          ];
          return postsArray;
        })
      )
      .subscribe(posts => {
        console.log(posts);
      });
  }
}


@Component({
  templateUrl: 'app.component-comment.html',
  styles: [`
    .pop-up-comment {
      color: lightgoldenrodyellow;
      flex: min-content;
      border-color: rebeccapurple;
    }
  `],
})
export class CommentComponent {
}

export interface PeriodicElement {
  uzytkownik: string;
  nazwaUzytkownika: string;
  miejscowosc: string;
  obserwujacy: string;
  obserwowani: string;
  iloscPostow: string;
}



