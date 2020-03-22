import {Injectable} from '@angular/core';
import {ApiService} from '../../services/api.service';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HistoryService {

  constructor(private api: ApiService) {
  }

  url = 'http://25.104.118.61:8100/history';
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
