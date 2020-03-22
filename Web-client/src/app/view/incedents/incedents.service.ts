import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ApiService} from '../../services/api.service';

@Injectable({
  providedIn: 'root'
})
export class IncedentsService {

  constructor(private api: ApiService) {
  }

  url = 'http://25.104.118.61:8100/problems';
  baseUrl = ''; // 'http://localhost:3000';
  public get(date1?, date2?): Observable<any> {
    let params = '';
    if (date1 && date2) {
      let params = '?' + Math.round((date1).getTime()) + '&to=' + Math.round((date2).getTime());
    }
    return this.api.get(this.url + params);
  }

  public postData(data: any) {
    return this.api.postData(this.url, data);
  }
}
