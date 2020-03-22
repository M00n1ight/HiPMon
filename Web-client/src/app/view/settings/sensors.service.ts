import {Injectable} from '@angular/core';
import {ApiService} from '../../services/api.service';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SensorsService {

  constructor(private api: ApiService) {
  }
  url = 'http://25.104.118.61:8110/sensors';
  baseUrl = ''; // 'http://localhost:3000';
  public getGroups(): Observable<any> {
    return this.api.get('http://25.104.118.61:8110/sensors/groups');
  }

  public getSensors(param: string): Observable<any> {
    return this.api.get(this.url + '?' + param);
  }

  public postData(data: any) {
    return this.api.postData('http://25.104.118.61:8110/sensors/update', data);
  }
}
