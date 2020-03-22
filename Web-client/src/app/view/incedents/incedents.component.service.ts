import { Injectable } from '@angular/core';
import {HttpClient, HttpEventType, HttpRequest, HttpResponse} from '@angular/common/http';
import {Observable, Subject} from 'rxjs';

@Injectable()
export class IncedentsComponentService {
  constructor(private httpClient: HttpClient) { }

  url = "";
  baseUrl =  ''; // 'http://localhost:3000';
  public get(): Observable<any> {
    return this.httpClient.get(this.url);
  }

  public postData(data: any) {
    return this.httpClient.post(this.url, data);
  }
}
