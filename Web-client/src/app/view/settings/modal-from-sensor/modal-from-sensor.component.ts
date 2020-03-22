import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ApiService} from '../../../services/api.service';
import {SensorsService} from '../sensors.service';

@Component({
  selector: 'app-modal-from-sensor',
  templateUrl: './modal-from-sensor.component.html',
  styleUrls: ['./modal-from-sensor.component.scss']
})
export class ModalFromSensorComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<ModalFromSensorComponent>,
    private api: SensorsService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  onYesClick(): void {
    this.data.bottomThreshold = Number(this.data.bottomThreshold);
    this.data.topThreshold = Number(this.data.topThreshold);
    this.api.postData(this.data).subscribe();

  }

  onNoClick() {
    this.dialogRef.close();
  }

  ngOnInit(): void {
  }

}
