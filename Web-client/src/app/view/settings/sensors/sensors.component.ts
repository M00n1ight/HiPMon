import { Component, OnInit } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {ModalFromSensorComponent} from '../modal-from-sensor/modal-from-sensor.component';
import {Group} from '../sensors-group.component';
import {Router} from '@angular/router';
import {ApiService} from '../../../services/api.service';
import {ActivatedRoute} from '@angular/router';

export interface Sensors {
  id: string;
  name: string;
  type: string;
  bottomThreshold: string;
  topThreshold: string;
}


@Component({
  selector: 'app-sensors',
  templateUrl: './sensors.component.html',
  styleUrls: ['./sensors.component.scss']
})
export class SensorsComponent implements OnInit {

  constructor(public dialog: MatDialog,
              private api: ApiService,
              private route: ActivatedRoute) {}

  displayedColumns = ['name', 'id', 'bottomThreshold', 'topThreshold'];
  dataSource: Sensors[] = [
  ];
 id: string;
  ngOnInit(): void {

    this.route.paramMap.subscribe( paramMap => {
      this.id = paramMap.get('id');
    });
    this.getData();
  }

  getData(): any {
    this.api.get('http://25.104.118.61:8110/sensors?groupId=' + this.id).subscribe((data: any) => {
      this.dataSource = data;
    });
  }

  openDialog(elem: any): void {
    const dialogRef = this.dialog.open(ModalFromSensorComponent, {
      width: '250px',
      data: elem
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }


}


