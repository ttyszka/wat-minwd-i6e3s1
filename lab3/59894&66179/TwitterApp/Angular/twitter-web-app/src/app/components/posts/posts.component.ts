import { Component, OnInit } from '@angular/core';
import { TweetService } from 'src/services/tweet.service';
import { ITweetModel } from 'src/app/models/tweets-model';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-posts',
  templateUrl: './posts.component.html',
  styleUrls: ['./posts.component.scss']
})
export class PostsComponent implements OnInit {

  tweetModel: ITweetModel;

  constructor(private tweetService: TweetService, private spinner: NgxSpinnerService) {}

  ngOnInit() {
    this.getUserTweetIds();
  }

  getUserTweetIds() {
    this.spinner.show();
    this.tweetService.getFirstAndLastTweetId().subscribe( x => {
      this.tweetModel = x;
      this.spinner.hide();
    });
  }
}
