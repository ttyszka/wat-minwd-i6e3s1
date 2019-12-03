import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from '../environments/environment';

const httpAddons = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
  export class HttpClientUtilsService{
    constructor(private http: HttpClient) { }

    public get = <T>(route: string) => {
      return this.http.get<T>(this.createCompleteRoute(route, environment.urlAddress));
    }

    public post = <T>(route: string, body) => {
      return this.http.post<T>(this.createCompleteRoute(route, environment.urlAddress), JSON.stringify(body), httpAddons);
    }

    public put = <T>(route: string, body) => {
      return this.http.put<T>(this.createCompleteRoute(route, environment.urlAddress), body, httpAddons);
    }

    public delete = (route: string) => {
      return this.http.delete(this.createCompleteRoute(route, environment.urlAddress));
    }

    private createCompleteRoute = (route: string, envAddress: string) => {
      return `${envAddress}/${route}`;
    }

}
