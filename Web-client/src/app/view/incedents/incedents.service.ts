import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ApiService} from '../../services/api.service';

@Injectable({
  providedIn: 'root'
})
export class IncedentsService {

  constructor(private api: ApiService) { }

  url = '';
  baseUrl =  ''; // 'http://localhost:3000';
  public get(): Observable<any> {
    return this.api.get(this.url);
  }

  public postData(data: any) {
    return this.api.postData(this.url, data);
  }
}
