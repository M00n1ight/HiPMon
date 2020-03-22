import { Injectable } from '@angular/core';
import {ApiService} from '../../services/api.service';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  constructor(private api: ApiService) { }

  url = 'http://25.104.118.61:8100/history';
  baseUrl = ''; // 'http://localhost:3000';
  public get(id: Array<any>, date1?, date2?): Observable<any> {
    let params = '?';
    if (id) {
      params = params + 'id=';
    }
    params = params + id.join('&id=');
    if (date1 && date2) {
      params = params + '&from=' + Math.round((date1).getTime()) + '&to=' + Math.round((date2).getTime());
    }
    return this.api.get(this.url + params);
  }

  public postData(data: any) {
    return this.api.postData(this.url, data);
  }
}
