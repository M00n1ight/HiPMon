import { Injectable } from '@angular/core';
import {HttpClient, HttpEventType, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable, Subject} from 'rxjs';

@Injectable()
export class ApiService {
  constructor(private httpClient: HttpClient) { }

  baseUrl = 'http://localhost:3000';
  public get(url: string): Observable<any> {
    return this.httpClient.get(this.baseUrl + '/' + url);
  }

  public postData(url: string, data: any) {
    return this.httpClient.post(this.baseUrl + '/' + url, data);
  }
}
