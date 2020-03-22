import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-modal-from-sensor',
  templateUrl: './modal-from-sensor.component.html',
  styleUrls: ['./modal-from-sensor.component.scss']
})
export class ModalFromSensorComponent implements OnInit {

  constructor(
    public dialogRef: MatDialogRef<ModalFromSensorComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  ngOnInit(): void {
  }

}
