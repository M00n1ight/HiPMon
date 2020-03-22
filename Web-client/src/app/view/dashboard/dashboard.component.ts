import {ChangeDetectionStrategy, Component, OnInit, ViewEncapsulation} from '@angular/core';
import {CompactType, DisplayGrid, GridType} from 'angular-gridster2';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {DateTimeAdapter} from '@danielmoncada/angular-datetime-picker';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
  changeDetection: ChangeDetectionStrategy.Default,
  encapsulation: ViewEncapsulation.None
})

export class DashboardComponent implements OnInit {

  constructor() {
  }

  options = {
    gridType: GridType.Fixed,
    compactType: CompactType.None,
    margin: 10,
    outerMargin: true,
    outerMarginTop: null,
    outerMarginRight: null,
    outerMarginBottom: null,
    outerMarginLeft: null,
    useTransformPositioning: true,
    mobileBreakpoint: 1340,
    minCols: 31,
    maxCols: 31,
    minRows: 10,
    maxRows: 100,
    maxItemCols: 100,
    minItemCols: 6,
    maxItemRows: 100,
    minItemRows: 4,
    maxItemArea: 2500,
    minItemArea: 1,
    defaultItemCols: 1,
    defaultItemRows: 1,
    fixedColWidth: 51,
    fixedRowHeight: 51,
    keepFixedHeightInMobile: false,
    keepFixedWidthInMobile: false,
    scrollSensitivity: 10,
    scrollSpeed: 20,
    enableEmptyCellClick: false,
    enableEmptyCellContextMenu: false,
    enableEmptyCellDrop: false,
    enableEmptyCellDrag: false,
    enableOccupiedCellDrop: false,
    emptyCellDragMaxCols: 50,
    emptyCellDragMaxRows: 50,
    ignoreMarginInRow: false,
    draggable: {
      enabled: true,
    },
    resizable: {
      enabled: true,
    },
    swap: false,
    pushItems: true,
    disablePushOnDrag: false,
    disablePushOnResize: false,
    pushDirections: {north: true, east: true, south: true, west: true},
    pushResizeItems: false,
    displayGrid: DisplayGrid.OnDragAndResize,
    disableWindowResize: false,
    disableWarnings: false,
    scrollToNewItems: false
  };

  dashboard = [
    {cols: 10, rows: 5, y: 0, x: 0},
    {cols: 10, rows: 5, y: 0, x: 2},
    {cols: 10, rows: 5, y: 0, x: 4},
    {cols: 10, rows: 5, y: 2, x: 5},
  ];


  title = 'date-time-range-picker';
  startDate: string; // start date will be stored here
  endDate: string; // end date will be stored here

  public selectedMoments = [new Date(), new Date()];

  log() {
    console.log(this.selectedMoments)
  }

  ngOnInit(): void {
  }

}
