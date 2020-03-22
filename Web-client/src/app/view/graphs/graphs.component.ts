import {Component, Input, OnInit} from '@angular/core';
import {ChartDataSets, ChartOptions} from 'chart.js';
import {Color, Label} from 'ng2-charts';

@Component({
  selector: 'app-graphs',
  templateUrl: './graphs.component.html',
  styleUrls: ['./graphs.component.scss']
})
export class GraphsComponent implements OnInit {

  @Input()
  name = 'unknown';

  constructor() {
  }

  public lineChartData: ChartDataSets[] = [
    {
      data: [ 65, 59, 80, 81, 65],
      label: 'Series A',
      backgroundColor: 'deepskyblue'
    },
  ];
  public lineChartLabels: Label[] = ['1', '2', '3', '4', '1'];
  public lineChartOptions: ChartOptions = {
    spanGaps: true,
    elements: {
      point: {
        pointStyle: 'line'
      }
    },
    tooltips: {
      mode: 'nearest',
      intersect: false,
      position: 'nearest',
    },
  };

  public lineChartType = 'line';

  data = [[1, 2, 3, 4, 5, 6, 7, 8]];
  labels = ['hoi', 'doei', 'hallo', 'hee', 'hoi', 'doei', 'hallo', 'hee'];

  ngOnInit(): void {
  }

}
