import {Component, OnInit} from '@angular/core';
import {Group} from '../settings/sensors-group.component';
import {ApiService} from '../../services/api.service';
import {HistoryService} from './history.service';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.scss']
})
export class HistoryComponent implements OnInit {

  constructor(
    private historyService: HistoryService
  ) {
  }

  displayedColumns = ['timestamp', 'type', 'modification'];
  dataSource: History[] = [];
  public selectedMoments = [new Date('2020.03.22'), new Date()];

  log() {
    this.ngOnInit();
    console.log(this.selectedMoments);
  }

  ngOnInit(): void {
    this.getData();
    this.dataSource.forEach((elem) => {
      elem.timestamp = new Date(elem.timestamp);
    });
    console.log(this.dataSource);
  }

  getData(): any {
    this.historyService.get().subscribe((data: any[]) => {
      this.dataSource = data;

      this.dataSource.forEach((elem) => {
        elem.timestamp = new Date(elem.timestamp).toLocaleString();
      });
      console.log(this.dataSource);
    });
  }
}

export interface History {
  id: string;
  timestamp: any;
  sensorId: string;
  newBottomThreshold: string;
  newTopThreshold: string;
}

