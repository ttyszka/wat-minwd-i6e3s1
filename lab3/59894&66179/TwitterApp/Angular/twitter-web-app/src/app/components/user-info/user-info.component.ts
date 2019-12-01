import { Component, OnInit } from '@angular/core';
import { UserInfoService } from "src/services/user-info.service";
import { IUserModel } from "src/app/models/user-model";
import { DomSanitizer, SafeResourceUrl, SafeUrl } from '@angular/platform-browser';
import { DatePipe } from '@angular/common';
import { NgxSpinnerService } from 'ngx-spinner';


@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.scss']
})
export class UserInfoComponent implements OnInit {

  userModel : IUserModel;
  imagePath : SafeResourceUrl;

  constructor(private userInfoService: UserInfoService,
    private _sanitizer: DomSanitizer,
    private datepipe: DatePipe,
    private spinner: NgxSpinnerService) { }

  ngOnInit() {
    this.GetUserModel();
  }
  GetUserModel() {
    this.spinner.show();
    this.userInfoService.GetUserProfileInfo().subscribe( x => {
      this.userModel = x;
      this.Photo_url(x.profileImage);
      this.spinner.hide();
      });
  }
  Photo_url(data: string){
    this.imagePath = this._sanitizer.bypassSecurityTrustResourceUrl(
      'data:image/jpeg;base64,' + data);
    }

}
