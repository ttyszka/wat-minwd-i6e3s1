import { Injectable } from '@angular/core';
import { HttpClientUtilsService } from 'src/common/http-client-utils.service';
import { Observable, of } from 'rxjs';
import { IUserModel } from 'src/app/models/user-model';


@Injectable({
  providedIn: 'root'
})
export class UserInfoService {
  private userInfoEndpointUrl = 'userprofile';
  constructor( private httpService : HttpClientUtilsService ) { }

  public GetUserProfileInfo() : Observable <IUserModel>{
    return this.httpService.get<IUserModel>(this.userInfoEndpointUrl);
  }
}