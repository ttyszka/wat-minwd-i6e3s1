import { Component, OnInit } from '@angular/core';
import { IWordsMapModel } from 'src/app/models/words-map-model';
import { WordsMapService } from 'src/services/words-map-service';
import {coerceNumberProperty} from '@angular/cdk/coercion';
import { CloudData, CloudOptions } from 'angular-tag-cloud-module';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-words-map',
  templateUrl: './words-map.component.html',
  styleUrls: ['./words-map.component.scss'],
})
export class WordsMapComponent implements OnInit {
  private _tickInterval = 1;
  wordsMapModel: IWordsMapModel[];
  constCollection: IWordsMapModel[];
  finalWordsMap: CloudData[];
  numberOfTweets = 20;
  startValue = 20;
  currentValue: number;


  options: CloudOptions = {
    // if width is between 0 and 1 it will be set to the size of the upper element multiplied by the value
    width: 900,
    height: 300,
    overflow: true,
  };

  data: CloudData[];

  autoTicks = false;
  disabled = false;
  invert = false;
  max = 100;
  min = 10;
  showTicks = false;
  step = 1;
  thumbLabel = true;
  value = this.startValue;
  vertical = false;

  get tickInterval(): number | 'auto' {
    return this.showTicks ? (this.autoTicks ? 'auto' : this._tickInterval) : 0;
  }
  set tickInterval(value) {
    this._tickInterval = coerceNumberProperty(value);
  }


  constructor( private wordsMapService : WordsMapService, private spinner: NgxSpinnerService) { this.data = [];}

  ngOnInit() {
    this.currentValue = this.startValue;
    this.getWordsMap(this.numberOfTweets);
  }
  setData(wordsMapModel : any) {
    this.data =[];
    this.finalWordsMap = [];
    wordsMapModel.forEach(element => {
      this.data.push(element);
    });
    this.finalWordsMap = this.data;
  }

  onSliderChange(event){
    this.currentValue = event.value;
  }

  onButtonClick(){
    this.getWordsMap(this.currentValue);
  }

  getWordsMap(tweets: number) {
    this.spinner.show();
    this.wordsMapService.getWordsMapKeyValueList(tweets).subscribe( x => {
      this.wordsMapModel = x;
      this.setData(x);
      this.spinner.hide();
    });
  }

}
