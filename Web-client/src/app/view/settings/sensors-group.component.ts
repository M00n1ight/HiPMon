import { Component, OnInit } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {ModalFromSensorComponent} from './modal-from-sensor/modal-from-sensor.component';
import {Router} from '@angular/router';
import {ApiService} from '../../services/api.service';

@Component({
  selector: 'app-settings',
  templateUrl: './sensors-group.component.html',
  styleUrls: ['./sensors-group.component.scss']
})
export class SensorsGroupComponent implements OnInit {

  constructor(public dialog: MatDialog,
              private router: Router,
              private api: ApiService) {}

  displayedColumns = ['id', 'name'];
  dataSource: Group[] = [
    {id: '1', name: 'TEST'},
  ];
  ngOnInit(): void {
  }

  goToGroup(elem: Group): void {
    this.router.navigate(['/sensors/' + elem.id]);
  }

  getData(): any {
    this.api.get('/sensors/groups').subscribe((data: Group[]) => {
      this.dataSource = data;
    });
  }


}

export interface Group {
  id: string;
  name: string;
}
