import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.scss']
})
export class HistoryComponent implements OnInit {

  constructor() { }

  displayedColumns = ['timestamp', 'sensorId', 'newBottomThreshold', 'newTopThreshold'];
  dataSource: History[] = [
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
    {id: '1', timestamp: new Date().toLocaleDateString(), sensorId: 'sdfsdsd', newBottomThreshold: '1.0079', newTopThreshold: 'H'},
  ];
  public selectedMoments = [new Date(), new Date()];

  log() {
    console.log(this.selectedMoments)
  }

  ngOnInit(): void {
  }

}

export interface History {
  id: string;
  timestamp: any;
  sensorId: string;
  newBottomThreshold: string;
  newTopThreshold: string;
}

